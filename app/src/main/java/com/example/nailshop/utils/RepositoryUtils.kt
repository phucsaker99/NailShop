package com.example.nailshop.utils

import android.content.Context
import com.example.nailshop.data.resource.repository.NailShopRepository
import com.example.nailshop.data.resource.local.NailShopLocalDataSource
import com.example.nailshop.data.resource.local.database.NailShopDatabase
import com.example.nailshop.data.resource.remote.NailShopRemoteDataSource

object RepositoryUtils {

    fun getRepository(context: Context): NailShopRepository {
        val nailShopDatabase = NailShopDatabase.getInstance(context)
        val local = nailShopDatabase.let { NailShopLocalDataSource.getInstance(it) }
        val remote = NailShopRemoteDataSource.getInstance()
        return NailShopRepository.getInstance(
            local,
            remote
        )
    }
}
