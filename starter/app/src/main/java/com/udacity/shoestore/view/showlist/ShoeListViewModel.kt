package com.udacity.shoestore.view.showlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<Boolean>()
    val shoeList: LiveData<Boolean> get() = _shoeList
}