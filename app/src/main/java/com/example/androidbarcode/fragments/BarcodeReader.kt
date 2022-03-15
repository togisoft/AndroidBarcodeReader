package com.example.androidbarcode.fragments

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.camera.core.TorchState
import androidx.camera.view.PreviewView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbarcode.R
import com.example.androidbarcode.databinding.FragmentBarcodeReaderBinding
import com.example.androidbarcode.helper.fullCountryName
import com.example.androidbarcode.helper.qrcode.QrCodeParse
import com.example.androidbarcode.helper.toStringId
import com.example.androidbarcode.model.BarcodeResultModel
import com.google.android.material.slider.Slider
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.google.zxing.ResultMetadataType
import com.king.zxing.CameraScan
import com.king.zxing.DecodeConfig
import com.king.zxing.DecodeFormatManager
import com.king.zxing.DefaultCameraScan
import com.king.zxing.analyze.MultiFormatAnalyzer
import com.king.zxing.config.CameraConfig
import com.king.zxing.config.ResolutionCameraConfig

class BarcodeReader : Fragment(), CameraScan.OnScanResultCallback {

    private var binding: FragmentBarcodeReaderBinding? = null
    private val viewModel: BarcodeResultModel by activityViewModels()
    private var mCameraScan: CameraScan? = null
    private var previewView: PreviewView? = null
    private var mediaPlayer: MediaPlayer? = null
    private val decodeConfig: DecodeConfig = DecodeConfig()
    private var flashStatus: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fragmentBinding = FragmentBarcodeReaderBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initUI()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.barcode_reader_menu, menu)
    }

    override fun onDestroy() {
        mCameraScan?.release()
        mediaPlayer?.release()
        super.onDestroy()
    }

    override fun onResume() {
        mCameraScan?.startCamera()
        super.onResume()
    }

    override fun onPause() {
        mCameraScan?.stopCamera()
        mediaPlayer?.stop()

        super.onPause()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.flash -> {
                return when (mCameraScan?.camera?.cameraInfo?.torchState?.value) {
                    TorchState.ON -> {
                        mCameraScan?.enableTorch(false)
                        item.setIcon(R.drawable.menu_flash_off)
                        flashStatus = false
                        true
                    }
                    TorchState.OFF -> {
                        mCameraScan?.enableTorch(true)
                        item.setIcon(R.drawable.menu_flash)
                        flashStatus = true
                        true
                    }
                    else -> true
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initUI() {
        decodeConfig.setHints(DecodeFormatManager.ALL_HINTS)
            .setSupportVerticalCode(true)
            .setSupportLuminanceInvert(true)
            .setAreaRectRatio(0.8f).isFullAreaScan = false

        previewView = binding?.previewView
        mCameraScan = DefaultCameraScan(this.requireParentFragment(), previewView!!)
        (mCameraScan as DefaultCameraScan).setOnScanResultCallback(this)
            .setVibrate(true)
            .setAnalyzeImage(true)
            .setCameraConfig(CameraConfig())
            .setCameraConfig(ResolutionCameraConfig(requireContext()))
            .setNeedTouchZoom(true)
            .setNeedAutoZoom(true)
            .setDarkLightLux(45f)
            .setBrightLightLux(100f)
            .setAnalyzer(MultiFormatAnalyzer(decodeConfig))
            .startCamera()
        zoom()

    }

    override fun onScanResultCallback(result: Result?): Boolean {
        if (result?.text != null) {
            mCameraScan?.stopCamera()
            if (result.barcodeFormat == BarcodeFormat.QR_CODE) {
                QrCodeParse.qrCodeParse(
                    result,
                    viewModel,
                    requireView(),
                    requireContext(),
                    mCameraScan
                )
            } else {
                barcodeParse(result)
            }
            mediaPlayer = MediaPlayer.create(context, R.raw.sound)
            mediaPlayer!!.start()
        } else {
            return false
        }
        return true
    }


    private fun barcodeParse(result: Result) {
        val barcode = result.text.toString()
        val format = result.barcodeFormat
//        val socket: ClientSocket = ClientSocket()
//        socket.run(barcode)
        val country =
            result.resultMetadata?.get(ResultMetadataType.POSSIBLE_COUNTRY) as String?
        viewModel.setBarcode(barcode)
        format?.let { viewModel.setFormat(it) }
        if (format.toStringId() == R.string.barcode_format_ean_13) {
            viewModel.setCountry(fullCountryName(country.toString(), requireContext()))
        } else {
            viewModel.setCountry("NO DATA")
        }
        try {
            mediaPlayer?.release()
            findNavController().navigate(BarcodeReaderDirections.actionBarcodeReaderToBarcodeResultFragment())

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }


    private fun zoom() {
        val slider1 = binding?.zoom
        slider1?.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: Slider) {}
            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: Slider) {
            }
        })
        slider1?.addOnChangeListener { _, value, _ ->
            mCameraScan?.camera?.cameraControl?.setLinearZoom(value / 100.toFloat())
        }
        val zoomOut = binding?.zoomOut
        val zoomIn = binding?.zoomIn

        zoomOut?.setOnClickListener {
            if (slider1?.value!! >= 10.toFloat()) {
                slider1.value -= 10
            }
        }
        zoomIn?.setOnClickListener {
            if (slider1?.value!! < 100.toFloat()) {
                slider1.value += 10
            }
        }
    }
}