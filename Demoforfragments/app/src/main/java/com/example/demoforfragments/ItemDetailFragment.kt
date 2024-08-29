package com.example.demoforfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class ItemDetailFragment : Fragment() {
    private var itemDetailTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_item_detail, container, false)
        itemDetailTextView = view.findViewById<TextView>(R.id.item_detail)
        return view
    }

    fun setItemDetail(itemDetail: String?) {
        if (itemDetailTextView != null) {
            itemDetailTextView!!.text = itemDetail
        }
    }
}