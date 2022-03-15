package com.example.androidbarcode.fragments.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidbarcode.R
import com.example.androidbarcode.databinding.FragmentLinkCodeCreateBinding
import com.example.androidbarcode.helper.ImageSaveOptions
import com.google.android.material.snackbar.Snackbar


class LinkCodeCreate : Fragment() {

    private var binding: FragmentLinkCodeCreateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentLinkCodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.createdImage?.isVisible = false
        binding?.saveImageGallery?.isVisible = false
        val valueText = binding?.barcodeText?.editText?.text
        val image =
            com.example.androidbarcode.helper.createQrCode(valueText.toString(), requireContext())
        binding?.createLinkCode?.setOnClickListener {
            binding?.createdImage?.setImageDrawable(image)
            binding?.createdImage?.isVisible = true
            binding?.saveImageGallery?.isVisible = true
        }
        binding?.saveImageGallery?.setOnClickListener { saveImageGallery(view) }
    }

    private fun saveImageGallery(view: View) {
        try {
            ImageSaveOptions.saveImagePngGallery(
                requireContext(),
                this.requireView(),
                com.example.androidbarcode.helper.createQrCode(
                    binding?.barcodeText?.editText?.text.toString(),
                    requireContext()
                )
            )
            Snackbar.make(view, R.string.saved_success, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(context?.getColor(R.color.dark_green)!!)
                .show()
        } catch (ex: Exception) {
            Snackbar.make(view, R.string.error, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(context?.getColor(R.color.red)!!)
                .show()
        }
    }

}