package com.example.androidbarcode.fragments.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.androidbarcode.R
import com.example.androidbarcode.databinding.FragmentShowBarcodeImageBinding
import com.example.androidbarcode.helper.ImageSaveOptions.printImage
import com.example.androidbarcode.helper.ImageSaveOptions.saveImagePngGallery
import com.example.androidbarcode.helper.createBarcode
import com.example.androidbarcode.helper.createQrCode
import com.example.androidbarcode.helper.toStringId
import com.example.androidbarcode.model.BarcodeResultModel

class ShowBarcodeImage : Fragment() {

    private var binding: FragmentShowBarcodeImageBinding? = null
    private val viewModel: BarcodeResultModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentShowBarcodeImageBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.barcodeFormat.value?.let {
            (requireActivity() as AppCompatActivity).supportActionBar?.setTitle(it.toStringId())
        }
        binding?.finalBarcodeImage?.setImageDrawable(viewModel.barcodeImage.value)
        if (viewModel.barcodeFormat.value?.toStringId() == R.string.barcode_format_qr_code){
            binding?.qrcodeText?.text = viewModel.barcode.value
        }else {
            binding?.qrcodeText?.text = ""
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.barcode_image_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.print_barcode -> {
                printImage(requireActivity(), viewModel.barcodeImage.value!!)
                return true
            }
            R.id.save_image -> {
                if (viewModel.barcodeFormat.value?.toStringId() == R.string.barcode_format_qr_code){
                    saveImagePngGallery(
                        requireContext(),
                        this.requireView(),
                        createQrCode(viewModel.barcode.value.toString(), requireContext())
                    )
                }else{
                    saveImagePngGallery(
                        requireContext(),
                        this.requireView(),
                        createBarcode(viewModel.barcode.value.toString(),
                            viewModel.barcodeFormat.value!!, requireContext())
                    )
                }

            }
        }
        return true
    }
}