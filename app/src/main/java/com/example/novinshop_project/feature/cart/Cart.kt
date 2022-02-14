package com.example.novinshop_project.feature.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseFragment
import com.example.novinshop_project.data.ProductItemItem
import com.example.novinshop_project.feature.auth.AuthViewMolde
import com.example.novinshop_project.feature.auth.Login_Activity
import com.example.novinshop_project.feature.cart.adapter.AdapterCartItem
import com.example.novinshop_project.feature.cart.viewmodel.CartListViewModel
import com.example.novinshop_project.feature.nextlevel.NextLevel_Activity
import com.example.novinshop_project.utils.NO
import com.example.novinshop_project.utils.YES
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class Cart : BaseFragment(), AdapterCartItem.OnCartItemClick, RemoveItemDialog.OnDialogRemove {

    val cartListViewModel: CartListViewModel by viewModel()
    val authViewMolde: AuthViewMolde by viewModel()
    var cartItemAdapterPublic: AdapterCartItem? = null
    val compositeDisposable = CompositeDisposable()
    var removeItemDialog: RemoveItemDialog? = null
    var cartItemRemore:ProductItemItem?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_cart, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next_cart.setOnClickListener {

            startActivity(Intent(context,NextLevel_Activity::class.java))
        }
        cartListViewModel.cartListLiveDat.observe(viewLifecycleOwner)
        {
            val cartItemAdapter: AdapterCartItem by inject { parametersOf(it) }
            cartItemAdapterPublic = cartItemAdapter
            rc_cart.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            rc_cart.adapter = cartItemAdapter
            cartItemAdapter.setOnClikcItemCart(this)

        }
        cartListViewModel.offPriceLiveData.observe(viewLifecycleOwner)
        {
            cartItemAdapterPublic?.let { caritemAdapter ->

                caritemAdapter.publickAllOffPrIce = it

            }
            Timber.i("$it")
        }
        cartListViewModel.totalPriceLiveData.observe(viewLifecycleOwner)
        {
            cartItemAdapterPublic?.let { caritemAdapter ->

                caritemAdapter.publicTotalAllPrice = it
                caritemAdapter.notifyDataSetChanged()
            }

        }
        cartListViewModel.papybalePriceLiveData.observe(viewLifecycleOwner)
        {
            cartItemAdapterPublic?.let { caritemAdapter ->

                caritemAdapter.publickAllPayablePrice = it

            }

        }

        cartListViewModel.progressBarLiveData.observe(viewLifecycleOwner)
        {
            setProgressBar(it)
        }

        cartListViewModel.emptyStateLiveData.observe(viewLifecycleOwner)
        {
            val parent = view.findViewById<LinearLayout>(R.id.lnr_empty)
            if (it.show) {
                val emptyState = showEmptyState(R.layout.layout_empty_state)
                emptyState?.let { view ->

                    val txtMessage = view.findViewById<TextView>(R.id.txt_state)
                    val btnLogin = view.findViewById<Button>(R.id.btn_empty_state)
                    txtMessage.text = getString(it.message)
                    if (it.showButton) {
                        btnLogin.visibility = View.VISIBLE
                    } else {
                        btnLogin.visibility = View.GONE

                    }
                    btnLogin.setOnClickListener {

                        startActivity(Intent(context, Login_Activity::class.java))
                    }
                }
            } else {
                parent?.let {
                    it.visibility = View.GONE
                }
            }

            Timber.i("$it")

        }


    }

    override fun onStart() {
        super.onStart()
        authViewMolde.checkLogin()
        cartListViewModel.refresh()
    }

    override fun onRemoveItem(cartItem: ProductItemItem) {

        cartItemRemore = cartItem
        removeItemDialog = RemoveItemDialog()
        removeItemDialog!!.show(parentFragmentManager,null)
        removeItemDialog!!.setOnRemoveItem(this)


    }

    override fun sumItem(cartItem: ProductItemItem, count: Int) {
        cartItemAdapterPublic!!.sumItemCount(cartItem, count)
        val new = count + 1
        cartListViewModel.changeCountItem(cartItem, new, true).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }

            })

    }

    override fun minusItem(cartItem: ProductItemItem, count: Int) {
        cartItemAdapterPublic!!.minusItemCount(cartItem, count)
        val new = count - 1
        cartListViewModel.changeCountItem(cartItem, new, false).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onRemoveItemClick(type: String) {
        when (type) {
            YES -> {
                cartListViewModel.removeItemCart(cartItemRemore!!).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CompletableObserver {
                        override fun onSubscribe(d: Disposable) {
                            compositeDisposable.add(d)
                        }

                        override fun onComplete() {
                            removeItemDialog!!.dismiss()
                        }

                        override fun onError(e: Throwable) {

                        }

                    })
            }
            NO -> {
                removeItemDialog!!.dismiss()

            }

        }
    }
}