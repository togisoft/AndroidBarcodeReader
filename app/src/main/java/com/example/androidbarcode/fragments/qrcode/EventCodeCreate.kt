package com.example.androidbarcode.fragments.qrcode

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidbarcode.adaptor.setposition.saveImageGallery
import com.example.androidbarcode.databinding.FragmentEventCodeCreateBinding
import com.example.androidbarcode.helper.createQrCode
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*


class EventCodeCreate : Fragment() {

    private var binding: FragmentEventCodeCreateBinding? = null

    private var starDateSelect: String? = null
    private var startTimeSelect: String? = null
    private var startDate: String? = null

    private var endDateSelect: String? = null
    private var endTimeSelect: String? = null
    private var endDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentEventCodeCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startDate()
        endDate()
        createEvent()
        binding?.createdImage?.isVisible = false
        binding?.saveImageGallery?.isVisible = false

        return super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    private fun startDate() {
        var dateText: String?
        var timeText: String?
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()
        binding?.startDate?.setOnClickListener {
            picker.show(requireActivity().supportFragmentManager, "Select Date")
        }

        picker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val dateFormatter = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
            starDateSelect = dateFormat.format(picker.selection)
            dateText = dateFormatter.format(picker.selection)
            val cal = Calendar.getInstance()
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(cal.get(Calendar.HOUR))
                .setMinute(cal.get(Calendar.MINUTE))
                .setTitleText("Select Time")
                .build()

            timePicker.show(requireActivity().supportFragmentManager, "Select Time")
            timePicker.addOnPositiveButtonClickListener {
                val hour = timePicker.hour
                val minute = timePicker.minute
                startTimeSelect = hour.toString() + minute.toString()
                timeText = "$hour:$minute"
                if (hour < 10) {
                    startTimeSelect = "0$hour$minute"
                    timeText = "0$hour:$minute"
                }
                startDate = starDateSelect + "T" + startTimeSelect + "00Z"
                binding?.startDate?.text = "$dateText $timeText"
            }

        }
    }


    @SuppressLint("SetTextI18n")
    private fun endDate() {
        var dateText: String?
        var timeText: String?
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select End Date")
            .build()
        binding?.endDate?.setOnClickListener {
            picker.show(requireActivity().supportFragmentManager, "Select End Date")
        }

        picker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            endDateSelect = dateFormat.format(picker.selection)
            dateText = dateFormatter.format(picker.selection)
            val cal = Calendar.getInstance()
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(cal.get(Calendar.HOUR))
                .setMinute(cal.get(Calendar.MINUTE))
                .setTitleText("Select Time")
                .build()
            timePicker.show(requireActivity().supportFragmentManager, "Select Time")
            timePicker.addOnPositiveButtonClickListener {
                val hour = timePicker.hour
                val minute = timePicker.minute
                var lastMinute = minute.toString()
                var lastHour = hour.toString()
                if (minute < 10) {
                    lastMinute = "${minute}0"
                }
                if (hour < 10) {
                    lastHour = "0$hour"
                }
                endTimeSelect = lastHour + lastMinute
                timeText = "$lastHour:$lastMinute"

                endDate = endDateSelect + "T" + endTimeSelect + "00Z"
                binding?.endDate?.text = "$dateText $timeText"
                Log.d("Date", endDate.toString())
            }
        }
    }

    private fun createEvent() {
        binding?.createEvent?.setOnClickListener {
            val title = binding?.title?.editText?.text.toString()
            val location = binding?.location?.editText?.text.toString()
            val summary = binding?.description?.editText?.text.toString()

            val barcodeText =
                "BEGIN:VEVENT\nSUMMARY:$title\nDTSTART:$startDate\nDTEND:$endDate\nLOCATION:$location\nDESCRIPTION:$summary\nEND:VEVENT"
            val image = createQrCode(barcodeText, requireContext())
            binding?.createdImage?.setImageDrawable(image)
            binding?.createdImage?.isVisible = true
            binding?.saveImageGallery?.isVisible = true
            binding?.saveImageGallery?.setOnClickListener {
                saveImageGallery(requireContext(), barcodeText, requireView())
            }
        }
    }
}