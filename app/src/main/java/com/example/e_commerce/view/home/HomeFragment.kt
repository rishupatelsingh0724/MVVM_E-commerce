package com.example.e_commerce.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_commerce.R
import com.example.e_commerce.adapter.HomeAdapter
import com.example.e_commerce.databinding.FragmentHomeBinding
import com.example.e_commerce.model.homeDataModel.HomeItemModel
import com.example.e_commerce.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private lateinit var adapter: HomeAdapter
    private lateinit var itemList: ArrayList<HomeItemModel>
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]


        val imageSliderArray = ArrayList<SlideModel>()
        imageSliderArray.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageSliderArray.add(SlideModel(R.drawable.banner_1, ScaleTypes.FIT))
        imageSliderArray.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        binding.imageSlider.setImageList(imageSliderArray)

        itemList = ArrayList()
        recyclerView = binding.recyclerView
        adapter = HomeAdapter(requireContext(), itemList)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        viewModel.liveData.observe(viewLifecycleOwner) {
            itemList.clear()
            itemList.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.fetchHomeItems(requireContext())





        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}