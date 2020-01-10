package com.example.coroutineemvvm.presentation.presentation.ui

import androidx.lifecycle.*
import com.example.coroutineemvvm.presentation.domain.interactor.FlowerInteractor
import com.example.coroutineemvvm.presentation.model.GrowZone
import com.example.coroutineemvvm.presentation.model.NoGrowZone
import com.example.coroutineemvvm.presentation.model.Plant
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlantsListViewModel(private val interactor: FlowerInteractor) : ViewModel() {

    private val _snackbar = MutableLiveData<String?>()
    val snackbar: LiveData<String?>
        get() = _snackbar

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val growZone = MutableLiveData<GrowZone>(NoGrowZone)

    val plants: LiveData<List<Plant>> = growZone.switchMap { growZone ->
        if (growZone == NoGrowZone) {
            interactor.getPlants()
        } else {
            interactor.getPlantsWithGrowZone(growZone.number)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        growZone.value = GrowZone(num)
        launchDataLoad { interactor.getPlants() }
    }

    fun onSnackbarShown() {
        _snackbar.value = null
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: Throwable) {
                _snackbar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }
}