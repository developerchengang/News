package com.doumiao.presentation

import android.app.Application
import com.doumiao.presentation.di.mNetworkModules
import com.doumiao.presentation.di.mRepositoryModules
import com.doumiao.presentation.di.mUseCaseModules
import com.doumiao.presentation.di.mViewModel
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
            listOf(mNetworkModules,
                mViewModel,
                mUseCaseModules,
                mRepositoryModules))
    }

}