package com.kathayat.weboconnectassignment.signup.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.kathayat.weboconnectassignment.R
import com.kathayat.weboconnectassignment.databinding.FragmentVerificationCodeBinding
import com.kathayat.weboconnectassignment.util.Constants.Companion.USER_OTP
import com.kathayat.weboconnectassignment.util.Constants.Companion.USER_PHONE_NUMBER
import com.kathayat.weboconnectassignment.util.SharedPrefDataStore


class VerificationCodeFragment : Fragment() {

    private var _binding: FragmentVerificationCodeBinding? = null
    private val binding by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVerificationCodeBinding.inflate(inflater, container, false)

        setOtpListener()

        val phoneNum = SharedPrefDataStore.init().getValue(requireContext(), USER_PHONE_NUMBER)

        binding.textView7.text = "+91" + phoneNum

        binding.sumitOtpButton.setOnClickListener {

        }
        binding.backArrowPhoneCode.setOnClickListener {
            findNavController().navigate(R.id.action_verificationCodeFragment_to_phoneNumberFragment)
        }



        return binding.root

    }

    private fun setOtpListener() {
        val number1 = binding.otp1
        val number2 = binding.otp2
        val number3 = binding.otp3
        val number4 = binding.otp4
        number1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (number1.text!!.length == 1) {
                    number2.requestFocus()
                }
            }
        })
        number2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (number2.text!!.length == 1) {
                    number3.requestFocus()
                } else if (number2.text!!.isEmpty()) {
                    number1.requestFocus()
                }
            }

        })
        number3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (number3.text!!.length == 1) {
                    number4.requestFocus()
                } else if (number3.text!!.isEmpty()) {
                    number2.requestFocus()
                }
            }
        })
        number4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireView().windowToken, 0)
                if (number4.text!!.length == 1
                    && number3.text!!.length == 1
                    && number2.text!!.length == 1
                    && number1.text!!.length == 1
                ) {
                    binding.sumitOtpButton.setOnClickListener {
                        val finalOTP =
                            number1.text!!.toString() +
                                    number2.text!! +
                                    number3.text!! +
                                    number4.text!!
                        val otpShared =
                            SharedPrefDataStore.init().getValue(requireContext(), USER_OTP)
                        if (otpShared == finalOTP) {
                            findNavController().navigate(R.id.action_verificationCodeFragment_to_createProfileFragment)
                        } else {
                            binding.inVaildOTPError.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
        number2.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL && number2.text.isNullOrEmpty()) {
                number1.requestFocus()
            }
            false
        }
        number3.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL && number3.text.isNullOrEmpty()) {
                number2.requestFocus()
            }
            false
        }
        number4.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL && number4.text.isNullOrEmpty()) {
                number3.requestFocus()
            }
            false
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}