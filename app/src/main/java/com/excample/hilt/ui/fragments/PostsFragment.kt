package com.excample.hilt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.excample.hilt.databinding.FragmentPostsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var binding: FragmentPostsBinding? = null
    private val viewModel by viewModels<PostsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater , container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRequests()
        setupObserves()
    }

    private fun setupObserves() {
        viewModel.postLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRequests() = binding?.let { binding ->
        binding.btnGetMore.setOnClickListener{
            val id = binding.etPostNumber.text.toString().toInt()
            val title = binding.etPostTitle.text.toString().trim()
            val url = binding.etPostBody.text.toString().trim()
            val thumbnailUrl = binding.etPostThumbnailUrl.text.toString().trim()
            viewModel.sendPost(
                PostAlbumId = 101,
                PostId = id,
                PostTitle = title,
                PostUrl = url,
                PostThumbnailUrl = thumbnailUrl
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}