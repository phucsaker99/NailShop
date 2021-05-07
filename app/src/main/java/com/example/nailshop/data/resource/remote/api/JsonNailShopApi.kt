package com.example.nailshop.data.resource.remote.api

import com.example.nailshop.data.model.*
import com.example.nailshop.utils.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface JsonNailShopApi {
    @GET(NailApi.GET_NAIL)
    fun getNailList(): Call<List<Nail>>

    @GET(BillApi.GET_BILL_BY_ID_USER)
    fun getBillByIdUser(
        @Query("idUser") idUser: Int
    ): Call<List<Bill>>

    @GET(BillApi.GET_CHECK_TIME_BY_BILL)
    fun getTimeByBill(
        @Query("listManicurist") listManicurist: String
    ): Call<List<Bill>>

    @GET(ManicuristApi.GET_MANICURIST)
    fun getManicuristList(): Call<List<Manicurist>>

    @GET(AccountApi.GET_ACCOUNT)
    fun getAccountList(): Call<List<Account>>

    @GET(StoreApi.GET_STORE)
    fun getStoreList(): Call<List<Store>>

    @FormUrlEncoded
    @POST(BillApi.DELETE_BILL)
    fun deleteBill(
        @Field("id") id: Int
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST(AccountApi.INSERT_ACCOUNT)
    fun insertAccount(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST(AccountApi.UPDATE_ACCOUNT)
    fun updateAccount(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("sdt") sdt: Int,
        @Field("gender") gender: Boolean
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST(BillApi.INSERT_BILL)
    fun insertBill(
        @Field("listManicurist") listManicurist: String,
        @Field("idUser") idUser: Int,
        @Field("listNail") listNail: String,
        @Field("imageNail") imageStore: String,
        @Field("store") store: String,
        @Field("address") address: String,
        @Field("date") date: String,
        @Field("money") money: Double,
        @Field("status") status: Boolean
    ): Call<ResponseBody>
}
