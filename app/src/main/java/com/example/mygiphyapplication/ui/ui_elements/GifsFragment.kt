package com.example.mygiphyapplication.ui.ui_elements

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.mygiphyapplication.Injection
import com.example.mygiphyapplication.R
import com.example.mygiphyapplication.databinding.FragmentGifsBinding
import com.example.mygiphyapplication.ui.state_holders.GifsPagingViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GifsFragment : Fragment() {
    private var _binding: FragmentGifsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGifsBinding.inflate(inflater, container, false)
        FROM_GIFS = 1
        val viewModel by viewModels<GifsPagingViewModel>(
            factoryProducer = { Injection.provideViewModelFactory(owner = this) }
        )
        val items = viewModel.items
        val adapter = GifsPagingAdapter()

        binding.bindAdapter(adapter = adapter)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                items.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    if (it.source.append is LoadState.Loading) {
                        binding.statusTv.text = "Loading"
                    } else {
                        binding.statusTv.text = "Done"
                    }

                }
            }
        }

        val navigationView = binding.bottomNav

        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val action = GifsFragmentDirections.actionGifsFragmentToHomeFragment()
                    findNavController().navigate(action)
                    true
                }
                R.id.help -> {
                    val action = GifsFragmentDirections.actionGifsFragmentToHelpFragment()
                    findNavController().navigate(action)
                    true
                }
                else -> {
                    val action = GifsFragmentDirections.actionGifsFragmentToSettingsFragment()
                    findNavController().navigate(action)
                    true
                }
            }
        }
        return binding.root
    }
}

private fun FragmentGifsBinding.bindAdapter(
    adapter: GifsPagingAdapter
) {
    rv.adapter = adapter
}
