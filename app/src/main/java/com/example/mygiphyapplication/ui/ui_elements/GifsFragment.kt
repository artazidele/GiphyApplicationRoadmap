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
import androidx.navigation.NavController
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

        setHasOptionsMenu(true)
        val navigationView = binding.bottomNav

        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Log.d("NAVIGATION", "home")
//                    Log.d("GIFS_ARE_SHOWN", "false")
                    if (GIFS_ARE_SHOWN) {

                        val action = GifsFragmentDirections.actionGifsFragmentToHomeFragment()
                        findNavController().navigate(action)
                    }
//                    toHome()
                    true
                }
                R.id.help -> {
                    Log.d("NAVIGATION", "help")
                    showHelp()
                    true
                }
                R.id.settings -> {
                    Log.d("NAVIGATION", "settings")
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                val action = GifsFragmentDirections.actionGifsFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    public fun toHome() {
        val action = GifsFragmentDirections.actionGifsFragmentToHomeFragment()
//        view?.post{
//            Log.d("toHome()", "CALLED")
//            findNavController().navigate(action)
//        }
        findNavControllerSafely()?.navigate(action)

    }

    fun Fragment.findNavControllerSafely(): NavController? {
        return if (isAdded) {
            findNavController()
        } else {
            null
        }
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