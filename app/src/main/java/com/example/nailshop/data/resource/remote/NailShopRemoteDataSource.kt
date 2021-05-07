package com.example.nailshop.data.resource.remote

import com.example.nailshop.data.model.*
import com.example.nailshop.data.resource.remote.api.OnDataCallback
import com.example.nailshop.data.resource.repository.NailShopDataSource
import com.example.nailshop.utils.Retrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NailShopRemoteDataSource : NailShopDataSource.Remote {

    override fun getNailList(callback: OnDataCallback<List<Nail>>) {
        Retrofit.provideNailShopApi().getNailList().enqueue(object : Callback<List<Nail>> {
            override fun onFailure(call: Call<List<Nail>>, t: Throwable) {
                callback.onFail(t as Exception)
            }

            override fun onResponse(call: Call<List<Nail>>, response: Response<List<Nail>>) {
                if (!response.isSuccessful) return
                callback.onSuccess(response.body()!!.toMutableList())
            }
        })
    }

    override fun getManicuristList(callback: OnDataCallback<List<Manicurist>>) {
        Retrofit.provideNailShopApi().getManicuristList()
            .enqueue(object : Callback<List<Manicurist>> {
                override fun onFailure(call: Call<List<Manicurist>>, t: Throwable) {
                    callback.onFail(t as Exception)
                }

                override fun onResponse(
                    call: Call<List<Manicurist>>,
                    response: Response<List<Manicurist>>
                ) {
                    if (!response.isSuccessful) return
                    callback.onSuccess(response.body()!!.toMutableList())
                }
            })
    }


    override fun getStoreList(callback: OnDataCallback<List<Store>>) {
        Retrofit.provideNailShopApi().getStoreList().enqueue(object : Callback<List<Store>> {
            override fun onFailure(call: Call<List<Store>>, t: Throwable) {
                callback.onFail(t as Exception)
            }

            override fun onResponse(call: Call<List<Store>>, response: Response<List<Store>>) {
                if (!response.isSuccessful) return
                callback.onSuccess(response.body()!!.toMutableList())
            }
        })
    }


    override fun getBillByIdUser(id: Int, callback: OnDataCallback<List<Bill>>) {
        Retrofit.provideNailShopApi().getBillByIdUser(id).enqueue(object : Callback<List<Bill>> {
            override fun onFailure(call: Call<List<Bill>>, t: Throwable) {
                callback.onFail(t as Exception)
            }

            override fun onResponse(call: Call<List<Bill>>, response: Response<List<Bill>>) {
                if (!response.isSuccessful) return
                callback.onSuccess(response.body()!!.toMutableList())
            }
        })
    }

    override fun getTimeByBill(manicuristList: String, callback: OnDataCallback<List<Bill>>) {
        Retrofit.provideNailShopApi().getTimeByBill(manicuristList)
            .enqueue(object : Callback<List<Bill>> {
                override fun onFailure(call: Call<List<Bill>>, t: Throwable) {
                    callback.onFail(t as Exception)
                }

                override fun onResponse(call: Call<List<Bill>>, response: Response<List<Bill>>) {
                    if (!response.isSuccessful) return
                    callback.onSuccess(response.body()!!.toMutableList())
                }
            })
    }

    override fun getAccountList(callback: OnDataCallback<List<Account>>) {
        Retrofit.provideNailShopApi().getAccountList().enqueue(object : Callback<List<Account>> {
            override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                callback.onFail(t as Exception)
            }

            override fun onResponse(call: Call<List<Account>>, response: Response<List<Account>>) {
                if (!response.isSuccessful || response.body().isNullOrEmpty()) return
                callback.onSuccess(response.body()!!.toMutableList())
            }
        })
    }

    override fun insertAccount(account: Account, callback: OnDataCallback<String>) {
        Retrofit.provideNailShopApi().insertAccount(account.username, account.password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    call.toString()
                    callback.onSuccess(call.toString())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    callback.onFail(t as Exception)
                }
            })
    }

    override fun insertBill(bill: Bill, callback: OnDataCallback<String>) {
        bill.apply {
            Retrofit.provideNailShopApi().insertBill(
                listManicurist,
                idUser,
                listNail,
                imageNail,
                store,
                address,
                date,
                money,
                status
            ).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    callback.onFail(t as Exception)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    call.toString()
                    callback.onSuccess(call.toString())
                }
            })
        }
    }

    override fun deleteBill(bill: Bill, callback: OnDataCallback<String>) {
        Retrofit.provideNailShopApi().deleteBill(bill.id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onFail(t as Exception)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body().toString())
                }
            }
        })
    }

    override fun updateAccount(account: Account, callback: OnDataCallback<String>) {
        Retrofit.provideNailShopApi().updateAccount(
            account.id,
            account.name,
            account.username,
            account.password,
            account.sdt,
            account.gender)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    callback.onFail(t as Exception)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body().toString())
                    }
                }
            })
    }


    companion object {
        private var instance: NailShopRemoteDataSource? = null
        fun getInstance() =
            instance ?: instance ?: NailShopRemoteDataSource().also {
                instance = it
            }
    }
}
