package com.glee.dashboard.ui.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glee.dashboard.R
import com.glee.dashboard.adapter.OrderAdapter
import com.glee.dashboard.model.Order
import com.glee.dashboard.viewmodel.OrderViewModel

class OrderListFragment : Fragment() {

    private lateinit var orderViewModel: OrderViewModel
    private var mAdapter: OrderAdapter? = null
    private lateinit var orderRecyclerView: RecyclerView
    private var mutableListActive: MutableList<Order> = mutableListOf()
    private var mutableListPending: MutableList<Order> = mutableListOf()
    private var mutableListCompleted: MutableList<Order> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order_list, container, false)
        orderViewModel =
            ViewModelProviders.of(this).get(OrderViewModel::class.java)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {

            orderRecyclerView = view.findViewById(R.id.orderRecyclerView)

            val textViewListTitle = view.findViewById(R.id.textViewListTitle) as TextView

            orderViewModel.getOrdersOnline()
                .observe(context as FragmentActivity, Observer { r ->
                    r.request.forEach {
                        val order = it
                        orderViewModel.setOrder(order)
                    }

                    orderViewModel.getProductsOnline()
                        .observe(context as FragmentActivity, Observer { r ->

                            r.request.forEach {
                                val product = it
                                orderViewModel.setProduct(product)

                                it.images!!.forEach {
                                    val image = it
                                    orderViewModel.setImage(image)
                                }
                            }

                        })
                })


            orderViewModel.getOrders()
                ?.observe(context as FragmentActivity, Observer { orders ->

                    mutableListActive.clear()
                    mutableListPending.clear()
                    mutableListCompleted.clear()

                    orders.forEach {

                        when {
                            it.statusId == 1 && !mutableListActive.contains(it) -> mutableListActive.add(
                                it
                            )
                            it.statusId == 2 && !mutableListPending.contains(it) -> mutableListPending.add(
                                it
                            )
                            it.statusId == 3 && !mutableListCompleted.contains(it) -> mutableListCompleted.add(
                                it
                            )
                        }
                    }


                    orderRecyclerView.adapter = mAdapter
                    orderRecyclerView.layoutManager = LinearLayoutManager(context)

                    when (getInt(ARG_OBJECT)) {
                        1 -> {
                            if (mutableListActive.isNotEmpty()) {
                                textViewListTitle.text = "Pending Orders"
                            }

                            loadResults(mutableListActive)
                        }
                        2 -> {
                            if (mutableListPending.isNotEmpty()) {
                                textViewListTitle.text = "Picked up & Dropped off Orders"
                            }

                            loadResults(mutableListPending)
                        }
                        3 -> {

                            if (mutableListCompleted.isNotEmpty()) {
                                textViewListTitle.text = "Completed Orders"
                            }

                            loadResults(mutableListCompleted)
                        }
                    }

                })
        }
    }

    private fun loadResults(results: MutableList<Order>) {
        if (mAdapter == null) {
            mAdapter = context?.let {
                OrderAdapter(it, results)
            }
            orderRecyclerView.adapter = mAdapter
        } else mAdapter?.updateData(results)
    }
}

private const val ARG_OBJECT = "object"