package com.example.androidbarcode.fragments.qrcode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.androidbarcode.R
import com.example.androidbarcode.adaptor.setposition.saveImageGallery
import com.example.androidbarcode.databinding.FragmentPhoneCodeCreateBinding
import com.example.androidbarcode.helper.createQrCode
import com.example.androidbarcode.helper.snackBarError
import com.example.androidbarcode.helper.snackBarSuccess
import java.lang.Exception


class PhoneCodeCreate : Fragment() {
    private var binding: FragmentPhoneCodeCreateBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPhoneCodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.createdImage?.isVisible = false
        binding?.saveImageGallery?.isVisible = false
        binding?.createPhoneCode?.setOnClickListener {
            val textData = binding?.phoneNumber?.editText?.text
            val phoneCode = "tel:$textData"
            val image = createQrCode(phoneCode, requireContext())
            binding?.createdImage?.setImageDrawable(image)
            binding?.createdImage?.isVisible = true
            binding?.saveImageGallery?.isVisible = true
            binding?.saveImageGallery?.setOnClickListener {
                try {
                    saveImageGallery(requireContext(), phoneCode, requireView())
                    snackBarSuccess(requireView(), requireContext(), R.string.saved_success)
                } catch (ex: Exception) {
                    snackBarError(requireView(), requireContext(), R.string.error)
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}