package com.glee.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.glee.dashboard.adapter.ImageAdapter
import com.glee.dashboard.model.Image
import com.glee.dashboard.model.Product
import com.glee.dashboard.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.content_product_details.*


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

        supportActionBar!!.title = "Product Details"


//        imagesRV.adapter = mAdapter
//
//        imagesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
//        val snapHelper = PagerSnapHelper()
//
//        snapHelper.attachToRecyclerView(imagesRV)

        orderViewModel =
            ViewModelProviders.of(this).get(OrderViewModel::class.java)

        orderViewModel.getProductImages(product.id)
            ?.observe(this, Observer { r ->
                loadImages(r.toMutableList())
            })

        rating.rating = 4.0F

        //Part1
        val entries = ArrayList<Entry>()

        //Part2
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 7f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 16f))

        //Part3
        val vl = LineDataSet(entries, "My Type")

        //Part4
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.colorPrimary
        vl.fillAlpha = R.color.colorAccent

        //Part5
        lineChart.xAxis.labelRotationAngle = 0f

        //Part6
        lineChart.data = LineData(vl)

        //Part7
        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.axisMaximum = 0 + 0.1f

        //Part8
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

        //Part9
        lineChart.description.text = "Days"
        lineChart.setNoDataText("No forex yet!")

        //Part10
        lineChart.animateX(1800, Easing.EaseInExpo)

        //Part11
        val markerView = CustomMarker(this, R.layout.marker_view)
        lineChart.marker = markerView

        seePhotos.setOnClickListener {
            val intent = Intent(this, ImageListActivity::class.java)
            startActivity(intent)
        }

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
//            imagesRV.adapter = mAdapter
        } else mAdapter?.updateData(results)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_product_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
