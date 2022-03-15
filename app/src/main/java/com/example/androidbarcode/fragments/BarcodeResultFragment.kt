package com.example.androidbarcode.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.androidbarcode.R
import com.example.androidbarcode.adaptor.CellClickListener
import com.example.androidbarcode.adaptor.setposition.*
import com.example.androidbarcode.databinding.FragmentBarcodeResultBinding
import com.example.androidbarcode.helper.*
import com.example.androidbarcode.model.BarcodeResultModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.result.ParsedResultType

class BarcodeResultFragment : Fragment(), CellClickListener {

    private var binding: FragmentBarcodeResultBinding? = null
    private val viewModel: BarcodeResultModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fragmentBinding = FragmentBarcodeResultBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        resultButton()
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.barcodeFormat.value?.let {
            (requireActivity() as AppCompatActivity).supportActionBar?.setTitle(it.toStringId())
        }
        setResult()
    }

    private fun setResult() {
        val textFlag = binding?.textFlag
        val barcodeCountry = viewModel.barcodeCountry.value
        val barcodeText = viewModel.barcode.value
        val type = viewModel.qr_type.value
        //Wifi
        if (viewModel.barcodeFormat.value == BarcodeFormat.QR_CODE) {
            when (type) {
                ParsedResultType.WIFI -> {
                    val name = viewModel.wifi_name.value
                    val pass = viewModel.wifi_pass.value
                    val encryption = viewModel.wifi_encryption.value
                    "$encryption\n$name\n$pass".also { binding?.edtResult?.text = it }
                }
                ParsedResultType.TEXT -> {
                    binding?.edtResult?.text = barcodeText
                }
                ParsedResultType.URI -> {
                    binding?.edtResult?.text = barcodeText
                }
                ParsedResultType.GEO -> {
                    binding?.edtResult?.text = barcodeText
                }
                ParsedResultType.TEL -> {
                    binding?.edtResult?.text = barcodeText
                }
                else -> {}
            }
        }
        binding?.createdDate?.text = getDate()
        binding?.edtResult?.text = barcodeText
        if (barcodeCountry.toString() == "NO DATA") {
            textFlag?.isVisible = false
        } else {
            textFlag?.text = barcodeCountry
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.barcode_result_menu, menu)
    }


    private fun resultButton() {
        when (viewModel.barcodeFormat.value?.toStringId()) {
            R.string.barcode_format_ean_13 -> {
                layoutAdapter(binding?.barcodeSection!!, requireContext(), barcodeSection(10), this)
            }
            R.string.barcode_format_ean_8 -> {
                layoutAdapter(binding?.barcodeSection!!, requireContext(), barcodeSection(10), this)
            }
            R.string.barcode_format_qr_code -> {
                when (viewModel.qr_type.value) {
                    ParsedResultType.WIFI -> {
                        layoutAdapter(
                            binding?.barcodeSection!!,
                            requireContext(),
                            qrWifiSection(11),
                            this
                        )
                    }
                    ParsedResultType.URI -> {
                        layoutAdapter(
                            binding?.barcodeSection!!,
                            requireContext(),
                            qrLinkSection(7),
                            this
                        )
                    }
                    ParsedResultType.GEO -> {
                        layoutAdapter(
                            binding?.barcodeSection!!,
                            requireContext(),
                            qrGeoSection(11),
                            this
                        )
                    }
                    ParsedResultType.TEL -> {
                        layoutAdapter(
                            binding?.barcodeSection!!,
                            requireContext(),
                            qrTelSection(9),
                            this
                        )
                    }
                    ParsedResultType.CALENDAR -> {
                        layoutAdapter(
                            binding?.barcodeSection!!,
                            requireContext(),
                            qrEventSection(7), this
                        )
                    }
                    else -> layoutAdapter(
                        binding?.barcodeSection!!,
                        requireContext(),
                        qrSection(7),
                        this
                    )
                }
            }
            else -> {
                layoutAdapter(binding?.barcodeSection!!, requireContext(), qrSection(7), this)
            }
        }
    }

    override fun onCellClickListener(position: Int) {
        if (viewModel.barcodeFormat.value?.toStringId() == R.string.barcode_format_ean_13 ||
            viewModel.barcodeFormat.value?.toStringId() == R.string.barcode_format_ean_8
        ) {
            adapterPosition(
                position,
                binding?.edtResult?.text.toString(),
                requireContext(),
                requireActivity(),
                viewModel,
                requireView()
            )
        } else if (viewModel.barcodeFormat.value?.toStringId() == R.string.barcode_format_qr_code) {
            when (viewModel.qr_type.value) {
                ParsedResultType.WIFI -> {
                    wifiPosition(
                        position,
                        requireContext(),
                        requireActivity(),
                        viewModel,
                        binding?.edtResult?.text.toString(),
                        requireView()
                    )
                }
                ParsedResultType.URI -> {
                    linkPositionQr(
                        position,
                        requireContext(),
                        requireActivity(),
                        viewModel,
                        binding?.edtResult?.text.toString(),
                        requireView()
                    )
                }
                ParsedResultType.GEO -> {
                    geoPosition(
                        position,
                        requireContext(),
                        requireActivity(),
                        viewModel,
                        binding?.edtResult?.text.toString(),
                        requireView()
                    )
                }
                ParsedResultType.TEL -> {
                    telPositionQr(
                        position,
                        requireContext(),
                        requireActivity(),
                        viewModel,
                        binding?.edtResult?.text.toString(),
                        requireView()
                    )
                }
                ParsedResultType.CALENDAR -> {
                    val title = viewModel.eventTitle.value
                    val location = viewModel.eventLocation.value
                    val startDate = viewModel.startDate.value
                    val endDate = viewModel.endDate.value
                    val description = viewModel.eventDesc.value

//                    eventPositionQr(position,
//                    requireContext(),
//                    requireActivity(),
//                    viewModel.)
                }
                else -> {
                    otherPositionQr(
                        position,
                        requireContext(),
                        requireActivity(),
                        viewModel,
                        binding?.edtResult?.text.toString(),
                        requireView()
                    )
                }
            }
        } else {
            otherPosition(
                position,
                requireContext(),
                requireActivity(),
                viewModel,
                binding?.edtResult?.text.toString(),
                requireView()
            )
        }
    }
}

