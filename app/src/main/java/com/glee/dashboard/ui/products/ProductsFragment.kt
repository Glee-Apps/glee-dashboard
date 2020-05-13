package com.glee.dashboard.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.glee.dashboard.R

class ProductsFragment : Fragment() {

    private lateinit var productsViewModel: ProductsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        productsViewModel =
                ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_products, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        productsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
