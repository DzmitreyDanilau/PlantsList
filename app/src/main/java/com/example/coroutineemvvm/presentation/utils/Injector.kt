package com.example.coroutineemvvm.presentation.utils

import PlantListViewModelFactory
import android.content.Context
import com.example.coroutineemvvm.presentation.data.datasource.AppDataBase
import com.example.coroutineemvvm.presentation.data.repositories.PlantRepositoryImpl
import com.example.coroutineemvvm.presentation.domain.interactor.FlowerInteractor
import com.example.coroutineemvvm.presentation.domain.interactor.FlowerInteractorImpl

interface ViewModelFactoryProvider {
    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory
}

val Injector: ViewModelFactoryProvider
    get() = currentInjector

private object DefaultViewModelProvider : ViewModelFactoryProvider {
    private fun getPlantRepository(context: Context): PlantRepositoryImpl {
        return PlantRepositoryImpl.getInstance(
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

    override fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val interactor = getPlantInteractor(context)
        return PlantListViewModelFactory(interactor)
    }
}

private object Lock

@Volatile
private var currentInjector: ViewModelFactoryProvider =
    DefaultViewModelProvider
