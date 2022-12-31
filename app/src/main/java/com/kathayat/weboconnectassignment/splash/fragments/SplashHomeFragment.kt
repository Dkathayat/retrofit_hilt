package com.kathayat.weboconnectassignment.splash.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kathayat.weboconnectassignment.databinding.FragmentSignUpHomeBinding


class SplashHomeFragment : Fragment() {
    private var _binding:FragmentSignUpHomeBinding?=null
    private val binding by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpHomeBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}