package com.example.novinshop_project.api

import com.example.novinshop_project.data.*
import com.example.novinshop_project.feature.auth.TokenContainer
import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    //Home
    @GET("/s/newshop/home/banners.php")
    fun getBanners(): Single<List<ResponseBanners>>

    @GET("/s/newshop/home/general_cat.php")
    fun getCategoryHome(): Single<List<ResponseCategoryHome>>

    @GET("/s/newshop/home/amazing.php")
    fun getAmazingProduct(): Single<List<ResponseAmazingProduct>>

    @GET("/s/newshop/home/Popular_products.php")
    fun getPopularProduct(): Single<List<ResponsePopular>>

    @GET("/s/newshop/home/banners_part2.php")
    fun getBannersType(): Single<List<ResponseBannersType>>

    @GET("/s/newshop/home/subcat_level.php")
    fun getSubCatLevel(@Query("genaral_id") subCatId: Int): Single<List<ResponseSubCatLevel>>

    @GET("/s/newshop/products/all_amazing_products.php?sort=")
    fun getAllAmazing(@Query("sort") sortId: Int): Single<List<ResponseSortAmazing>>

    //DetailsProducts
    @GET("/s/newshop/products/product.php")
    fun getDetailsProduct(@Query("id") productId: Int): Single<ResponseDetailsProduct>

    @GET("/s/newshop/products/price_history.php")
    fun getHistoryChart(@Query("product_id") productId: Int): Single<List<ResponseHistoryPrice>>

    @GET("/s/newshop/compare/compare_categories.php")
    fun getCategoryComapre(@Query("procuct_id") productId: Int): Single<List<ResponseCategoryCompare>>

    @GET("/s/newshop/compare/compare.php")
    fun compareProduct(
        @Query("product1") productIdOne: Int,
        @Query("product2") productIdTwo: Int
    ): Single<ResponseCompare>

    //
    @GET("/s/newshop/products/product_technical_properties.php")
    fun getProperty(@Query("product_id") productId: Int): Single<List<ResponseProperty>>

    //Score
    @GET("/s/newshop/products/scores.php")
    fun getShowRatingCommnet(@Query("product_id") productId: Int): Single<List<ResponseRating>>

    @GET("/s/newshop/products/show_comments.php")
    fun getShowCommnet(@Query("product_id") productId: Int): Single<List<Resps>>

    //Auth
    @FormUrlEncoded
    @POST("/s/newshop/auoth/check_user.php")
    fun checkUser(@Field("mobile_phone") phone: String): Single<ResponseCheckUser>

    @FormUrlEncoded
    @POST("/s/newshop/auoth/register.php")
    fun registerUser(
        @Field("mobile_phone") phone: String,
        @Field("name_family") name: String
    ): Single<ResponseRegister>

    @FormUrlEncoded
    @POST("/s/newshop/auoth/login.php")
    fun login(@Field("mobile_phone") phone: String): Single<ResponseLogin>

    //BookMark
    @FormUrlEncoded
    @POST("/s/newshop/products/add_bookmark.php")
    fun addToBookMark(@Field("product_id") productId: Int): Single<ResponseAddBookmark>

    //Commnet
    @FormUrlEncoded
    @POST("/s/newshop/products/add_comment.php")
    fun insertComment(
        @Field("product_id") productId: Int,
        @Field("content") content: String,
        @Field("title") title: String,
        @Field("positive") posotive: String,
        @Field("negative") negative: String,
        @Field("Advice") advice: String
    ): Single<ResponseInsertCommnet>

    @FormUrlEncoded
    @POST("/s/newshop/products/add_profesional_comment.php")
    fun insertCommentPro(
        @Field("product_id") productId: Int,
        @Field("content") content: String,
        @Field("title") title: String,
        @Field("positive") posotive: String,
        @Field("negative") negative: String,
        @Field("Advice") advice: String,
        @Field("score") score: String
    ): Single<ResponseInsertCommnet>

    @GET("/s/newshop/products/comment_type.php")
    fun getStatusScore(@Query("product_id") idProduct:Int): Single<ResponseScoreInsert>

    @GET("/s/newshop/products/scores.php")
    fun getScoreComment(@Query("product_id") idProduct:Int): Single<List<ResponseScoreItemCommnet>>

    //AddToCart
    @FormUrlEncoded
    @POST("/s/newshop/basket/add_to_cart.php")
    fun addToCart(
        @Field("product_id") productId: Int, @Field("color_id") colorId: Int,
        @Field("size_id") sizeId: Int
    ): Single<ResponseInsertCommnet>

    //Category
    @GET("/s/newshop/category/cetegories.php")
    fun getCategory(): Single<List<ResponseCategory>>

    @GET("/s/newshop/category/subcats.php")
    fun getSubCat(@Query("sub_id") catId: Int): Single<List<ResponseSubCat>>

    @GET("/s/newshop/category/brands.php")
    fun getBrands(@Query("subcat1_id") catId: Int): Single<List<ResponseBrands>>

    @GET("/s/newshop/category/Popular_cat.php")
    fun getPopularCat(@Query("subcat1_id") catId: Int): Single<List<ResponsePopularCat>>

    //
    @GET("/s/newshop/products/brand_banners.php")
    fun getBannerBrand(@Query("brand_id") brandId: Int): Single<ResponseBrandBanner>

    @GET("/s/newshop/products/brand_products.php")
    fun getBrandProduct(@Query("brand_id") brandId: Int): Single<List<ResponseBrandProduct>>

    //Cart
    @GET("/s/newshop/basket/basket_list.php")
    fun getCartList(): Single<ResponseCartList>

    @GET("/s/newshop/home/basket_count.php")
    fun getCountItem(): Single<ResponseCount>

    @FormUrlEncoded
    @POST("/s/newshop/basket/remove_from_basket.php")
    fun removeItem(@Field("cart_item_id") itemId: Int): Single<ResponseMessage>

    @FormUrlEncoded
    @POST("/s/newshop/basket/change_count.php")
    fun changeCountItem(
        @Field("cart_item_id") itemId: Int,
        @Field("count") countItem: Int
    ): Single<ResponseChangeCountItem>

    @GET("/s/newshop/basket/checkout_list.php")
    fun checkOutList(): Single<ResponseCheckOutList>

    //
    @GET("/s/newshop/basket/show_addresses.php")
    fun getAddress(): Single<List<ResponseAddress>>

    @POST("/s/newshop/basket/add_new_address.php")
    fun addAddress(@Body address: JsonObject): Single<ResponseAddAddress>

    @GET("/s/newshop/basket/wallet_chackout.php")
    fun insertTrancation(
        @Query("reciver_id") reciveId: String,
        @Query("ref_id") refId: String,
        @Query("shipping_price") shippingPrice: String,
        @Query("payable_price") payBalePrice: String
    ): Single<ResponseTrancation>

    @GET("/s/newshop/profile/get_order_history.php")
    fun getOrderHistory(): Single<List<ResponseOrderHistory>>

    @FormUrlEncoded
    @POST("/s/newshop/profile/get_order_detaile.php")
    fun getDetailsOrder(@Field("ref_id") refId: String): Single<ResponseDetailsOrder>

    //
    @GET("/s/newshop/profile/show_bookmarks.php")
    fun getFavourite(): Single<List<ResponseFavourite>>

    //
    @GET("/s/newshop/profile/show_profile.php")
    fun getInfoUser(): Single<ResponseInfoUser>

    //
    @FormUrlEncoded
    @POST("/s/newshop/search/search_resualt.php")
    fun serach(@Field("search") searchKeyword: String): Single<ResponseSearch>

}

fun retrofitApi(): ApiService {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            if (TokenContainer.token != null) {
               newRequest.addHeader("Authorization", TokenContainer.token)
              //  newRequest.addHeader("Authorization", "a581ca145ab1c942b0edbe0fae0cf4f82c456849bafba9df046e10ed41d78247e10c4f4d1630834143")
            }
            newRequest.method(oldRequest.method(), oldRequest.body())
            return@addInterceptor it.proceed(newRequest.build())
        }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://rezamotahari1375.ir")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(ApiService::class.java)
}