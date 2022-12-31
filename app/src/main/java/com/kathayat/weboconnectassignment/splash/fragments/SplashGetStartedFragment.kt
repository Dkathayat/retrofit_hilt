package com.kathayat.weboconnectassignment.splash.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kathayat.weboconnectassignment.databinding.FragmentSignUpGetStartedBinding
import com.kathayat.weboconnectassignment.signup.SignUpActivity

class SplashGetStartedFragment : Fragment() {

    private var _binding:FragmentSignUpGetStartedBinding?=null
    private val binding by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentSignUpGetStartedBinding.inflate(inflater,container,false)


        binding.splashGetStartedButton.setOnClickListener {
            startActivity(Intent(requireContext(),SignUpActivity::class.java))
            activity?.finish()
        }


        return binding.root

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}