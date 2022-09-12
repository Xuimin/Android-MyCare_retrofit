package com.example.tracingapp.ui.fragment

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tracingapp.R
import com.example.tracingapp.data.viewmodel.HistoryViewModel
import com.example.tracingapp.data.viewmodel.UserViewModel
import com.example.tracingapp.databinding.FragmentScannerBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@AndroidEntryPoint
class ScannerFragment : Fragment() {
    private lateinit var binding: FragmentScannerBinding
    private val userViewModel: UserViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle(Html.fromHtml("<big><big>Check-in</big></big>"))

        binding = FragmentScannerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        setUpScan()
    }

    private fun bindView() {
        userViewModel.getOwnDetails.observe(viewLifecycleOwner) { details ->
            binding.tvUsername.text = details.fullname
            binding.tvIc.text = details.ic

            if(details.isVerified == true) {
                binding.tvVerify.text = "Verified"
                binding.tvVerify.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_verified, 0)
            } else {
                binding.tvVerify.text = "Not Verified"
                binding.tvVerify.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_not_verified, 0)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpScan() {
        binding.btnCheckIn.setOnClickListener {
            val options = ScanOptions()
            options.apply {
                setOrientationLocked(false)
                setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                setPrompt("Scan")
                setCameraId(0)
                setBeepEnabled(true)
                setBarcodeImageEnabled(true)
                setOrientationLocked(true)
            }
            zXingQRCodeScanLauncher.launch(options)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val zXingQRCodeScanLauncher = registerForActivityResult(ScanContract()) {
            result ->
        if(result.contents == null) {
            checkInFail()
        } else {
            val location = result.contents
            var username = ""

            lifecycleScope.launch {
                userViewModel.getOwnDetails.observe(viewLifecycleOwner) {
                    username = it.fullname

                    val time = LocalTime.now().toString()
                    val date = LocalDate.now().toString()

                    checkInSuccess(location, date, time, username)

                    historyViewModel.checkIn(location, date, time)
                }
            }
        }
    }

    fun checkInSuccess(location: String, date: String, time: String, username: String) {
        val view = View.inflate(requireContext(), R.layout.scan_success, null)
        val dialog = Dialog(requireContext())
        dialog.setContentView(view)

        dialog.findViewById<TextView>(R.id.tv_user_name).text = username
        dialog.findViewById<TextView>(R.id.tv_place_name).text = location
        dialog.findViewById<TextView>(R.id.tv_checkin_date).text = date
        dialog.findViewById<TextView>(R.id.tv_checkin_time).text = time
        dialog.show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun checkInFail() {
        val view = View.inflate(requireContext(), R.layout.scan_success, null)
        val dialog = Dialog(requireContext())
        dialog.setContentView(view)
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}