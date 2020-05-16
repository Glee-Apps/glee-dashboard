package com.glee.dashboard.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.glee.dashboard.R
import com.glee.dashboard.model.Order
import com.glee.dashboard.viewmodel.OrderViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderAdapter(
    val mContext: Context,
    var mList: MutableList<Order>
) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var orderViewModel: OrderViewModel =
        ViewModelProviders.of(mContext as FragmentActivity).get(OrderViewModel::class.java)


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById(R.id.image) as ImageView
        var productName = itemView.findViewById(R.id.productName) as TextView
        var updatedAt = itemView.findViewById(R.id.updatedAt) as TextView
        var totalAmount = itemView.findViewById(R.id.totalAmount) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        with(holder) {
            orderViewModel.getProducts()
                ?.observe(mContext as FragmentActivity, Observer { products ->
                    products.forEach {
                        if (item.productId == it.product!!.id) {
                            productName.text = "${it.product?.name} X ${item.quantity}"

                            val d = it.product?.updatedAt!!.split("T").toTypedArray()

                            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                            val outputFormat: DateFormat = SimpleDateFormat("dd MMM")
                            val inputDateStr = d[0]
                            val date: Date = inputFormat.parse(inputDateStr)
                            val outputDateStr: String = outputFormat.format(date)

                            updatedAt.text = "$outputDateStr at ${d[1].take(5)}"

                            val total = item.quantity * it.product!!.cost
                            totalAmount.text = "Ksh.${"%,d".format(total)}"
                            image.load(it.images!![0].url)
                        }
                    }
                })
        }
    }

    fun updateData(list: MutableList<Order>) {
        mList = list
        notifyDataSetChanged()
    }

}