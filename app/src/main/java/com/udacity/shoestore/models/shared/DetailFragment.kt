package com.udacity.shoestore.models.shared

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.DetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding
    // as suggested, using "activityViewModels() so that listingFragment and detailFragment can transfer data
    private val model: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Do binding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )
        //NEW Set up two-way data binding:
        binding.sharedViewModel = model

        // Set ClickListeners:
        binding.cancelButton.setOnClickListener {
            view: View -> view.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
        }
        binding.saveButton.setOnClickListener {
            view: View -> view.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
            // this function saves shoe data to the SharedViewModel
            saveNewData()
        }
        return binding.root
    }

    // This function transfers shoe data to a shoe variable:
    fun saveNewData() {
          model.addShoe()
    }
}