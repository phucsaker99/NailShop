package com.example.nailshop.utils

import com.example.nailshop.data.resource.remote.api.JsonNailShopApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object LinkBaseApi {
    const val NAIL_SHOP_API = "https://phucnailshop.000webhostapp.com/webservicenails/"
}

object NailApi {
    private const val TABLE = "Nail/"
    const val GET_NAIL = "${TABLE}getNailList.php"
    const val INSERT_NAIL = "${TABLE}insertNail.php"
    const val UPDATE_NAIL = "${TABLE}updateNail.php"
    const val DELETE_NAIL = "${TABLE}deleteNail.php"
}

object StoreApi {
    private const val TABLE = "Store/"
    const val GET_STORE = "${TABLE}getStoreList.php"
    const val INSERT_STORE = "${TABLE}insertStore.php"
    const val UPDATE_STORE = "${TABLE}updateStore.php"
    const val DELETE_STORE = "${TABLE}deleteStore.php"
}

object ManicuristApi {
    private const val TABLE = "Manicurist/"
    const val GET_MANICURIST = "${TABLE}getManicuristList.php"
    const val INSERT_MANICURIST = "${TABLE}insertManicurist.php"
    const val UPDATE_MANICURIST = "${TABLE}updateManicurist.php"
    const val DELETE_MANICURIST = "${TABLE}deleteManicurist.php"
}

object BillApi {
    private const val TABLE = "Bill/"
    const val GET_BILL_BY_ID_USER = "${TABLE}getBillsByIdUser.php"
    const val GET_CHECK_TIME_BY_BILL = "${TABLE}checkTimeByBill.php"
    const val INSERT_BILL = "${TABLE}insertBill.php"
    const val UPDATE_BILL = "${TABLE}updateBill.php"
    const val DELETE_BILL = "${TABLE}deleteBill.php"
}

object AccountApi {
    private const val TABLE = "Account/"
    const val GET_ACCOUNT = "${TABLE}getAccountList.php"
    const val INSERT_ACCOUNT = "${TABLE}insertAccount.php"
    const val UPDATE_ACCOUNT = "${TABLE}updateAccount.php"
    const val DELETE_ACCOUNT = "${TABLE}deleteAccount.php"
}

object Retrofit {

    fun provideNailShopApi(): JsonNailShopApi {
        val gson = GsonBuilder().serializeNulls().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(LinkBaseApi.NAIL_SHOP_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(JsonNailShopApi::class.java)
    }
}
