package com.velkonost.sample.myapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velkonost.sample.myapplication.domain.dish.usecase.GetDishUseCase
import com.velkonost.sample.myapplication.domain.entity.Dish
import com.velkonost.sample.myapplication.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    private val getDishUseCase: GetDishUseCase
): BaseViewModel() {

    private val _dish = MutableLiveData<Dish>()
    val dish: LiveData<Dish> get() = _dish

    fun load(id: String) {
        vmScope.launch(Dispatchers.IO) {
            getDishUseCase(id)?.let {
                _dish.postValue(it)
            }
        }
    }

}