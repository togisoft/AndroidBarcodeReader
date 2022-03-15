package com.example.androidbarcode.fragments.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidbarcode.R
import com.example.androidbarcode.adaptor.setposition.saveImageGallery
import com.example.androidbarcode.databinding.FragmentContactCardCreateBinding
import com.example.androidbarcode.helper.createQrCode
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception


class ContactCardCreate : Fragment() {

    private var binding: FragmentContactCardCreateBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentContactCardCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            binding?.createdImage?.isVisible = false
            binding?.saveImageGallery?.isVisible = false

        binding?.createGeo?.setOnClickListener {
            val name = binding?.firstName?.editText?.text
            val surName = binding?.lastName?.editText?.text
            val company = binding?.company?.editText?.text
            val title = binding?.title?.editText?.text
            val phone = binding?.phone?.editText?.text
            val email = binding?.email?.editText?.text
            val address = binding?.address?.editText?.text
            val webSite = binding?.address?.editText?.text
            val cardText =
                "BEGIN:VCARD\nVERSION:3.0\nN:$name $surName\nORG:$company\nTITLE:$title\nTEL:$phone\nEMAIL:$email\nADR:$address\nURL:$webSite\nEND:VCARD"
            val image = createQrCode(cardText, requireContext())
            binding?.createdImage?.setImageDrawable(image)
            binding?.createdImage?.isVisible = true
            binding?.saveImageGallery?.isVisible = true
            binding?.saveImageGallery?.setOnClickListener {
                try {
                    saveImageGallery(requireContext(), cardText, requireView())
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
        super.onViewCreated(view, savedInstanceState)
    }
}