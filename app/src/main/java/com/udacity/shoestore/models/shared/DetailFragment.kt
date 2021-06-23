package com.udacity.shoestore.models.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding

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
        binding.cancelButton.setOnClickListener {
            view: View -> view.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
        }
        binding.saveButton.setOnClickListener {
            view: View -> view.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
            saveNewShoe()
        }
        return binding.root
    }

    fun saveNewShoe() {
        // view: View -> view.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
    }
}