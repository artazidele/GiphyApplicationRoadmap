package com.example.mygiphyapplication.ui.ui_elements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mygiphyapplication.databinding.FragmentSettingsBinding

var FROM_GIFS = 0

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.closeBtn.setOnClickListener {
            if (FROM_GIFS == 0) {
                val action = SettingsFragmentDirections.actionSettingsFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                val action = SettingsFragmentDirections.actionSettingsFragmentToGifsFragment()
                findNavController().navigate(action)
            }
        }

        return binding.root
    }
}