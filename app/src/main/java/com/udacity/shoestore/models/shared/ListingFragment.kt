package com.udacity.shoestore.models.shared

import android.app.Activity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding.inflate
import com.udacity.shoestore.databinding.ListingFragmentBinding
import kotlinx.android.synthetic.main.common_list.view.*
import kotlinx.android.synthetic.main.common_list2.view.*
import kotlin.system.exitProcess


class ListingFragment : Fragment() {

    private lateinit var binding: ListingFragmentBinding
    private lateinit var lastView: View

    // as suggested, using "activityViewModels() so that listingFragment and detailFragment can transfer data
    private val model: SharedViewModel by activityViewModels()

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
        // https://stackoverflow.com/questions/54893039/get-actionbar-inside-fragment-using-androidx
        // This gets and hides the "up button" on the action bar so that user cannot navigate back:
        val bar = (activity as AppCompatActivity).supportActionBar
        bar?.setDisplayHomeAsUpEnabled(false)
        binding.setLifecycleOwner(this)
        // The code below causes app to exit upon back button press, obtained from:
        // https://stackoverflow.com/questions/55074497/how-to-add-onbackpressedcallback-to-fragment
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    (activity as AppCompatActivity).finish()
                    // can also consider finishAffinity() or System.exit(0)
                }
            }
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()

      //  val inflater: LayoutInflater = LayoutInflater.from(context)
        val inflater: LayoutInflater = getLayoutInflater()
        val view: View = inflater.inflate(R.layout.common_list2, null)
        val main: ViewGroup = binding.insertionPoint

        /* NEWLY DELETED:
        (view as? ViewGroup)?.let {
            lastView = View.inflate(context, R.layout.common_list2, it)
        }

 */
        fun addField(text: String, height: Int = 100, width: Int = 1000) {
            var textView: TextView = TextView(context)
            textView.text = text
            textView.height = height
            textView.width = width
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
            main.addView(textView)
        }

        // spacer for top navbar:
        addField("")
        // iterate over all the shoes stored in the model and print info to Listing View:
        for (shoe in model.listOfShoes) {
            // Name field:
            addField("Name: " + shoe.name)
            // Size field:
            addField("Size: " + shoe.size.toString())
            // Company field:
            addField("Company: " + shoe.company.toString())
            // Description field:
            addField("Description: " + shoe.description.toString())
            // spacer between Shoes in list:
            addField("")
        }
        // setup floatingactionbutton migration:
        val actionButton = binding.fab
        actionButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }

/* NEWLY DELETED:
        var actionButton: FloatingActionButton = FloatingActionButton(context)
        actionButton.setImageResource(R.drawable.ic_add)
        lastView.myLinearLayout.addView(actionButton)

        actionButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }
*/
        }

        // Create options menu:
        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            super.onCreateOptionsMenu(menu, inflater)
            inflater.inflate(R.menu.logout_menu, menu)
        }

        // When the option is chosen migrate to the Login Fragment
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.logout -> moveBack()
            }
            return super.onOptionsItemSelected(item)
        }

        // migrates the user back to the Login Fragment
        fun moveBack() {
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToLoginFragment())
        }
    }








