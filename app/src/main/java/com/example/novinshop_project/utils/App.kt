package com.example.novinshop_project.utils

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.example.novinshop_project.MainViewModel
import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.api.retrofitApi
import com.example.novinshop_project.data.*
import com.example.novinshop_project.feature.address.AdapterShowAddress
import com.example.novinshop_project.feature.address.AddressViewModel
import com.example.novinshop_project.feature.address.repo.AddressRepository
import com.example.novinshop_project.feature.address.repo.AddressRepositoryImpl
import com.example.novinshop_project.feature.address.source.RemoteAddressDataSource
import com.example.novinshop_project.feature.allAmazing.SortViewModel
import com.example.novinshop_project.feature.allAmazing.adapter.AdapterAllAmazing
import com.example.novinshop_project.feature.allAmazing.repo.SortRepository
import com.example.novinshop_project.feature.allAmazing.repo.SortRepositoryImpl
import com.example.novinshop_project.feature.allAmazing.source.RemoteSortDataSource
import com.example.novinshop_project.feature.auth.AuthViewMolde
import com.example.novinshop_project.feature.auth.repo.AuthRepository
import com.example.novinshop_project.feature.auth.repo.AuthRepositoryImpl
import com.example.novinshop_project.feature.auth.source.AuthLocalDataSource
import com.example.novinshop_project.feature.auth.source.RemoteAuthDataSource
import com.example.novinshop_project.feature.cart.adapter.AdapterCartItem
import com.example.novinshop_project.feature.cart.repo.CartListRepository
import com.example.novinshop_project.feature.cart.repo.CartListRepositpryImpl
import com.example.novinshop_project.feature.cart.source.RemoteCartListDataSource
import com.example.novinshop_project.feature.cart.viewmodel.CartListViewModel
import com.example.novinshop_project.feature.category.adapter.AdapterBrands
import com.example.novinshop_project.feature.category.adapter.AdapterCategory
import com.example.novinshop_project.feature.category.adapter.AdapterPopularCat
import com.example.novinshop_project.feature.category.adapter.AdapterSubCat
import com.example.novinshop_project.feature.category.brandProduct.adapter.AdapterBrandProduct
import com.example.novinshop_project.feature.category.brandProduct.repo.BrandBannerRepository
import com.example.novinshop_project.feature.category.brandProduct.repo.BrandBannerRepositoryImpl
import com.example.novinshop_project.feature.category.brandProduct.source.RemoteBrandBannerDataSource
import com.example.novinshop_project.feature.category.brandProduct.viewmodel.BrandBannerViewModel
import com.example.novinshop_project.feature.category.repo.CategoryRepository
import com.example.novinshop_project.feature.category.repo.CategoryRepositoryImpl
import com.example.novinshop_project.feature.category.source.RemoteCategoryDataSource
import com.example.novinshop_project.feature.category.subCat.repo.SubCatRepository
import com.example.novinshop_project.feature.category.subCat.repo.SubcatRepositryImpl
import com.example.novinshop_project.feature.category.subCat.source.RemoteSubCatDataSource
import com.example.novinshop_project.feature.category.subCat.viewmodel.SubCatViewModel
import com.example.novinshop_project.feature.category.viewmodel.CategoryViewModel
import com.example.novinshop_project.feature.comment.repo.ShowRatingViewModel
import com.example.novinshop_project.feature.comment.repo.adapter.AdapterRatingCommnet
import com.example.novinshop_project.feature.comment.repo.adapter.AdapterShowCommnet
import com.example.novinshop_project.feature.comment.repo.insertComment.InsertCommnetViewModel
import com.example.novinshop_project.feature.comment.repo.insertComment.adapter.AdapterScoreInsert
import com.example.novinshop_project.feature.comment.repo.insertComment.repo.InsertCommnetRepository
import com.example.novinshop_project.feature.comment.repo.insertComment.repo.InsertCommnetRepositoryImpl
import com.example.novinshop_project.feature.comment.repo.insertComment.source.RemoteInsertDataSource
import com.example.novinshop_project.feature.comment.repo.repo.ShowCommentRepository
import com.example.novinshop_project.feature.comment.repo.repo.ShowCommnetRepositpryImpl
import com.example.novinshop_project.feature.comment.repo.source.RemoteShowCommnetDataSource
import com.example.novinshop_project.feature.detailsOrderHistory.AdapterProductDetilsOrder
import com.example.novinshop_project.feature.detailsOrderHistory.DetilasOrderViewModel
import com.example.novinshop_project.feature.detailsOrderHistory.repo.DetilasOrderRepository
import com.example.novinshop_project.feature.detailsOrderHistory.repo.DetilasOrderRepositoryImpl
import com.example.novinshop_project.feature.detailsOrderHistory.source.RemoteDetailsOrederDataSource
import com.example.novinshop_project.feature.detials.DetailsViewModel
import com.example.novinshop_project.feature.detials.adapter.*
import com.example.novinshop_project.feature.detials.chart.HistoryViewMolde
import com.example.novinshop_project.feature.detials.chart.repo.HisortChartRepositoryImpl
import com.example.novinshop_project.feature.detials.chart.repo.HistoryChartRepository
import com.example.novinshop_project.feature.detials.chart.source.RmoteHistoryChartDataSource
import com.example.novinshop_project.feature.detials.comapre.AdapterCategoryCompare
import com.example.novinshop_project.feature.detials.comapre.CategoryCoampreViewModel
import com.example.novinshop_project.feature.detials.comapre.CompareProductViewModel
import com.example.novinshop_project.feature.detials.comapre.adapter.AdapterCompareParent
import com.example.novinshop_project.feature.detials.comapre.repo.CategoryComapreRepository
import com.example.novinshop_project.feature.detials.comapre.repo.CategoryComapreRepositoryImpl
import com.example.novinshop_project.feature.detials.comapre.repo.CompareProductRepository
import com.example.novinshop_project.feature.detials.comapre.repo.CompareProductRepositoryImpl
import com.example.novinshop_project.feature.detials.comapre.source.RemoteCategoryComapreDataSource
import com.example.novinshop_project.feature.detials.comapre.source.RemoteCompareDataSource
import com.example.novinshop_project.feature.detials.property.AdapterProperty
import com.example.novinshop_project.feature.detials.property.PropertyViewModel
import com.example.novinshop_project.feature.detials.property.repo.PropertyRepository
import com.example.novinshop_project.feature.detials.property.repo.PropertyRepositoryImpl
import com.example.novinshop_project.feature.detials.property.source.RemotePropertyDataSource
import com.example.novinshop_project.feature.detials.repo.DetailsProductRepository
import com.example.novinshop_project.feature.detials.repo.DetailsProductRepositoryImpl
import com.example.novinshop_project.feature.detials.source.RemoteDetilasProductDataSource
import com.example.novinshop_project.feature.home.HomeViewModel
import com.example.novinshop_project.feature.home.adapterHome.*
import com.example.novinshop_project.feature.home.repo.*
import com.example.novinshop_project.services.ImageLoadingImpl
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.feature.home.source.*
import com.example.novinshop_project.feature.home.subCatLevel.AdapterSubcatLevel
import com.example.novinshop_project.feature.home.subCatLevel.SubcatLevelViewModel
import com.example.novinshop_project.feature.nextlevel.AdapterCheckOutProduct
import com.example.novinshop_project.feature.nextlevel.CheckOutListViewModel
import com.example.novinshop_project.feature.nextlevel.repo.CheckOutListRepository
import com.example.novinshop_project.feature.nextlevel.repo.CheckOutListRepositpryImpl
import com.example.novinshop_project.feature.nextlevel.source.RemoteCheckOutDataSource
import com.example.novinshop_project.feature.order.OrderHistoryViewModel
import com.example.novinshop_project.feature.order.adapter.AdapterOrderHistory
import com.example.novinshop_project.feature.order.repo.OrderHistoryRepository
import com.example.novinshop_project.feature.order.repo.OrderHistoryRepositoryImpl
import com.example.novinshop_project.feature.order.source.RemoteOrderHistory
import com.example.novinshop_project.feature.profile.favourite.AdapterFavourite
import com.example.novinshop_project.feature.profile.favourite.FavouriteViewModel
import com.example.novinshop_project.feature.profile.favourite.repo.FavouriteRepository
import com.example.novinshop_project.feature.profile.favourite.repo.FavouriteRepositoryImpl
import com.example.novinshop_project.feature.profile.favourite.source.RemoteFavouriteDataSource
import com.example.novinshop_project.feature.profile.infoUser.InfoViewModel
import com.example.novinshop_project.feature.profile.infoUser.repo.InfoRepositoryImpl
import com.example.novinshop_project.feature.profile.infoUser.repo.InfoRespository
import com.example.novinshop_project.feature.profile.infoUser.source.RemoteInfoDartaSource
import com.example.novinshop_project.feature.search.adapter.AdapterPartOne
import com.example.novinshop_project.feature.search.adapter.AdapterPartTwo
import com.example.novinshop_project.feature.search.repo.SearchRepository
import com.example.novinshop_project.feature.search.repo.SearchRepositoryImpl
import com.example.novinshop_project.feature.search.source.RemoteSearchDataSource
import com.example.novinshop_project.feature.search.viewmodel.SearchViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.get


import org.koin.android.ext.koin.androidContext

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)
        val appSignatureHashHelper = AppSignatureHashHelper(this)
        Log.i("RE", "HashKey: "+appSignatureHashHelper.appSignatures[0])
        val myModule = module {
            single<ApiService> { retrofitApi() }
            factory<BannerRepository> {
                BannerRepositoryImpl(
                    RemoteBannersDataSource(get()),
                    LocalBannersDataSource()
                )
            }
            factory<CategoryHomeRepository> {
                CategoryHomeRepositoryImp(
                    RemoteCategoryHomeDataSource(
                        get()
                    )
                )
            }
            factory<AmazingRepository> { AmazingRepositoryImp(RemoteAmazingDatasource(get())) }
            factory<PopularProductRepository> {
                PopualarProductRepositoryImp(
                    RemotePopualarProductDataSource(get())
                )
            }
            factory<BannersTypeRepository> {
                BannersTypeRepositoryImp(
                    RemoteBannersTypeDataSource(
                        get()
                    )
                )
            }
            viewModel { HomeViewModel(get(), get(), get(), get(), get()) }
            single<ImageLoadingServices> { ImageLoadingImpl() }
            factory { (banners: List<ResponseBanners>) -> AdapterBanners(banners, get()) }
            factory { (categories: List<ResponseCategoryHome>) ->
                AdapterCategoryHome(
                    categories,
                    get()
                )
            }
            factory { (amazingProducts: List<ResponseAmazingProduct>) ->
                AdapterAmazing(
                    amazingProducts,
                    get()
                )
            }
            factory { (popualrs: List<ResponsePopular>) -> AdapterPopualr(popualrs, get()) }
            factory { (types: List<ResponseBannersType>) -> AdapterBannersType(types, get()) }
            factory <SubCatLevelRepository>{ SubcatLevelRepositoryImpl(RemoteSubCatLevelDataSource(get())) }
            viewModel{(subCtId:Int)->SubcatLevelViewModel(subCtId,get())}
            factory { (listSubCat:List<ResponseSubCatLevel>)->AdapterSubcatLevel(listSubCat,get()) }
            //
            factory <SortRepository>{ SortRepositoryImpl(RemoteSortDataSource(get())) }
            viewModel{(sortId:Int)->SortViewModel(sortId,get())}
            factory { (sortAmazing:List<ResponseSortAmazing>)->AdapterAllAmazing(sortAmazing,get()) }

            //
            factory<DetailsProductRepository> {
                DetailsProductRepositoryImpl(
                    RemoteDetilasProductDataSource(get())
                )
            }
            viewModel { (id: Int) -> DetailsViewModel(id, get()) }
            factory { (gallery: List<ImagesItem>) -> AdapterImageGallery(gallery, get()) }
            factory { (colors: List<ProductColorsItem>) -> AdapterColor(colors) }
            factory { (sizes: List<ProductSizeItem>) -> AdapterSizeProduct(sizes) }
            factory { (properties: List<PropertiesItem>) -> AdapterProperties(properties) }
            factory { (commnet: List<CommentsItem>) -> AdapterCommnet(commnet) }
            factory { (similars: List<SimilarItem>) -> AdapterSimilar(similars, get()) }
            //
            factory<HistoryChartRepository> {
                HisortChartRepositoryImpl(
                    RmoteHistoryChartDataSource(
                        get()
                    )
                )
            }
            viewModel { (id: Int) -> HistoryViewMolde(id, get()) }
            factory<CategoryComapreRepository> { CategoryComapreRepositoryImpl(RemoteCategoryComapreDataSource(get())) }
            viewModel { (id: Int) -> CategoryCoampreViewModel(id, get()) }
            factory { (categoryCompare:List<ResponseCategoryCompare>)-> AdapterCategoryCompare(categoryCompare,get()) }
            //
            factory <CompareProductRepository>{CompareProductRepositoryImpl(RemoteCompareDataSource(get()))  }
            viewModel{(bundle:Bundle)-> CompareProductViewModel(bundle,get())}
            factory { (parent:List<PropertyItemProduct>)-> AdapterCompareParent(parent) }

            //
            factory<PropertyRepository> {PropertyRepositoryImpl(RemotePropertyDataSource(get())) }
            viewModel{ (id:Int)->PropertyViewModel(id,get())}
            factory { (property: List<ResponseProperty>) -> AdapterProperty(property) }
            //
            factory <ShowCommentRepository>{ ShowCommnetRepositpryImpl(
                RemoteShowCommnetDataSource((get()))
            ) }
            viewModel { (id:Int)->ShowRatingViewModel(id,get()) }
            factory { (ratings: List<ResponseRating>) -> AdapterRatingCommnet(ratings) }
            factory { (commnets: List<Resps>) -> AdapterShowCommnet(commnets) }
            factory { (score: List<ScoreItems>) -> AdapterScoreInsert(score) }


            //
            single<SharedPreferences> { this@App.getSharedPreferences("user", MODE_PRIVATE)  }
            factory <AuthRepository>{AuthRepositoryImpl(RemoteAuthDataSource(get()),AuthLocalDataSource(get()))  }
            viewModel { AuthViewMolde(get()) }
            //

            single<InsertCommnetRepository>{InsertCommnetRepositoryImpl(RemoteInsertDataSource(get()))}
            viewModel { (id:Int)->InsertCommnetViewModel(id,get()) }
            //
            factory <CategoryRepository>{ CategoryRepositoryImpl(RemoteCategoryDataSource(get()))  }
            viewModel { CategoryViewModel(get()) }
            factory { (listCatgeoty:List<SubcatItem>)-> AdapterCategory(listCatgeoty,get()) }
            //
            factory<SubCatRepository> { SubcatRepositryImpl(RemoteSubCatDataSource(get())) }
            viewModel { (catId:Int)->SubCatViewModel(catId,get()) }
            factory { (brandS:List<ResponseBrands>)->AdapterBrands(brandS,get()) }
            factory { (cat:List<ResponsePopularCat>)->AdapterPopularCat(cat,get()) }
            factory { (subCat:List<ResponseSubCat>)->AdapterSubCat(subCat,get()) }
            //
            factory<BrandBannerRepository> { BrandBannerRepositoryImpl(RemoteBrandBannerDataSource(get())) }
            viewModel { (brandId:Int)->BrandBannerViewModel(brandId,get()) }
            factory { (brandProduct:List<ResponseBrandProduct>)-> AdapterBrandProduct(brandProduct,get()) }
            //CartFragment
            factory<CartListRepository> { CartListRepositpryImpl(RemoteCartListDataSource(get())) }
            viewModel { CartListViewModel(get ()) }
            factory { (cartItem:ResponseCartList)-> AdapterCartItem(cartItem,get()) }
            viewModel { MainViewModel(get()) }
            //
            factory<CheckOutListRepository> {CheckOutListRepositpryImpl(RemoteCheckOutDataSource(get()))  }
            viewModel{CheckOutListViewModel(get())}
            factory { (cartCheckOut:List<ProductItemDeliveriesItem>)-> AdapterCheckOutProduct(cartCheckOut,get()) }
            //
            factory <AddressRepository>{AddressRepositoryImpl(RemoteAddressDataSource(get()))  }
            viewModel { AddressViewModel(get()) }
            factory { (addresse:List<ResponseAddress>)-> AdapterShowAddress(addresse) }
            //
            factory<OrderHistoryRepository> {OrderHistoryRepositoryImpl(RemoteOrderHistory(get()))  }
            viewModel{OrderHistoryViewModel(get())}
            factory { (order:List<ResponseOrderHistory>)->AdapterOrderHistory(get(),get()) }
            //
            factory<DetilasOrderRepository> {DetilasOrderRepositoryImpl(RemoteDetailsOrederDataSource(get()))  }
            viewModel { (refId:String)->DetilasOrderViewModel(refId,get()) }
            factory { (detialsOrder:List<OrderDetailItem>)->AdapterProductDetilsOrder(detialsOrder,get()) }
            //
            factory <FavouriteRepository>{FavouriteRepositoryImpl(RemoteFavouriteDataSource(get()))  }
            viewModel { FavouriteViewModel(get()) }
            factory { (favourite:List<ResponseFavourite>)->AdapterFavourite(favourite,get()) }
            //
            factory <InfoRespository>{InfoRepositoryImpl(RemoteInfoDartaSource(get()))  }
            viewModel { InfoViewModel(get()) }
            //
            factory <SearchRepository>{ SearchRepositoryImpl(RemoteSearchDataSource(get())) }
            viewModel { SearchViewModel(get()) }
            factory { (partOne:List<Part1Item>)->AdapterPartOne(partOne) }
            factory { (partTwo:List<Part2Item>)->AdapterPartTwo(partTwo,get()) }


        }

        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

        val authRepository : AuthRepository =  get()
        authRepository.loadToken()

    }

}