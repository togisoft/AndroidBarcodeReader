package com.example.androidbarcode.fragments.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidbarcode.R
import com.example.androidbarcode.adaptor.setposition.saveImageGallery
import com.example.androidbarcode.databinding.FragmentTextCodeCreateBinding
import com.example.androidbarcode.helper.createQrCode
import com.example.androidbarcode.helper.snackBarError
import com.example.androidbarcode.helper.snackBarSuccess
import java.lang.Exception


class TextCodeCreate : Fragment() {
    private var binding: FragmentTextCodeCreateBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentTextCodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.createdImage?.isVisible = false
        binding?.saveImageGallery?.isVisible = false
        binding?.createTextCode?.setOnClickListener {
            val barcodeText = binding?.barcodeText?.editText?.text.toString()
            val image = createQrCode(barcodeText, requireContext())
            binding?.createdImage?.setImageDrawable(image)
            binding?.createdImage?.isVisible = true
            binding?.saveImageGallery?.isVisible = true
        }
        binding?.saveImageGallery?.setOnClickListener {
            val barcodeText = binding?.barcodeText?.editText?.text.toString()
            try {
                saveImageGallery(
                    requireContext(),
                    barcodeText,
                    requireView()
                )
                snackBarSuccess(requireView(), requireContext(), R.string.saved_success)
            } catch (ex: Exception) {
                snackBarError(requireView(), requireContext(), R.string.error)
            }
        }
    }
}
