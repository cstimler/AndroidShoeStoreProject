package com.udacity.shoestore.models.shared

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingFragmentBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.common_list.view.*
import kotlinx.android.synthetic.main.floating_button.view.*
import java.util.*


class ListingFragment : Fragment() {

    private lateinit var binding: ListingFragmentBinding
    private lateinit var lastView: View
    private lateinit var buttonView: View
    private lateinit var nextTextView: TextView
    //private lateinit var newBinding:
   // private lateinit var sharedBinding: NewViewBinding
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
        //setDisplayHomeAsUpEnabled(false)
        //toolbar.navigationIcon(null)
        Log.i("onLinearLayout", "insideCreateView")
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
       // binding.myTextView.text = model.name.value.toString()

        // add some stuff:
     //   newBinding.newField.text = "Testing, Testing"
        // end of some stuff:
        return binding.root
    }



    override fun onResume() {
        super.onResume()
        /*
        binding.myTextView.text = model.name.value.toString()
        binding.mySizeView.text = model.size.value.toString()
        binding.myCompanyView.text = model.company.value.toString()
        binding.myDescriptionView.text = model.description.value.toString()
        Log.i("onStart", model.name.value.toString())
        */
        val ll: LinearLayout = LinearLayout(this.context)
        val lmain: LinearLayout = LinearLayout(this.context)
        ll.setLayoutParams(
            ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
        // LayoutInflater.from(this.context).inflate(R.layout.common_list, null)

      //  val myLayout = LayoutInflater.from(context).inflate(R.layout.common_list, null)
        /*
        val tv: TextView = TextView(this.context)
        tv.setText("Testing, Testing")
        tv.height=100
        tv.width=100
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
        getView()?.invalidate()
        ll.addView(tv)
        lmain.addView(ll)
        myLayout.new_field.text = "TESTING, TESTING"
        */
        (view as? ViewGroup)?.let {
                lastView = View.inflate(context, R.layout.common_list2, it)
          //  Log.i("onResume", myLayout.myLinearLayout.toString())
            // lastView.new_field.text="Charles Stimler"
            var textView:TextView = TextView(context)
            textView.text = "Charlie"
            textView.height = 100
            textView.width = 100
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)
          //  getView()?.invalidate()
            lastView.myLinearLayout.addView(textView)

            //scroll.setBackgroundColor(getResources().getColor(android.R.color.transparent, null)
           // if (lastView.getParent() == null) {
            //    scroll.addView(lastView)
          //  }
            // setContentView(ll)
        }
        (view as? ViewGroup)?.let {
           buttonView = View.inflate(context, R.layout.floating_button, it)
        }
        val scroll: ScrollView = ScrollView(context)
        scroll.setLayoutParams(
            ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        )

        for (shoe in model.listOfShoes) {
            var textView:TextView = TextView(context)
            textView.text = shoe.name
            textView.height = 1000
            textView.width = 1000
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
            //  getView()?.invalidate()
            lastView.myLinearLayout.addView(textView)
        }
        var actionButton: FloatingActionButton = FloatingActionButton(context)
        lastView.myLinearLayout.addView(actionButton)
        buttonView.fab2.setOnClickListener {
                view: View -> view.findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }
       actionButton.setOnClickListener {
                view: View -> view.findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }
        /*
        if(lastView.getParent() != null) {
                if (lastView.getParent() is ViewGroup) {
            (lastView.getParent()).removeView(lastView)
                }
        }
        */

    }

    fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
        container.removeView(`object` as View?)
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

    fun makeShoe() {
      var nextShoe = Shoe(model.name.value!!, model.size.value!!, model.company.value!!, model.description.value!! )
      //  binding.myTextView.setText(model.name.value!!)
    }

}


