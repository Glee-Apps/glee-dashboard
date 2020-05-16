package com.glee.dashboard.ui.orders

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.glee.dashboard.R
import com.glee.dashboard.viewmodel.OrderViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OrderFragment : Fragment() {

    private lateinit var orderViewModel: OrderViewModel
    private lateinit var tabs: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter


    private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)

        val toolbar = root.findViewById<Toolbar>(R.id.topAppBar)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        tabs = root.findViewById(R.id.tabs)

        sectionsPagerAdapter =
            SectionsPagerAdapter(this)

        viewPager = root.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = context!!.resources.getString(TAB_TITLES[position])
            viewPager.setCurrentItem(tab.position, true)
        }.attach()

        orderViewModel =
            ViewModelProviders.of(this).get(OrderViewModel::class.java)

        orderViewModel.getOrders()
            ?.observe(this, Observer { orders ->
                val totalAmountList = mutableListOf<Int>()
                orders.forEach {
                    orderViewModel.getProducts()
                        ?.observe(this, Observer { products ->
                            products.forEach { r ->
                                if (it.productId == r.product!!.id && totalAmountList.size < orders.size) {
                                    val amount = it.quantity * r.product!!.cost
                                    totalAmountList.add(amount)
                                }

                                (activity as AppCompatActivity?)!!.supportActionBar!!.subtitle =
                                    "Total Ksh.${"%,d".format(totalAmountList.sum())}"
                            }
                        })
                }
            })

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.orders_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
