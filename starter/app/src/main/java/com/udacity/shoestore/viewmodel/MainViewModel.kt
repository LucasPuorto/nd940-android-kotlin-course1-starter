package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    private val _shoeListMutableLiveData = MutableLiveData<MutableList<Shoe>>()
    val shoeListLiveData: LiveData<MutableList<Shoe>> get() = _shoeListMutableLiveData

    private val _cancelMutableLiveData = MutableLiveData<Boolean>()
    val cancelLiveData: LiveData<Boolean> get() = _cancelMutableLiveData

    private val _addShoeMutableLiveData = MutableLiveData<Boolean>()
    val addShoeLiveData: LiveData<Boolean> get() = _addShoeMutableLiveData

    var shoeName = MutableLiveData<String>()
    var shoeCompany = MutableLiveData<String>()
    var shoeSize = MutableLiveData<String>()
    var shoeDescription = MutableLiveData<String>()

    private val shoeList = mutableListOf<Shoe>()

    init {
        _cancelMutableLiveData.value = false
        _addShoeMutableLiveData.value = false
    }

    fun addShoe() {
        if (!shoeName.value.isNullOrBlank() && !shoeCompany.value.isNullOrBlank() && !shoeSize.value.isNullOrBlank() && !shoeDescription.value.isNullOrBlank()) {
            val shoe = Shoe(
                name = shoeName.value!!,
                size = shoeSize.value!!.toDouble(),
                company = shoeCompany.value!!,
                description = shoeDescription.value!!
            )
            shoeList.add(shoe)
            _shoeListMutableLiveData.postValue(shoeList)
            _addShoeMutableLiveData.value = true
        }
    }

    fun onCancel() {
        _cancelMutableLiveData.value = true
    }

    fun onAddShoeComplete() {
        _addShoeMutableLiveData.value = false
        shoeName = MutableLiveData<String>()
        shoeCompany = MutableLiveData<String>()
        shoeSize = MutableLiveData<String>()
        shoeDescription = MutableLiveData<String>()
    }

    fun onCancelEventComplete() {
        _cancelMutableLiveData.value = false
    }
}