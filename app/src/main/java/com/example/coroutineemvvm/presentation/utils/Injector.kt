package com.example.coroutineemvvm.presentation.utils

import android.content.Context
import com.example.coroutineemvvm.presentation.data.datasource.AppDataBase
import com.example.coroutineemvvm.presentation.data.repositories.FlowerRepository
import com.example.coroutineemvvm.presentation.data.repositories.FlowerRepositoryImpl
import com.example.coroutineemvvm.presentation.domain.interactor.FlowerInteractor
import com.example.coroutineemvvm.presentation.domain.interactor.FlowerInteractorImpl
import com.example.coroutineemvvm.presentation.presentation.ui.FlowerViewModel

interface ViewModelFactoryProvider {
    fun providePlantListViewModelFactory(context: Context): FlowerViewModel
}

val Injector: ViewModelFactoryProvider
    get() = currentInjector

private object DefaultViewModelProvider : ViewModelFactoryProvider {
    private fun getPlantRepository(context: Context): FlowerRepository {
        return FlowerRepositoryImpl.getInstance(
            plantDao(context),
            plantService()
        )
    }

    private fun getPlantInteractor(context: Context): FlowerInteractor {
        return FlowerInteractorImpl.getInstance(getPlantRepository(context))
    }

    private fun plantService() = NetworkService()

    private fun plantDao(context: Context) =
        AppDataBase.getInstance(context.applicationContext).plantDao()


    override fun providePlantListViewModelFactory(context: Context): FlowerViewModel {
        val interactor = getPlantInteractor(context)
        return FlowerViewModel(interactor)
    }
}

private object Lock

@Volatile
private var currentInjector: ViewModelFactoryProvider =
    DefaultViewModelProvider