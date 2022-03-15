package com.example.androidbarcode.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidbarcode.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

	private var binding: FragmentHomeBinding? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
		binding = fragmentBinding
		return fragmentBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding?.scanBarcode?.setOnClickListener { findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBarcodeReader()) }
		binding?.createQrCode?.setOnClickListener {findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQRCreateFragment())}


	}


}