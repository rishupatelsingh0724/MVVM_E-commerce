package com.example.e_commerce.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.e_commerce.databinding.FragmentProfileBinding
import com.example.e_commerce.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.profileData.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                binding.userProfileName.text = data.name
                binding.userProfileEmail.text = data.email
                Glide.with(requireActivity()).load(data.image).into(binding.profileImage)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchProfileData()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}