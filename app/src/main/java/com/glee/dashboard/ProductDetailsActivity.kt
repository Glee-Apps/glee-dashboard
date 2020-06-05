package com.glee.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.glee.dashboard.adapter.ImageAdapter
import com.glee.dashboard.model.Image
import com.glee.dashboard.model.Product
import com.glee.dashboard.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var orderViewModel: OrderViewModel

    private var mAdapter: ImageAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setSupportActionBar(toolbar)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val product: Product = intent.getParcelableExtra("product")!!

        supportActionBar!!.title = "${product.name}"


        imagesRV.adapter = mAdapter

        imagesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()

        snapHelper.attachToRecyclerView(imagesRV)

        orderViewModel =
            ViewModelProviders.of(this).get(OrderViewModel::class.java)

        orderViewModel.getProductImages(product.id)
            ?.observe(this, Observer { r ->
                loadImages(r.toMutableList())
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadImages(results: MutableList<Image>) {
        if (mAdapter == null) {
            mAdapter = this.let {
                ImageAdapter(this, results)
            }
            imagesRV.adapter = mAdapter
        } else mAdapter?.updateData(results)
    }

}
