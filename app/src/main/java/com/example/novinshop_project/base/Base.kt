package com.example.novinshop_project.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.novinshop_project.R
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override val rootView: CoordinatorLayout?
        //        get() = window.decorView.rootView as CoordinatorLayout?
        get() {
            val parent = window.decorView.findViewById<ViewGroup>(android.R.id.content)
            if (parent !is CoordinatorLayout) {
                parent.children.forEach {
                    if (it is CoordinatorLayout) {
                        return it
                    }
                }
                throw Exception("rootView must be Coordinator Layout")
            } else {
                return parent
            }
        }

    override val myContex: Context?
        get() = this

}

abstract class BaseFragment : Fragment(), BaseView {
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout
    override val myContex: Context?
        get() = context

}

interface BaseView {
    val rootView: CoordinatorLayout?
    val myContex: Context?
    fun setProgressBar(progress: Boolean) {
        rootView?.let { rootView ->
            myContex?.let {
                var progressView = rootView.findViewById<View>(R.id.frame_progress)
                if (progressView == null && progress) {
                    progressView = LayoutInflater.from(myContex)
                        .inflate(R.layout.progress_view, rootView, false)
                    rootView.addView(progressView)
                }
                progressView?.visibility = if (progress) View.VISIBLE else View.GONE
            }

        }
    }

    fun showEmptyState(layout: Int): View? {

        rootView?.let {
            rootView
            myContex?.let {
                var emptyStat = rootView!!.findViewById<View>(R.id.lnr_empty)
                if (emptyStat == null) {
                    emptyStat = LayoutInflater.from(myContex)
                        .inflate(layout, rootView, false)
                    rootView!!.addView(emptyStat)
                }
                emptyStat.visibility = View.VISIBLE
                return emptyStat

            }

        }
        return null
    }

}


fun <T> Single<T>.singleObserver(): Single<T> {

    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


abstract class BaseViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    var progressBarLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

