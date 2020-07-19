package com.yourssu.anywherelibrary.presentation.library_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.yourssu.anywherelibrary.R

class LibraryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val navController = findNavController(R.id.library_nav_host_fragment)

        navController.setGraph(R.navigation.library_navigation)
    }
}