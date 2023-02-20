package com.example.mygiphyapplication.ui.ui_elements

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mygiphyapplication.R
import com.example.mygiphyapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.mainBtn.setOnClickListener {
            val searchObject = binding.mainEt.text.toString()
            if (searchObject == "") {
                showError()
            } else {
                QUERY = searchObject
                val action = HomeFragmentDirections.actionHomeFragmentToGifsFragment()
                findNavController().navigate(action)
            }
        }
        val navigationView = binding.bottomNav

        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
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

    private fun showError() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.settings_window, null)
        val textView = dialogView.findViewById<TextView>(R.id.settings_tv)
        textView.text = "Please enter what do You want to search!"
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.close_btn).setOnClickListener {
            alertDialog.dismiss()
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
