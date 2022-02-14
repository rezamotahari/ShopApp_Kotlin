package com.example.novinshop_project.feature.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseFragment
import com.example.novinshop_project.data.ResponseBanners
import com.example.novinshop_project.feature.allAmazing.AllAmazing_Actvity
import com.example.novinshop_project.feature.detials.Details_Product
import com.example.novinshop_project.feature.home.adapterHome.*
import com.example.novinshop_project.feature.home.subCatLevel.SubCatLevel_Fragmnet
import com.example.novinshop_project.feature.search.Search_Activity
import com.example.novinshop_project.utils.PRODUCT_ID
import com.example.novinshop_project.utils.TYPE_ONE
import com.example.novinshop_project.utils.TYPE_TWO
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class Home : BaseFragment(), AdapterBannersType.OnClickBannersType,
    AdapterAmazing.OnClickProductItem, AdapterPopualr.OnclickItemProductPopular,
    AdapterCategoryHome.OnClickSubCatLevel {

    val homeViewModel: HomeViewModel by viewModel()
    val handler = Handler(Looper.myLooper()!!)
    var bannersSlider: List<ResponseBanners>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_all_amazing.setOnClickListener {
            startActivity(Intent(requireContext(), AllAmazing_Actvity::class.java))

        }
        rltv_search.setOnClickListener {

            startActivity(Intent(context, Search_Activity::class.java))
        }
        homeViewModel.bannersLiveData.observe(viewLifecycleOwner)
        {
            Log.i("r", it.toString())
            bannersSlider = it
            val bannersAdapter: AdapterBanners by inject { parametersOf(it) }
            banners_viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
            banners_viewpager.setClipToPadding(false)
            banners_viewpager.setClipChildren(false)
            banners_viewpager.setOffscreenPageLimit(3)
            banners_viewpager.getChildAt(0)!!.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER)
            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(20))
            transformer.addTransformer { page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.1f
            }
            banners_viewpager.setPageTransformer(transformer)
            banners_viewpager.adapter = bannersAdapter
            dots_indicator.setViewPager2(banners_viewpager)

            banners_viewpager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(sliderRunable)
                    handler.postDelayed(sliderRunable, 5000)
                }
            })

        }
        homeViewModel.progressBarLiveData.observe(viewLifecycleOwner)
        {
            setProgressBar(it)
        }

        homeViewModel.categoryLiveData.observe(viewLifecycleOwner)

        {
            Log.i("re", it.toString())
            val categoryAdapter: AdapterCategoryHome by inject { parametersOf(it) }
            rc_catgory_Home.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rc_catgory_Home.adapter = categoryAdapter
            categoryAdapter.setSubCatLevel(this)
        }

        homeViewModel.amazingProductLivaData.observe(viewLifecycleOwner)
        {
//          Log.i("HOME", "onViewCreated: $it")
            val amazingAdapterAmazing: AdapterAmazing by inject { parametersOf(it) }
            rc_amazing_product.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rc_amazing_product.adapter = amazingAdapterAmazing
            Timber.i("$it")
            amazingAdapterAmazing.setOnCLickProductItem(this)
        }
        homeViewModel.popularLiveData.observe(viewLifecycleOwner)
        {
            val popualrAdapter: AdapterPopualr by inject { parametersOf(it) }
            rc_popular_Producat_main.layoutManager = GridLayoutManager(context, 3)
            val itemDecorationVertical: RecyclerView.ItemDecoration =
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            val itemDecorationhORIZONTAL: RecyclerView.ItemDecoration =
                DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
//            rc_popular_Producat_main.addItemDecoration(itemDecorationVertical)
            rc_popular_Producat_main.addItemDecoration(itemDecorationhORIZONTAL)
            rc_popular_Producat_main.adapter = popualrAdapter
            popualrAdapter.setOnclickItemProductPopular(this)

        }
        homeViewModel.bannerTypeLiveData.observe(viewLifecycleOwner)
        {

            val bannersType: AdapterBannersType by inject { parametersOf(it) }
            rc_banners_type_main.layoutManager = GridLayoutManager(context, 2)
            rc_banners_type_main.adapter = bannersType
            bannersType.setOnClickBannersType(this)
        }

    }

    private val sliderRunable = Runnable {

        if (this == null) return@Runnable
        val index: Int = banners_viewpager.currentItem!! + 1
        banners_viewpager.setCurrentItem(index)
        if (index > bannersSlider!!.size - 1)
            banners_viewpager.setCurrentItem(0)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(sliderRunable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(sliderRunable, 5000)
    }

    override fun onClickTypes(type: String, link: String) {

        when (type) {
            TYPE_ONE -> {
                Timber.i("1")
            }
            TYPE_TWO -> {
                val browser = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(browser)
                Timber.i("2")
            }
        }
    }

    override fun onClickProduct(productId: Int) {

        startActivity(Intent(context, Details_Product::class.java).apply {

            putExtra("id", productId)
        })
    }

    override fun onClickPopularItem(idProduct: Int) {
        startActivity(Intent(context, Details_Product::class.java).apply {

            putExtra("id", idProduct)
        })
    }

    override fun onClickSubcat(subcatId: Int) {

        val bundle = Bundle()
        bundle.putInt(PRODUCT_ID, subcatId)
        SubCatLevel_Fragmnet.newInstance(subcatId)
        findNavController().navigate(R.id.action_home2_to_subCatLevel_Fragmnet, bundle)
    }
}