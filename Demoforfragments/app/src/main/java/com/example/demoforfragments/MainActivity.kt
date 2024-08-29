package com.example.demoforfragments


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ItemListFragment.OnItemSelectedListener {
    private var isTwoPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager

        if (findViewById<View?>(R.id.detail_container) != null) {
            // We are in two-pane layout (tablet)
            isTwoPane = true
           Log.d("MainActivty","In two plane layout")
            val itemListFragment: ItemListFragment = ItemListFragment()
            itemListFragment.setOnItemSelectedListener(this)

            fragmentManager.beginTransaction()
                .replace(R.id.list_container, itemListFragment)
                .commit()

            val itemDetailFragment: ItemDetailFragment = ItemDetailFragment()
            fragmentManager.beginTransaction()
                .replace(R.id.detail_container, itemDetailFragment)
                .commit()
        } else {
            Log.d("MainActivty","In single plane layout")
            // We are in single-pane layout (phone)
            val itemListFragment: ItemListFragment = ItemListFragment()
            itemListFragment.setOnItemSelectedListener(this)

            fragmentManager.beginTransaction()
                .replace(R.id.list_container, itemListFragment)
                .commit()
        }
    }

    override fun onItemSelected(item: String?) {
        if (isTwoPane) {
            // Update detail fragment in two-pane layout
            val detailFragment: ItemDetailFragment? =
                supportFragmentManager.findFragmentById(R.id.detail_container) as ItemDetailFragment?
            if (detailFragment != null) {
                detailFragment.setItemDetail(item)
            }
        } else {
            // Replace list fragment with detail fragment in single-pane layout
            val detailFragment: ItemDetailFragment = ItemDetailFragment()

            val args = Bundle()
            args.putString("item_detail", item)
            detailFragment.setArguments(args)

            supportFragmentManager.beginTransaction()
                .replace(R.id.list_container, detailFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}