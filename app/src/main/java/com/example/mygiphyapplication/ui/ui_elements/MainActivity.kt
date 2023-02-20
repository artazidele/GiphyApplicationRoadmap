package com.example.mygiphyapplication.ui.ui_elements

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mygiphyapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

var QUERY = ""
var GIFS_ARE_SHOWN = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
//
//        navigationView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.home -> {
//                    Log.d("NAVIGATION", "home")
////                    Log.d("GIFS_ARE_SHOWN", "false")
//                    if (GIFS_ARE_SHOWN) {
//
////                        val action = GifsFragmentDirections.actionGifsFragmentToHomeFragment()
////                        findNavController(R.id.gifsFragment).navigate(action)
//                    }
////                    toHome()
//                    true
//                }
//                R.id.help -> {
//                    Log.d("NAVIGATION", "help")
//                    showHelp()
//                    true
//                }
//                R.id.settings -> {
//                    Log.d("NAVIGATION", "settings")
//                    showSettings()
//                    true
//                }
//                else -> {
//                    true
//                }
//            }
//        }
    }

    private fun toHome() {
        if (GIFS_ARE_SHOWN) {

//        if (GifsFragment().isVisible) {
            Log.d("GIFS_ARE_SHOWN", "GIFS_ARE_SHOWN")
            GifsFragment().toHome()
//            val action = GifsFragmentDirections.actionGifsFragmentToHomeFragment()
//            this.supportFragmentManager.findNavController(R.id.bottomNav).navigate(action)
        }

    }

    public fun showSettings() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.settings_window, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.close_btn).setOnClickListener {
            alertDialog.dismiss()
        }
    }

    public fun showHelp() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.help_window, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.close_btn).setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
