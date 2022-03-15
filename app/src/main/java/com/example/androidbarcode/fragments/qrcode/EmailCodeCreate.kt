package com.example.androidbarcode.fragments.qrcode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.androidbarcode.R
import com.example.androidbarcode.adaptor.setposition.saveImageGallery
import com.example.androidbarcode.databinding.FragmentEmailCodeCreateBinding
import com.example.androidbarcode.helper.createQrCode
import com.example.androidbarcode.helper.snackBarSuccess
import java.lang.Exception


class EmailCodeCreate : Fragment() {
    private var binding: FragmentEmailCodeCreateBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentEmailCodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.createdImage?.isVisible = false
        binding?.saveImageGallery?.isVisible = false

        binding?.createEmailCode?.setOnClickListener {
            val email = binding?.emailText?.editText?.text.toString()
            val emailText = "mailto:$email"
            val image = createQrCode(emailText, requireContext())
            binding?.createdImage?.setImageDrawable(image)
            binding?.createdImage?.isVisible = true
            binding?.saveImageGallery?.isVisible = true
            binding?.saveImageGallery?.setOnClickListener {
                try {
                    saveImageGallery(requireContext(), emailText, requireView())
                    snackBarSuccess(requireView(), requireContext(), R.string.saved_success)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}