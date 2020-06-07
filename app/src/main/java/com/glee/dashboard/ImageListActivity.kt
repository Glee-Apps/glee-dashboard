package com.glee.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.glee.dashboard.adapter.GalleryImageClickListener
import com.glee.dashboard.adapter.ImageAdapter
import com.glee.dashboard.model.Image
import kotlinx.android.synthetic.main.activity_image_list.*
import kotlinx.android.synthetic.main.activity_image_list.toolbar

class ImageListActivity : AppCompatActivity(), GalleryImageClickListener {

    private val SPAN_COUNT = 3
    private val imageList = ArrayList<Image>()
    lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        imageAdapter = ImageAdapter(this, imageList)
//        imageAdapter.listener = this
        // init recyclerview
        recyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        recyclerView.adapter = imageAdapter

        loadImages()

    }

    private fun loadImages() {
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/wBYDxLq/beach.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/gM5NNJX/butterfly.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/10fFGkZ/car-race.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/ygqHsHV/coffee-milk.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/7XqwsLw/fox.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/L1m1NxP/girl.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/wc9rSgw/desserts.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/wdrdpKC/kitten.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/dBCHzXQ/paris.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/JKB0KPk/pizza.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/VYYPZGk/salmon.jpg"))
        imageList.add(Image(0, "", "", 0, "https://i.ibb.co/JvWpzYC/sunset.jpg"))
        imageAdapter.notifyDataSetChanged()
    }


    override fun onClick(position: Int) {

        Toast.makeText(this, "$position", Toast.LENGTH_LONG).show()

        val bundle = Bundle()
        bundle.putSerializable("images", imageList)
        bundle.putInt("position", position)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val galleryFragment = GalleryFullscreenFragment()
        galleryFragment.arguments = bundle
        galleryFragment.show(fragmentTransaction, "gallery")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
