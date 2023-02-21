package com.example.layoutlogin.fragment

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentMainVM : ViewModel() {
    lateinit var dataEmail: MutableLiveData<String>
    lateinit var dataPass: MutableLiveData<String>
    fun setEmail(data : String){
         dataEmail.value = data
    }
    fun setPass(data: String){
        dataPass.value = data
    }
    fun getEmail():LiveData<String>{
        return dataEmail
    }
    fun getPass():LiveData<String>{
        return dataPass
    }

    fun loginDelay(showToast:Toast){
        viewModelScope.launch() {
            for (i in 1..10){
                delay(200)
                if(i == 10){
                    showToast.show()
                }
            }
        }
    }
}