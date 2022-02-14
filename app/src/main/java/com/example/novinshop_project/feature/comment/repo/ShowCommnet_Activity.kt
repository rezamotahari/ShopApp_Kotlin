package com.example.novinshop_project.feature.comment.repo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.feature.auth.AuthViewMolde
import com.example.novinshop_project.feature.auth.Login_Activity
import com.example.novinshop_project.feature.comment.repo.adapter.AdapterRatingCommnet
import com.example.novinshop_project.feature.comment.repo.adapter.AdapterShowCommnet
import com.example.novinshop_project.feature.comment.repo.insertComment.InsertCommnet_Activity
import com.example.novinshop_project.utils.PRODUCT_ID
import kotlinx.android.synthetic.main.activity_show_commnet.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class ShowCommnet_Activity : BaseActivity() {
    private  val TAG = "ShowCommnet_Activity"
    val showRatingViewModel :ShowRatingViewModel by viewModel { parametersOf(intent.getIntExtra("id",0)) }
    val authViewMolde:AuthViewMolde by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_commnet)

        txt_title_activity.text= getString(R.string.commnet)
        img_Back.setOnClickListener {
            finish()
        }
        showRatingViewModel.showRatingLiveData.observe(this)
        {
            val adapterRatingCommnet :AdapterRatingCommnet by inject { parametersOf(it) }
            rc_rating.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            rc_rating.adapter = adapterRatingCommnet

            Log.i(TAG, "onCreate: $it")
            Timber.i("$it")
        }

        showRatingViewModel.showCommnetLiveDta.observe(this)
        {

            val adapterShowCommnet :AdapterShowCommnet by inject { parametersOf(it) }
            rc_show_commnet.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            rc_show_commnet.adapter = adapterShowCommnet
        }
        showRatingViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }

        fab_intent_insert_commnet.setOnClickListener {

            if (authViewMolde.checkLoginLiveData.value == true)
            {
                startActivity(Intent(this,InsertCommnet_Activity::class.java).apply {
                    putExtra(PRODUCT_ID,intent.getIntExtra("id",0))
                })
            }
            else {
                startActivity(Intent(this, Login_Activity::class.java))

            }


        }
    }
    override fun onResume() {
        super.onResume()
        authViewMolde.checkLogin()
    }
}