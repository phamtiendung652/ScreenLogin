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


class FragmentMain : Fragment() {

    private val viewModel:FragmentMainVM by viewModels()
    private  var binding :FragmentMainBinding?=null
    lateinit var sharedPref:SharedPreferences

    var mailT: String? = null
    var passT:String? = null


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
            it.btnLogin.setOnClickListener {
                binding?.let {
                    CheckIsEmpty(it.edtEmail,it.edtPass)
                }
            }
            it.btnBack.setOnClickListener {
                ShowDialog()
            }

        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }
    private fun saveData() {
        sharedPref = activity?.getSharedPreferences("saveData",Context.MODE_PRIVATE)!!
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
     private fun CheckIsEmpty(edtE:EditText,edtP:EditText){
         val duration = Toast.LENGTH_SHORT
         if(edtE.text.isNullOrBlank()|| edtP.text.isNullOrBlank()){
             val text = "Email or Password is empty"
             val tost = Toast.makeText(context,text, duration)
             tost.show()
         }else {
             val text = "Login Success"
             val tost = Toast.makeText(context,text, duration)
             //tost.show()
             viewModel.loginDelay(tost)
         }
     }

    private fun ShowDialog(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("EXIT")
            .setMessage("Do you want to exit ?")
            .setCancelable(true)
            .setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which -> activity?.finish() })
            .setNegativeButton("No",DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            .show()
    }
}