package com.udacity.shoestore.models.shared

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingFragmentBinding

class ListingFragment : Fragment() {

    private lateinit var binding: ListingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.listing_fragment,
            container,
            false
        )
        setHasOptionsMenu(true)
        //setDisplayHomeAsUpEnabled(false)
        //toolbar.navigationIcon(null)
        Log.i("GetActivity", "checking log")
        // getActivity()?.getActionBar()?.setDisplayHomeAsUpEnabled(false)
        // ((ActionBarActivity)getActivity()).getSupportActionBar()
        // https://stackoverflow.com/questions/54893039/get-actionbar-inside-fragment-using-androidx
        // This gets and hides the "up button" on the action bar so that user cannot navigate back:
        val bar = (activity as AppCompatActivity).supportActionBar
        bar?.setDisplayHomeAsUpEnabled(false)
        if (bar == null) {
            Log.i("GetActivity", "bar is null")
        }
        binding.fab.setOnClickListener {
            view: View -> view.findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
        }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when (item.itemId) {
            R.id.logout -> moveBack()
        }
        return super.onOptionsItemSelected(item)
    }

    fun moveBack() {
                findNavController().navigate(ListingFragmentDirections.actionListingFragmentToLoginFragment())
    }

}


