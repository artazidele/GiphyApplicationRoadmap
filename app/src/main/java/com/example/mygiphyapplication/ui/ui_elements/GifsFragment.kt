package com.example.mygiphyapplication.ui.ui_elements

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mygiphyapplication.Injection
import com.example.mygiphyapplication.R
import com.example.mygiphyapplication.data.entities.ResponseItems
import com.example.mygiphyapplication.databinding.FragmentGifsBinding
import com.example.mygiphyapplication.ui.state_holders.GifsPagingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GifsFragment : Fragment() {
    private var _binding: FragmentGifsBinding? = null
    private val binding get() = _binding!!

    lateinit var gifMenu : BottomNavigationView

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
        GIFS_ARE_SHOWN = true
        Log.d("GIFS_ARE_SHOWN", GIFS_ARE_SHOWN.toString())

        binding.bindAdapter(adapter = adapter)

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                items.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        return binding.root
    }



    public fun toHome() {
        Log.d("toHome()", "CALLED")
        val action = GifsFragmentDirections.actionGifsFragmentToHomeFragment()
        findNavController()?.navigate(action)
    }

}

private fun FragmentGifsBinding.bindAdapter(
    adapter: GifsPagingAdapter
) {
    rv.adapter = adapter
}