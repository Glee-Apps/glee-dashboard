package com.glee.dashboard.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.glee.dashboard.R
import com.glee.dashboard.model.Image

class ImageAdapter(
    val mContext: Context,
    var mList: MutableList<Image>
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    var listener: GalleryImageClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById(R.id.imageViewGallery) as ImageView
        var container = itemView.findViewById(R.id.container) as com.glee.dashboard.SquareLayout
        fun bind() {
            container.setOnClickListener {
                listener?.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_image, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        val item = mList[position]
        with(holder) {
            if (item.url != "null") {
                image.load(item.url)
            }
        }
    }

    fun updateData(list: MutableList<Image>) {
        mList = list
        notifyDataSetChanged()
    }
}