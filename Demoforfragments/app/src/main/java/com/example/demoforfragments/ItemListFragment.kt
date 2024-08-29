package com.example.demoforfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment


class ItemListFragment : Fragment() {
    private var listener: OnItemSelectedListener? = null

    interface OnItemSelectedListener {
        fun onItemSelected(item: String?)
    }

    fun setOnItemSelectedListener(listener: OnItemSelectedListener?) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_item_list, container, false)

        val listView = view.findViewById<ListView>(R.id.item_list)
        val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")

        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_list_item_1, items
        )
        listView.adapter = adapter

        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                if (listener != null) {
                    listener!!.onItemSelected(items[position])
                }
            }

        return view
    }
}