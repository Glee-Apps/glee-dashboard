package com.glee.dashboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import coil.api.load
import com.glee.dashboard.model.Image
import kotlinx.android.synthetic.main.image_fullscreen.view.*

class GalleryFullscreenFragment : DialogFragment() {
    private var imageList = ArrayList<Image>()
    private var selectedPosition: Int = 0
    lateinit var tvGalleryTitle: TextView
    lateinit var viewPager: ViewPager
    lateinit var galleryPagerAdapter: GalleryPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery_fullscreen, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        tvGalleryTitle = view.findViewById(R.id.tvGalleryTitle)
        galleryPagerAdapter = GalleryPagerAdapter()
        imageList = arguments?.getSerializable("images") as ArrayList<Image>
        selectedPosition = arguments!!.getInt("position")
        viewPager.adapter = galleryPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        setCurrentItem(selectedPosition)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    private fun setCurrentItem(position: Int) {
        viewPager.setCurrentItem(position, false)
    }

    // viewpager page change listener
    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                // set gallery title
                tvGalleryTitle.text = imageList[position].id.toString()
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
            }

            override fun onPageScrollStateChanged(arg0: Int) {
            }
        }

    // gallery adapter
    inner class GalleryPagerAdapter : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.image_fullscreen, container, false)
            val image = imageList[position]
            // load image
            view.ivFullscreenImage.load(image.url)
            return view
        }

        override fun getCount(): Int {
            return imageList.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj as View
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }
    }
}
