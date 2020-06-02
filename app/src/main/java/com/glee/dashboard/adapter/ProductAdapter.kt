package com.glee.dashboard.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.glee.dashboard.R
import com.glee.dashboard.model.Product
import com.glee.dashboard.model.ProductsWithImages

class ProductAdapter(
    val mContext: Context,
    var mList: MutableList<ProductsWithImages>
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById(R.id.image) as ImageView
        var productName = itemView.findViewById(R.id.productName) as TextView
        var productQuantity = itemView.findViewById(R.id.productQuantity) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        with(holder) {
            productName.text = item.product!!.name
            productQuantity.text = "${item.product!!.quantity} pieces remaining"
            if (item.images!!.isNotEmpty()) {
                image.load(item.images!![0].url)
            }
        }
    }

    fun updateData(list: MutableList<ProductsWithImages>) {
        mList = list
        notifyDataSetChanged()
    }

}