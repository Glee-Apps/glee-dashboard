package com.glee.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.glee.dashboard.model.*
import com.glee.dashboard.repository.OrderRepository

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: OrderRepository = OrderRepository(application)

    fun getOrdersOnline(): MutableLiveData<BaseOrdersResponse> {
        return repository.getOrdersOnline()
    }

    fun getProductsOnline(): MutableLiveData<BaseProductsResponse> {
        return repository.getProductsOnline()
    }


    fun setOrder(order: Order) {
        return repository.saveOrder(order)
    }

    fun getOrders(): LiveData<List<Order>>? = repository.getOrders()

    fun setProduct(product: Product) {
        return repository.saveProduct(product)
    }

    fun setImage(image: Image) {
        return repository.saveImage(image)
    }


    fun getProducts(): LiveData<List<ProductsWithImages>>? = repository.getProducts()

//    fun getUser(id: String): MutableLiveData<UserResponse> {
//        return repository.getUser(id)
//    }

//    fun updateStatus(id: String, req: StatusUpdateRequest): MutableLiveData<Message> {
//        return repository.updateStatus(id, req)
//    }

//    private val _index = MutableLiveData<Int>()
//    val text: LiveData<String> = Transformations.map(_index) {
//        "Hello world from section: $it"
//    }
//
//    fun setIndex(index: Int) {
//        _index.value = index
//    }

}