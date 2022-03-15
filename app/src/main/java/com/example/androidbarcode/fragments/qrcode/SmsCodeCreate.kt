package com.example.androidbarcode.fragments.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidbarcode.R
import com.example.androidbarcode.adaptor.setposition.saveImageGallery
import com.example.androidbarcode.databinding.FragmentSmsCodeCreateBinding
import com.example.androidbarcode.helper.createQrCode
import com.example.androidbarcode.helper.snackBarSuccess


class SmsCodeCreate : Fragment() {
    private var binding: FragmentSmsCodeCreateBinding? = null
    private var barcodeText: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSmsCodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.createdImage?.isVisible = false
        binding?.saveImageGallery?.isVisible = false
        binding?.createSmsCode?.setOnClickListener {
            try {
                val phoneNumber = binding?.phoneNumber?.editText?.text.toString()
                val smsText = binding?.smsText?.editText?.text.toString()
                barcodeText = "smsto:$phoneNumber:$smsText"
                val image = createQrCode(barcodeText!!, requireContext())
                binding?.createdImage?.setImageDrawable(image)
                binding?.createdImage?.isVisible = true
                binding?.saveImageGallery?.isVisible = true
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        binding?.saveImageGallery?.setOnClickListener {
            try {
                saveImageGallery(requireContext(), barcodeText!!, requireView())
                snackBarSuccess(requireView(), requireContext(), R.string.saved_success)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}