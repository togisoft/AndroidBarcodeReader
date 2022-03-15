package com.example.androidbarcode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidbarcode.databinding.FragmentQrcodeCreateBinding
import java.lang.Exception


class QRCreateFragment : Fragment() {
    private var binding: FragmentQrcodeCreateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentQrcodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.textCode?.setOnClickListener {
            try {
                findNavController().navigate(
                    QRCreateFragmentDirections.actionQRCreateFragmentToTextCodeCreate()
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        binding?.linkCode?.setOnClickListener {
            try {
                findNavController().navigate(QRCreateFragmentDirections.actionQRCreateFragmentToLinkCodeCreate())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        binding?.wifiCode?.setOnClickListener {
            try {
                findNavController().navigate(QRCreateFragmentDirections.actionQRCreateFragmentToWifiCodeCreate())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        binding?.locationCode?.setOnClickListener {
            try {
                findNavController().navigate(QRCreateFragmentDirections.actionQRCreateFragmentToGeoCodeCreate())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        binding?.contactCode?.setOnClickListener {
            try {
                findNavController().navigate(QRCreateFragmentDirections.actionQRCreateFragmentToContactCardCreate())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        binding?.phoneCode?.setOnClickListener {
            try {
                findNavController().navigate(QRCreateFragmentDirections.actionQRCreateFragmentToPhoneCodeCreate())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        binding?.eventCode?.setOnClickListener {
            try {
                findNavController().navigate(QRCreateFragmentDirections.actionQRCreateFragmentToEventCodeCreate())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        binding?.smsCode?.setOnClickListener {
            try {
                findNavController().navigate(QRCreateFragmentDirections.actionQRCreateFragmentToSmsCodeCreate())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        binding?.email?.setOnClickListener {
            try {
                findNavController().navigate(QRCreateFragmentDirections.actionQRCreateFragmentToEmailCodeCreate())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

}