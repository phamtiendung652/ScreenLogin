package com.example.layoutlogin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layoutlogin.databinding.ActivityMainBinding
import com.example.layoutlogin.fragment.FragmentMain

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val frm = FragmentMain()
        supportFragmentManager.beginTransaction().replace(R.id.frmMain,frm).commit()


    }
}