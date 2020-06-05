package com.glee.dashboard.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.glee.dashboard.ProductDetailsActivity
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
        var productCategory = itemView.findViewById(R.id.productCategory) as TextView
        var productButton = itemView.findViewById(R.id.productButton) as ConstraintLayout

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

            if (item.product!!.category == 1) {
                productCategory.text = "TELEVISIONS"
            } else {
                productCategory.text = "SHOES"
            }

            productButton.setOnClickListener {
                val intent = Intent(mContext, ProductDetailsActivity::class.java)
                intent.putExtra("product", item.product)
                mContext.startActivity(intent)
            }

        }
    }

    fun updateData(list: MutableList<ProductsWithImages>) {
        mList = list
        notifyDataSetChanged()
    }

}