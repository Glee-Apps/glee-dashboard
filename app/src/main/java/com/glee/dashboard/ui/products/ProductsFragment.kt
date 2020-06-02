package com.glee.dashboard.ui.products

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.glee.dashboard.R
import com.glee.dashboard.adapter.ProductAdapter
import com.glee.dashboard.model.Product
import com.glee.dashboard.model.ProductsWithImages
import com.glee.dashboard.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment() {

    private lateinit var orderViewModel: OrderViewModel
    private var mAdapter: ProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_products, container, false)

        val toolbar = root.findViewById<Toolbar>(R.id.topAppBar)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        orderViewModel =
            ViewModelProviders.of(this).get(OrderViewModel::class.java)

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

        orderViewModel.getProducts()
            ?.observe(this, Observer { products ->

                recyclerView.adapter = mAdapter
                recyclerView.layoutManager = LinearLayoutManager(context)
                loadResults(products.toMutableList())

                (activity as AppCompatActivity?)!!.supportActionBar!!.subtitle =
                    "${"%,d".format(products.size)} products available"
            })

        return root
    }

    private fun loadResults(results: MutableList<ProductsWithImages>) {
        if (mAdapter == null) {
            mAdapter = context?.let {
                ProductAdapter(it, results)
            }
            recyclerView.adapter = mAdapter
        } else mAdapter?.updateData(results)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.orders_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
