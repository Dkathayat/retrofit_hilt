package com.kathayat.weboconnectassignment.signup.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.kathayat.weboconnectassignment.R
import com.kathayat.weboconnectassignment.databinding.FragmentPhoneNumberBinding
import com.kathayat.weboconnectassignment.splash.SplashActivity
import com.kathayat.weboconnectassignment.util.Constants
import com.kathayat.weboconnectassignment.util.Constants.Companion.USER_OTP
import com.kathayat.weboconnectassignment.util.Constants.Companion.USER_PHONE_NUMBER
import com.kathayat.weboconnectassignment.util.SharedPrefDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PhoneNumberFragment : Fragment() {
    private var _binding: FragmentPhoneNumberBinding? = null
    private val binding by lazy { _binding!! }
    private var dialogShown = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPhoneNumberBinding.inflate(inflater, container, false)


        binding.requestOtpButton.setOnClickListener {
            if (binding.enterPhoneNumberET.text.length < 10) {
                Toast.makeText(
                    requireContext(),
                    "Please enter a vaild phone number",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireView().windowToken, 0)
                dialogShown = if (!dialogShown) {
                    buildAlertDialog()
                    true
                } else {
                    if (findNavController().currentDestination?.id == R.id.phoneNumberFragment) {
                        findNavController().navigate(R.id.action_phoneNumberFragment_to_verificationCodeFragment)
                    }
                    SharedPrefDataStore.init().setValue(
                        requireContext(),
                        USER_PHONE_NUMBER,
                        binding.enterPhoneNumberET.text.toString()
                    )
                    false
                }
            }
        }
        binding.backArrowPhoneNumber.setOnClickListener {
            startActivity(Intent(requireContext(), SplashActivity::class.java))
            activity?.finish()
        }

        return binding.root
    }

    @SuppressLint("MissingInflatedId")
    private fun buildAlertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.phone_code_alert_dialog, null)
        val phoneCodeAlertDialog = view.findViewById<TextView>(R.id.phoneCodeAlertDialog)
        val firstSub = binding.enterPhoneNumberET.text.subSequence(0, 2)
        val lastSub = binding.enterPhoneNumberET.text.subSequence(8, 10)
        phoneCodeAlertDialog.text = "$firstSub$lastSub"
        SharedPrefDataStore.init().setValue(requireContext(), USER_OTP, "$firstSub$lastSub")
        alertDialog.setView(view)
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}