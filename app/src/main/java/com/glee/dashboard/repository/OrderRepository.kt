package com.glee.dashboard.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.glee.dashboard.dao.ImageDao
import com.glee.dashboard.dao.OrderDao
import com.glee.dashboard.dao.ProductDao
import com.glee.dashboard.model.*
import com.glee.dashboard.source.ApiInterface
import com.glee.dashboard.source.ApiService
import com.glee.dashboard.source.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class OrderRepository(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private var client: ApiInterface = ApiService.api

    private var orderDao: OrderDao?

    private var productDao: ProductDao?

    private var imageDao: ImageDao?

    init {
        val db = Database.getDatabase(application)
        orderDao = db?.orderDao()
        productDao = db?.productDao()
        imageDao = db?.imageDao()
    }

    fun getOrdersOnline(): MutableLiveData<BaseOrdersResponse> {

        val liveData = MutableLiveData<BaseOrdersResponse>()

        client.getOrders().enqueue(object : Callback<BaseOrdersResponse> {
            override fun onResponse(
                call: Call<BaseOrdersResponse>,
                response: Response<BaseOrdersResponse>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()

                    if (response.code() == 200) {
                        liveData.value = response.body()
                    } else {
                        liveData.value = null
                    }

                }
            }

            override fun onFailure(call: Call<BaseOrdersResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return liveData
    }

    fun getProductsOnline(): MutableLiveData<BaseProductsResponse> {

        val liveData = MutableLiveData<BaseProductsResponse>()

        client.getProducts().enqueue(object : Callback<BaseProductsResponse> {
            override fun onResponse(
                call: Call<BaseProductsResponse>,
                response: Response<BaseProductsResponse>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()

                    if (response.code() == 200) {
                        liveData.value = response.body()
                    } else {
                        liveData.value = null
                    }

                }
            }

            override fun onFailure(call: Call<BaseProductsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return liveData
    }

    fun getOrders(): LiveData<List<Order>>? = orderDao?.getOrders()

    fun saveOrder(order: Order) {
        launch { saveOrdersBG(order) }
    }

    private suspend fun saveOrdersBG(order: Order) {
        withContext(Dispatchers.IO) {
            orderDao?.setOrder(order)
        }
    }

    fun getProducts(): LiveData<List<ProductsWithImages>>? = productDao?.loadProductsWithImages()

    fun getProductImages(id: Int): LiveData<List<Image>>? = imageDao?.getImagesForProduct(id)

    fun saveProduct(product: Product) {
        launch { saveProductsBG(product) }
    }

    private suspend fun saveProductsBG(product: Product) {
        withContext(Dispatchers.IO) {
            productDao?.setProduct(product)
        }
    }

    fun saveImage(image: Image) {
        launch { saveImagesBG(image) }
    }

    private suspend fun saveImagesBG(image: Image) {
        withContext(Dispatchers.IO) {
            imageDao?.setImage(image)
        }
    }
}