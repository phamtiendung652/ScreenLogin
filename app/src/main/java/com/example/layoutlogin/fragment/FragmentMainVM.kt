package com.example.layoutlogin.fragment

import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentMainVM : ViewModel() {
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)
    var error = MutableLiveData("");
//    lateinit var dataEmail: MutableLiveData<String>
//    lateinit var dataPass: MutableLiveData<String>
//    fun setEmail(data : String){
//         dataEmail.value = data
//    }
//    fun setPass(data: String){
//        dataPass.value = data
//    }
//    fun getEmail():LiveData<String>{
//        return dataEmail
//    }
//    fun getPass():LiveData<String>{
//        return dataPass
//    }

     fun checkIsEmpty(edtE: String?, edtP: String?){
        if(edtE.isNullOrBlank()|| edtP.isNullOrBlank()){
            error.postValue("Email or Password is empty")
        }else {
            loginDelay()
        }
    }
    fun loginDelay(){
        viewModelScope.launch() {
            loading.postValue(true);
            for (i in 1..50){
                Log.i("loginDelay","loginDelay $i")
                delay(200)
            }
            Log.i("loginDelay","loginDelay $loading")
            loading.postValue(false);
        }
    }
}