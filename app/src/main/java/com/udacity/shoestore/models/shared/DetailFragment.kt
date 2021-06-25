package com.udacity.shoestore.models.shared

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.DetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding
   // private lateinit var sharedBinding: NewViewBinding
  //  private lateinit var viewModel: SharedViewModel
    private val model: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )
       /* sharedBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.new_view,
            container,
        false
        )  */
     //   binding.sharedViewModel = viewModel
        binding.cancelButton.setOnClickListener {
            view: View -> view.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
        }
        binding.saveButton.setOnClickListener {
            view: View -> view.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
            saveNewData()
        }
        return binding.root
    }

    fun saveNewData() {
        model.name.value = binding.shoeNameText.text.toString()
        Log.i("onStart", model.name.value.toString())
        model.size.value = binding.shoeSizeText.text.toString().toDoubleOrNull()
        model.company.value = binding.companyText.text.toString()
        model.description.value = binding.descriptionText.text.toString()
        val nextShoe = Shoe(binding.shoeNameText.text.toString(),
            (binding.shoeSizeText.text.toString().toDoubleOrNull() ?: 0.0) as Double, binding.companyText.text.toString(), binding.descriptionText.text.toString())
        saveShoe(nextShoe)
        // view: View -> view.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
    }

    fun saveShoe(shoe: Shoe) {
        model.listOfShoes.add(shoe)
    }
}