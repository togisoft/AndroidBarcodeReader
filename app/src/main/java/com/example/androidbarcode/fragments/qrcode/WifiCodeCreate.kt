package com.example.androidbarcode.fragments.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidbarcode.R
import com.example.androidbarcode.databinding.FragmentWifiCodeCreateBinding
import com.example.androidbarcode.helper.ImageSaveOptions
import com.google.android.material.snackbar.Snackbar


class WifiCodeCreate : Fragment() {
    private var binding: FragmentWifiCodeCreateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentWifiCodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.createdImage?.isVisible = false
        binding?.saveImageGallery?.isVisible = false
        binding?.createWifi?.setOnClickListener { createWifiCode() }
        binding?.saveImageGallery?.setOnClickListener { saveImageGallery(view) }
    }

    private fun createWifiCode() {
        var wifiType: String? = null
        val wpa = binding?.wpa
        val wep = binding?.wep
        val networkName = binding?.networkName?.editText?.text
        val networkPass = binding?.password?.editText?.text

        if (wpa?.isChecked == true) {
            wifiType = "WPA"
        } else if (wep?.isChecked == true) {
            wifiType = "WEP"
        }
        val wifi = "WIFI:T:$wifiType;S:$networkName;P:$networkPass;;"

        val image = com.example.androidbarcode.helper.createQrCode(wifi, requireContext())
        binding?.createdImage?.setImageDrawable(image)
        binding?.createdImage?.isVisible = true
        binding?.saveImageGallery?.isVisible = true

    }

    private fun saveImageGallery(view: View) {
        var wifiType: String? = null
        val wpa = binding?.wpa
        val wep = binding?.wep
        val networkName = binding?.networkName?.editText?.text
        val networkPass = binding?.password?.editText?.text

        if (wpa?.isChecked == true) {
            wifiType = "WPA"
        } else if (wep?.isChecked == true) {
            wifiType = "WEP"
        }
        val wifi = "WIFI:T:$wifiType;S:$networkName;P:$networkPass;;"
        try {
            ImageSaveOptions.saveImagePngGallery(
                requireContext(),
                this.requireView(),
                com.example.androidbarcode.helper.createQrCode(
                    wifi,
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