package com.udacity.shoestore.models.shared

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseMethod
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class SharedViewModel : ViewModel() {
    var shoeName: MutableLiveData<String> = MutableLiveData<String>()
    var shoeSize: MutableLiveData<Double> = MutableLiveData<Double>()
    var shoeCompany: MutableLiveData<String> = MutableLiveData<String>()
    var shoeDescription: MutableLiveData<String> = MutableLiveData<String>()
    val listOfShoes = mutableListOf<Shoe>()


init {
    // needs this not to crash!
    shoeSize.value = 0.0
}



// takes bound data, incorporates it into a new Shoe object, then stores it in a Mutable List
fun addShoe() {
    var newShoe = Shoe(shoeName.value.toString(), shoeSize.value.toString().toDoubleOrNull() ?: 0.0 as Double, shoeCompany.value.toString(), shoeDescription.value.toString())
    listOfShoes.add(newShoe)
    wipeVariablesClean()
}
// prevents old data from being displayed in the detail view
    fun wipeVariablesClean() {
        shoeName.value = ""
        shoeSize.value = 0.0
        shoeCompany.value = ""
        shoeDescription.value = ""
    }
}

