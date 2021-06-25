package com.udacity.shoestore.models.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData<String>()
    val size: MutableLiveData<Double> = MutableLiveData<Double>()
    val company: MutableLiveData<String> = MutableLiveData<String>()
    val description: MutableLiveData<String> = MutableLiveData<String>()
    var nextShoe: MutableLiveData<Shoe> = MutableLiveData<Shoe>()
    val listOfShoes = mutableListOf<Shoe>()

init {
    name.value = "test"
    size.value = 0.5
    company.value = "Nike"
    description.value = "comfortable"
}


/*
    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description
*/



}
