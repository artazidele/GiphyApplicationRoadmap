package com.example.mygiphyapplication.ui.ui_elements

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Button
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
                    showHelp()
                    true
                }
                R.id.settings -> {
                    showSettings()
                    true
                }
                else -> {
                    true
                }
            }
        }
        return binding.root
    }

    private fun showSettings() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.settings_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.close_btn).setOnClickListener {
            alertDialog.dismiss()
        }
    }

    private fun showHelp() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.help_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.close_btn).setOnClickListener {
            alertDialog.dismiss()
        }
    }

}

private fun FragmentGifsBinding.bindAdapter(
    adapter: GifsPagingAdapter
) {
    rv.adapter = adapter
}
