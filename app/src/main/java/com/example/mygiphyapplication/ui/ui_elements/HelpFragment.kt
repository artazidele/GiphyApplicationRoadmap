package com.example.mygiphyapplication.ui.ui_elements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mygiphyapplication.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)

        binding.closeBtn.setOnClickListener {
            if (FROM_GIFS == 0) {
                val action = HelpFragmentDirections.actionHelpFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                val action = HelpFragmentDirections.actionHelpFragmentToGifsFragment()
                findNavController().navigate(action)
            }
        }

        return binding.root
    }
}