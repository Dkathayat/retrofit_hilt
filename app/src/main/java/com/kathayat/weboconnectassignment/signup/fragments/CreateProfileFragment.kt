package com.kathayat.weboconnectassignment.signup.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kathayat.weboconnectassignment.MainActivity
import com.kathayat.weboconnectassignment.R
import com.kathayat.weboconnectassignment.databinding.FragmentCreateProfileBinding
import com.kathayat.weboconnectassignment.network.NetworkResource
import com.kathayat.weboconnectassignment.repository.MainRepository
import com.kathayat.weboconnectassignment.util.SharedPrefDataStore
import com.kathayat.weboconnectassignment.viewmodel.MainViewModelFactory
import com.kathayat.weboconnectassignment.viewmodel.RegistationViewModel

class CreateProfileFragment : Fragment() {

    private var _binding: FragmentCreateProfileBinding? = null
    private val binding by lazy { _binding!! }
    private lateinit var registationViewModel: RegistationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateProfileBinding.inflate(inflater, container, false)

        val mainRepository = MainRepository()
        val viewModelFactory = MainViewModelFactory(mainRepository)
        registationViewModel =
            ViewModelProvider(this, viewModelFactory)[RegistationViewModel::class.java]


        registationViewModel.userResponse.observe(requireActivity(), Observer { response ->

            when(response) {
                is NetworkResource.Success -> {
                    //Got the Success response do what you want to do next
                }
                is NetworkResource.Loading -> {
                    // You can show progress bar
                }
                is NetworkResource.Error -> {
                    Log.e(TAG,"Registration failed ${response.message}")
                }
            }

        })


        val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                binding.shapeableImgCreateAccount.setImageURI(it)
            }
        }

        binding.shapeableImgCreateAccount.setOnClickListener {
            contract.launch("image/*")
        }

        binding.backArrowCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_createProfileFragment_to_verificationCodeFragment)
        }

        binding.summitAccountButton.setOnClickListener {
            if (validateInput()) {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                activity?.finish()

                // Below code will register the user to the backend server

//                registationViewModel.registerUser(
//                    binding.editTextFirstName.text.toString(),
//                    binding.editTextLastName.text.toString(),
//                    binding.editTextPhoneNumber.text.toString(),
//                    binding.editTextPostalCode.text.toString()
//                )
            SharedPrefDataStore.init().setValue(requireContext(),"Signed_up","success")
            }


        }

        binding.pickLocationButton.setOnClickListener {
            Snackbar.make(binding.root, "Location Fetched...", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }

    fun validateInput(): Boolean {
        if (binding.editTextFirstName.text.length < 2) {
            binding.editTextFirstName.error = "Please enter a valid Name"
            return false
        }
        if (binding.editTextLastName.text.length < 6) {
            binding.editTextLastName.error = "Please enter a valid Name"
            return false

        }
        if (binding.editTextPhoneNumber.text.length < 10) {
            binding.editTextPhoneNumber.error = "Please enter a valid phone number"
            return false
        }

        if (binding.editTextPostalCode.text.isEmpty()) {
            binding.editTextPostalCode.error = "Please enter a valid Address"
            return false
        }

        return true
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}