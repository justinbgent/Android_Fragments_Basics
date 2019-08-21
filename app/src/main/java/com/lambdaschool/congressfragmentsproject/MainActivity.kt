package com.lambdaschool.congressfragmentsproject

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.congressfragmentsproject.api.CongressDao
import com.lambdaschool.congressfragmentsproject.api.CongresspersonDetails
import com.lambdaschool.congressfragmentsproject.api.CongresspersonOverview
import com.lambdaschool.congressfragmentsproject.fragments.CongresspersonOverviewFragment
import com.lambdaschool.congressfragmentsproject.fragments.DetailFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), CongresspersonOverviewFragment.OnListFragmentInteractionListener {

    companion object{
        const val PERSON_KEY = "CONGRESS_MAN"
    }

    override fun onListFragmentInteraction(item: CongresspersonOverview?) {
//        item.apiUri
//        item.title
//        item.firstName
//        item.lastName
        val fragment = DetailFragment()

        val bundle = Bundle()
        bundle.putSerializable(PERSON_KEY, item)

        fragment.arguments = bundle

        if (fragment_secondary == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, fragment)
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

//        val fragment = CongresspersonOverviewFragment
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.llayout, CongresspersonOverviewFragment)
//            .commit()
    }
}
