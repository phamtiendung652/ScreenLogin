package com.example.layoutlogin.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.layoutlogin.databinding.FragmentMainBinding
import kotlin.time.Duration


class FragmentMain : Fragment() {

    private val viewModel:FragmentMainVM by viewModels()
    private  var binding :FragmentMainBinding?=null
    lateinit var sharedPref:SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        val root = binding!!.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            it.btnLogin.setOnClickListener {_ ->
                viewModel.
                checkIsEmpty(it.edtEmail.text.toString(),it.edtPass.text.toString())
            }
            it.btnBack.setOnClickListener {
                ShowDialog()
            }

        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (!it) {
                val text = "Login Success"
                val tost = Toast.makeText(context, text, Toast.LENGTH_SHORT)
                tost.show()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val tost = Toast.makeText(context, it, Toast.LENGTH_SHORT)
                tost.show()
                viewModel.error.postValue("")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }
    private fun saveData() {
        sharedPref = activity?.getSharedPreferences("saveData",Context.MODE_PRIVATE)!!
        var mailT: String? = null
        var passT:String? = null

        binding?.let {
            mailT = it.edtEmail.text.toString()
            passT = it.edtPass.text.toString()

        }
        val editor = sharedPref.edit()
        editor.putString("_mail",mailT)
        editor.putString("_pass",passT)
        editor.apply()
        Toast.makeText(context,"Data đã được lưu",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        sharedPref = activity?.getSharedPreferences("saveData",Context.MODE_PRIVATE)!!
        var mailT: String? = null
        var passT:String? = null

        mailT = sharedPref.getString("_mail",null)
        passT = sharedPref.getString("_pass",null)

        binding?.let {
            it.edtEmail.setText(mailT)
            it.edtPass.setText(passT)
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding =null
    }


    private fun ShowDialog(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("EXIT")
            .setMessage("Do you want to exit ?")
            .setCancelable(true)
            .setPositiveButton("Yes") { _, _ -> activity?.finish() }
            .setNegativeButton("No",DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            .show()
    }
}