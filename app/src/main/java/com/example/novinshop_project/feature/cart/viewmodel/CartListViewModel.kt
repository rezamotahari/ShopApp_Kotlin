package com.example.novinshop_project.feature.cart.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.*
import com.example.novinshop_project.feature.auth.TokenContainer
import com.example.novinshop_project.feature.cart.repo.CartListRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver
import io.reactivex.Completable
import org.greenrobot.eventbus.EventBus

class CartListViewModel(val cartListRepository: CartListRepository) : BaseViewModel() {

    val cartListLiveDat = MutableLiveData<ResponseCartList>()
    val emptyStateLiveData = MutableLiveData<EmptyState>()
    val papybalePriceLiveData = MutableLiveData<Int>()
    val totalPriceLiveData = MutableLiveData<Int>()
    val offPriceLiveData = MutableLiveData<Int>()

    init {
        getListCart()
    }


    fun getListCart() {
        if (!TokenContainer.token.isNullOrEmpty()) {
            progressBarLiveData.value = true
            emptyStateLiveData.value = EmptyState(false)
            cartListRepository.getCartList().singleObserver()
                .doFinally {

                    progressBarLiveData.value = false
                }
                .subscribe(object : DigiShopSingleObserver<ResponseCartList>(compositeDisposable) {
                    override fun onSuccess(t: ResponseCartList) {

                        if (!t.productItem.isNullOrEmpty()) {
                            cartListLiveDat.value = t
                            papybalePriceLiveData.value = t.payablePrice
                            totalPriceLiveData.value = t.totalPrice
                            offPriceLiveData.value = t.totalOffPrice
                        } else {
                            emptyStateLiveData.value = EmptyState(true, R.string.emptyCart)
                        }
                    }

                })
        } else {
            emptyStateLiveData.value = EmptyState(true, R.string.isLogin, true)
        }
    }

    fun removeItemCart(cartItem: ProductItemItem): Completable {
        return cartListRepository.removeItem(cartItem.id.toInt()).doAfterSuccess {
            val cartItems: MutableList<ProductItemItem> =
                cartListLiveDat.value!!.productItem as MutableList<ProductItemItem>
            val index = cartItems.indexOf(cartItem)
            cartItems.removeAt(index)
            cartListLiveDat.value!!.productItem = cartItems

            val countItem = EventBus.getDefault().getStickyEvent(ResponseCount::class.java)
            countItem?.let {
                it.count -= cartItem.count.toInt()
                EventBus.getDefault().postSticky(it)

            }
            changePrice()
            cartListLiveDat?.let {

                if (it.value?.productItem!!.isEmpty()) {
                    emptyStateLiveData.postValue(EmptyState(true, R.string.emptyCart))
                }

            }
        }.ignoreElement()

    }

    fun changePrice() {
        cartListLiveDat.value?.let { responce ->
            totalPriceLiveData.value?.let {

                var totalPrice = 0
                var offPrice = 0
                var payblaPrice = 0
                responce.productItem.forEach { cartItem ->

                    totalPrice += cartItem.price.toInt() * cartItem.count.toInt()
                    offPrice += cartItem.offPrice.toInt() * cartItem.count.toInt()
                    payblaPrice = totalPrice - offPrice

                }
             //   responce.payablePrice=payblaPrice
                totalPriceLiveData.postValue(totalPrice)
               papybalePriceLiveData.postValue(payblaPrice)
                offPriceLiveData.postValue(offPrice)
            }

        }
    }

    fun changeCountItem(cartItem: ProductItemItem, count: Int, isChange: Boolean): Completable {
        return cartListRepository.changeCountItem(cartItem.id.toInt(), count).doAfterSuccess {
            val cartItemCount = EventBus.getDefault().getStickyEvent(ResponseCount::class.java)
            cartItemCount?.let {
                if (isChange) {
                    it.count += 1
                    EventBus.getDefault().postSticky(it)
                }
                else
                {
                    it.count -=1
                    EventBus.getDefault().postSticky(it)
                }

            }
            val cartItems:MutableList<ProductItemItem> = cartListLiveDat.value!!.productItem as MutableList<ProductItemItem>
            val index = cartItems.indexOf(cartItem)
            cartItem.count = count.toString()
            cartItems.set(index,cartItem)
            cartListLiveDat.value!!.productItem = cartItems
            changePrice()


        }.ignoreElement()
    }

    fun refresh() {
        getListCart()
    }

}