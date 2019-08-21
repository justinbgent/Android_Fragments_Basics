package com.lambdaschool.congressfragmentsproject

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.congressfragmentsproject.adapter.MyCongresspersonOverviewRecyclerViewAdapter
import com.lambdaschool.congressfragmentsproject.api.CongressDao
import com.lambdaschool.congressfragmentsproject.api.CongresspersonDetails
import com.lambdaschool.congressfragmentsproject.api.CongresspersonOverview
import com.lambdaschool.congressfragmentsproject.fragments.CongresspersonOverviewFragment
import com.lambdaschool.congressfragmentsproject.fragments.DetailFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_congresspersonoverview_list.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), CongresspersonOverviewFragment.OnListFragmentInteractionListener {

    companion object{
        const val PERSON_KEY = "CONGRESS_MAN"
    }
    override fun onFragmentInteraction(item: CongresspersonOverview?) {
        Toast.makeText(this, "Fragment Interacted ${item?.firstName}", Toast.LENGTH_SHORT).show()

        val fragment = DetailFragment()

        val bundle = Bundle()
        bundle.putSerializable(PERSON_KEY, item)

        fragment.arguments = bundle

        if (fragment_secondary == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        else{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_secondary, fragment)
                .commit()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get an overview list for all members of congress
        val allMembers: ArrayList<CongresspersonOverview> = CongressDao.allMembers

        // get details for a single member of congress
        val singleMemberDetails: CongresspersonDetails? = allMembers[0].id?.let { CongressDao.getMemberDetails(it) }

        // get congressperson portrait
        val image: Bitmap? = allMembers[0].id?.let { CongressDao.getImage(it) }

//        val fragment = CongresspersonOverviewFragment as Fragment
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_fragment, fragment)
//            .commit()

//        list.setHasFixedSize(true)
//        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        list.layoutManager = layoutManager
//        val personListAdapter = MyCongresspersonOverviewRecyclerViewAdapter(CongressDao.allMembers)
//        list.adapter = personListAdapter
    }
}
