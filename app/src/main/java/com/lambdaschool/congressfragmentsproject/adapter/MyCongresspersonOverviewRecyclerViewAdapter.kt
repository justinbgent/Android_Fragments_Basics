package com.lambdaschool.congressfragmentsproject.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.lambdaschool.congressfragmentsproject.R
import com.lambdaschool.congressfragmentsproject.api.CongresspersonOverview


import com.lambdaschool.congressfragmentsproject.fragments.CongresspersonOverviewFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_congresspersonoverview.view.*

class MyCongresspersonOverviewRecyclerViewAdapter(
    private val congressPersons: List<CongresspersonOverview>,
    private val clickListener: OnListFragmentInteractionListener? = null
) : RecyclerView.Adapter<MyCongresspersonOverviewRecyclerViewAdapter.ViewHolder>() {

//    private val mOnClickListener: View.OnClickListener
//
//    init {
//        mOnClickListener = View.OnClickListener { v ->
//            val item = v.tag as CongresspersonOverview
//            // Notify the active callbacks interface (the activity, if the fragment is attached to
//            // one) that an item has been selected.
//            clickListener?.onFragmentInteraction(item)
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_congresspersonoverview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val congressPerson = congressPersons[position]
        holder.congressTitle.text = congressPerson.title
        holder.congressPersonsName.text = "${congressPerson.lastName}, ${congressPerson.firstName}"

//        with(holder.view) {
//            tag = congressPerson
//            setOnClickListener(mOnClickListener)
//        }
        holder.llayout.setOnClickListener {

            clickListener?.onFragmentInteraction(congressPerson)

        }

    }

    override fun getItemCount(): Int = congressPersons.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val congressTitle = view.title_view
        val congressPersonsName = view.name_view
        val llayout = view.llayout
    }
}
