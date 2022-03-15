package com.example.androidbarcode.fragments.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidbarcode.R
import com.example.androidbarcode.adaptor.setposition.saveImageGallery
import com.example.androidbarcode.databinding.FragmentGeoCodeCreateBinding
import com.example.androidbarcode.helper.createQrCode
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception


class GeoCodeCreate : Fragment() {
    private var binding: FragmentGeoCodeCreateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentGeoCodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.createdImage?.isVisible = false
        binding?.saveImageGallery?.isVisible = false
        binding?.createGeo?.setOnClickListener { createGeoCode() }
        binding?.saveImageGallery?.setOnClickListener { saveGallery(view) }
    }

    private fun createGeoCode() {
        val latitude = binding?.latitude?.editText?.text.toString()
        val longitude = binding?.longitude?.editText?.text.toString()
        val geoText = "geo:$latitude,$longitude"
        val image = createQrCode(geoText, requireContext())
        binding?.createdImage?.setImageDrawable(image)
        binding?.createdImage?.isVisible = true
        binding?.saveImageGallery?.isVisible = true
    }

    private fun saveGallery(view: View) {
        val latitude = binding?.latitude?.editText?.text.toString()
        val longitude = binding?.longitude?.editText?.text.toString()
        val geoText = "geo:$latitude,$longitude"
        try {
            saveImageGallery(requireContext(), geoText, requireView())
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