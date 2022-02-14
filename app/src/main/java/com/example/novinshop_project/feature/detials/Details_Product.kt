package com.example.novinshop_project.feature.detials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.compose.ui.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseActivity
import com.example.novinshop_project.feature.auth.AuthViewMolde
import com.example.novinshop_project.feature.auth.Login_Activity
import com.example.novinshop_project.feature.comment.repo.ShowCommnet_Activity
import com.example.novinshop_project.feature.detials.adapter.*
import com.example.novinshop_project.feature.detials.chart.HistoryPrice_Activity
import com.example.novinshop_project.feature.detials.comapre.ComapreCategory_Activity
import com.example.novinshop_project.feature.detials.dialog.MoreDialogBottomSheet
import com.example.novinshop_project.feature.detials.property.Property_Activity
import com.example.novinshop_project.utils.CHART
import com.example.novinshop_project.utils.COMPARE
import com.example.novinshop_project.utils.PriceConverter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details_product.*
import kotlinx.android.synthetic.main.activity_details_product.dots_indicator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_show_comment.*
import kotlinx.android.synthetic.main.layout_by_product.*
import kotlinx.android.synthetic.main.layout_seller.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class Details_Product : BaseActivity(), MoreDialogBottomSheet.OnClickMoreDialog,AdapterColor.OnClickItemColor

, AdapterSizeProduct.OnclickItemSizeId {
    val detailsViewModel: DetailsViewModel by viewModel {
        parametersOf(intent.getIntExtra("id", 0))
    }
    val authViewMolde:AuthViewMolde by viewModel()
    var idProduct: Int? = null
    var idColor: Int = 0
    var idSize: Int = 0
    var checkColor:Boolean = true
    var checkSize:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_product)
        img_detail_close.setOnClickListener {

            finish()
        }
        img_detail_more.setOnClickListener {

            val moreDialog = MoreDialogBottomSheet()
            moreDialog.show(supportFragmentManager, null)
            moreDialog.setOnclickMoreDialog(this)
        }

        img_detail_fav.setOnClickListener {


            if (authViewMolde.checkLoginLiveData.value == true)
            {
              authViewMolde.addToBookmark(idProduct!!)
            }
            else
            {
                startActivity(Intent(this,Login_Activity::class.java))
            }


        }
        authViewMolde.addToBookmarkLiveData.observe(this)
        {
            if (it.status.equals("true"))
            {
                img_detail_fav.setImageResource(R.drawable.ic_baseline_favorite_24)
                Snackbar.make(coordinator,it.message,Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                img_detail_fav.setImageResource(R.drawable.ic_favourite_heart)
                Snackbar.make(coordinator,it.message,Snackbar.LENGTH_SHORT).show()

            }

            Timber.i("${it.message}")
        }


        detailsViewModel.detialsProductLiveDta.observe(this)
        {
            idProduct = it.id
            val galleryAdapter: AdapterImageGallery by inject { parametersOf(it.images) }
            viewPager_gallery.adapter = galleryAdapter
            dots_indicator.setViewPager2(viewPager_gallery)
            txt_titile_product.text = it.name
            rating_product.rating = 3.5f
            txt_seller.text = it.sellerName
            txt_garanatty.text = it.garantyDescription
            txt_count_number.text = it.number.toString() + " تعداد موجود در انبار"
            txt_sub_cat_1.text = it.catName
            txt_sub_cat_2.text = it.subcatName
            txt_content_product.text = it.description
            txt_count_commnet.text = it.commentCount

             if (it.status.equals("true"))
            {
                img_detail_fav.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            else
            {
                img_detail_fav.setImageResource(R.drawable.ic_favourite_heart)

            }
            if (it.productColors.isNullOrEmpty()) {
                rc_product_color.visibility = View.GONE
                title_Color.visibility = View.GONE
                checkColor = false
            }
            if (it.productSize.isNullOrEmpty()) {
                rc_product_size.visibility = View.GONE
                txt_size.visibility = View.GONE
                checkSize = false
            }

            if (it.offPrice.equals("0")) {
                txt_price_product.text = PriceConverter.priceConverter(it.price)
                txt_price_off_product.visibility = View.GONE
                txt_price_product.textSize = 14F
                txt_price_product.setTextColor(ContextCompat.getColor(this, R.color.gray_900))
            } else {
                txt_price_off_product.text = PriceConverter.priceConverter(it.offPrice)
                txt_price_product.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                txt_price_product.text = PriceConverter.priceConverter(it.price)
            }
//            txt_price_off_product.text = PriceConverter.priceConverter(it.offPrice)
//            txt_price_product.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
//            txt_price_product.text = PriceConverter.priceConverter(it.price)
            Timber.i("$it")
            //
            val colorAdapter: AdapterColor by inject { parametersOf(it.productColors) }
            rc_product_color.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            rc_product_color.adapter = colorAdapter
            colorAdapter.setOnClickColorId(this)
            //
            val adapterSize: AdapterSizeProduct by inject { parametersOf(it.productSize) }
            rc_product_size.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            rc_product_size.adapter = adapterSize
            adapterSize.setOnSizeId(this)
            //
            val propertiesAdapter: AdapterProperties by inject { parametersOf(it.properties) }
            rc_product_property.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rc_product_property.adapter = propertiesAdapter
            //
            val commnetAdapter: AdapterCommnet by inject { parametersOf(it.comments) }
            rc_comment.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            rc_comment.adapter = commnetAdapter
            //
            val simialrAdapter: AdapterSimilar by inject { parametersOf(it.similar) }
            rc_smilar_product.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

            val itemDecorationhORIZONTAL: RecyclerView.ItemDecoration =
                DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
            rc_smilar_product.addItemDecoration(itemDecorationhORIZONTAL)
            rc_smilar_product.adapter = simialrAdapter
        }

        detailsViewModel.progressBarLiveData.observe(this)
        {

            setProgressBar(it)
        }

        rltv_property.setOnClickListener {

            startActivity(Intent(this,Property_Activity::class.java).apply {

                putExtra("id",idProduct)
            })

        }
        txt_count_commnet.setOnClickListener {

            startActivity(Intent(this,ShowCommnet_Activity::class.java).apply {

                putExtra("id",idProduct)
            })
        }

        btn_buy_product.setOnClickListener {

            if ( checkColor ==true && checkSize == false)
            {
                if (idColor!=0)
                authViewMolde.addToCart(idProduct!!,idColor!!,0)
                else
                    Snackbar.make(coordinator,"لطفا رنگ را انتخاب کنید",Snackbar.LENGTH_SHORT).show()
            }
            else if (checkSize == true && checkColor == false)
            {
                if (idSize!=0)
                authViewMolde.addToCart(idProduct!!,0,idSize!!)
                else
                Snackbar.make(coordinator,"لطفا سایز را انتخاب کنید",Snackbar.LENGTH_SHORT).show()

            }
            else
            {
                if (idColor!! == 0 || idSize!! ==0)
                    Snackbar.make(coordinator,"لطفا سایز و رنگ را انتخاب کنید",Snackbar.LENGTH_SHORT).show()
                else
                    authViewMolde.addToCart(idProduct!!,idColor!!,idSize!!)


            }




        }

        authViewMolde.addToCartLiveData.observe(this)
        {
            Snackbar.make(coordinator,it.message,Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun onclickMore(type: String) {

        when (type) {
            CHART -> {
                startActivity(Intent(this, HistoryPrice_Activity::class.java).apply {

                    putExtra("id",idProduct)
                })

            }
            COMPARE -> {
                startActivity(Intent(this, ComapreCategory_Activity::class.java).apply {

                    putExtra("id",idProduct)
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        authViewMolde.checkLogin()
    }

    override fun onClickColorId(colorId: Int) {
     idColor = colorId
        Timber.i("$colorId")
    }

    override fun onClickSizeId(sizeId: Int) {
        idSize = sizeId
        Timber.i("$sizeId")
    }
}