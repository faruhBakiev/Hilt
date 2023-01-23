package com.excample.hilt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.excample.hilt.R
import com.excample.hilt.data.adapters.GetAdapter
import com.excample.hilt.databinding.FragmentGetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetFragment : Fragment() {

    private var binding: FragmentGetBinding? = null
    private val viewModel: GetViewModel by viewModels()
    private val getAdapter = GetAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserves()
        setupRequests()
        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        binding?.btnGetMore?.setOnClickListener {
            findNavController().navigate(R.id.action_getFragment_to_postsFragment)
        }
    }

    private fun setupRequests() {
        viewModel.getPosts()
    }

    private fun setupObserves() {
        viewModel.getLiveData.observe(viewLifecycleOwner) {
            getAdapter.submitList(it)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initialize() {
        binding?.recyclerView?.adapter = getAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}