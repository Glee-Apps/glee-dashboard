package com.glee.dashboard.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.glee.dashboard.R

class OrderFragment : Fragment() {

    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        orderViewModel =
                ViewModelProviders.of(this).get(OrderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        orderViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
