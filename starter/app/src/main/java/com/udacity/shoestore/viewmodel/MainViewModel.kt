package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<Boolean>()
    val shoeList: LiveData<Boolean> get() = _shoeList
}