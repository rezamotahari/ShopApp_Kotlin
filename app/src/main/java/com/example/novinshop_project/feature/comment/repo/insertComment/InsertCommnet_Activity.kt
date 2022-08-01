package com.example.novinshop_project.feature.comment.repo.insertComment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.data.ScoreItems
import com.example.novinshop_project.feature.comment.repo.insertComment.adapter.AdapterScoreInsert
import com.example.novinshop_project.utils.PRODUCT_ID
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_insert_commnet.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import java.lang.StringBuilder

class InsertCommnet_Activity : BaseActivity(), AdapterScoreInsert.OnChangeItemSlider {
    private val TAG = "InsertCommnet_Activity"
    val insertCommnetViewModel: InsertCommnetViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(
                PRODUCT_ID, 0
            )
        )
    }
    var posotive: String? = null
    var negative: String? = null
    var advice: String? = null
    var score: String? = null
    var arrayScore = ArrayList<ScoreItems>()
    var checkStatus: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_insert_commnet)
        txt_title_activity.text = getString(R.string.add_comment)
        img_Back.setOnClickListener {
            finish()
        }
        val productId = intent.getIntExtra(PRODUCT_ID, 0)

        insertCommnetViewModel.progressBarLiveData.observe(this)
        {
            setProgressBar(it)
        }

        insertCommnetViewModel.scoreinsertCommnetLiveData.observe(this)
        {
            Timber.i("$it")
            if (it.status.equals("1")) {
                checkStatus = true
                rc_score.visibility = View.VISIBLE

                rc_score.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                val adapterScoreInsert: AdapterScoreInsert by inject { parametersOf(it.score) }
                rc_score.adapter = adapterScoreInsert
                adapterScoreInsert.setOnChangeSlider(this)
            } else {
                rc_score.visibility = View.GONE
                checkStatus = false
            }

        }
        img_addPostive.setOnClickListener {

            if (edt_posotive.length() != 0) {
                val sPosotive = StringBuilder(edt_posotive.text.toString())
                txtPosptive.append("\n" + sPosotive)
                edt_posotive.text.clear()
                sPosotive.append("")
                posotive = txtPosptive.text.toString().replace("\n", ",")
            }


        }
        img_addNegative.setOnClickListener {

            if (edt_negative.length() != 0) {
                val sNegative = StringBuilder(edt_negative.text.toString())
                txt_Negative.append("\n" + sNegative)
                edt_negative.text.clear()
                sNegative.append("")
                negative = txt_Negative.text.toString().trim().replace("\n", ",")

            }


        }


        img_1.setOnClickListener {

            advice = "توصیه میکنم"
            img_1.setColorFilter(resources.getColor(R.color.green_500))
            img_2.setColorFilter(resources.getColor(R.color.gray_900))
            img_3.setColorFilter(resources.getColor(R.color.gray_900))

        }
        img_2.setOnClickListener {
            img_1.setColorFilter(resources.getColor(R.color.gray_900))
            img_2.setColorFilter(resources.getColor(R.color.yellow))
            img_3.setColorFilter(resources.getColor(R.color.gray_900))
            advice = "مطمئن نیستم"

        }
        img_3.setOnClickListener {
            img_1.setColorFilter(resources.getColor(R.color.gray_900))
            img_2.setColorFilter(resources.getColor(R.color.gray_900))
            img_3.setColorFilter(resources.getColor(R.color.red))
            advice = "توصیه نمیکنم"

        }

        btn_add_commnet.setOnClickListener {

            if (checkStatus) {
                insertCommnetViewModel.insertCommnetPro(
                    productId, edt_content.text.toString(), edt_title.text.toString(),
                    posotive!!, negative!!, advice!!, score!!
                )
            } else {
                insertCommnetViewModel.insertCommnet(
                    productId, edt_content.text.toString(), edt_title.text.toString(),
                    posotive!!, negative!!, advice!!
                )
            }


        }

        insertCommnetViewModel.insertCommnetLiveData.observe(this)
        {
            Snackbar.make(coordinator, it.message, Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onChangeScore(value: Float, item: ScoreItems) {
        Log.i(TAG, "onChangeScore: $value")
        Log.i(TAG, "onChangeScore: $item")
        arrayScore.remove(item)
        item.value = value.toInt()
        arrayScore.add(item)
        Log.i(TAG, "onChangeScore: $arrayScore")
        score = arrayScore.toString().replace("ScoreItems", "").trim()
        Log.i(TAG, "onChangeScore: $score")

    }


}