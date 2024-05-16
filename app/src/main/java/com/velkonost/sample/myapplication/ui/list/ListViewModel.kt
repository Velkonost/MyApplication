package com.velkonost.sample.myapplication.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.velkonost.sample.myapplication.domain.dish.usecase.DeleteDishesUseCase
import com.velkonost.sample.myapplication.domain.dish.usecase.GetDishesListUseCase
import com.velkonost.sample.myapplication.domain.entity.Dish
import com.velkonost.sample.myapplication.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel
@Inject constructor(
    private val getDishesListUseCase: GetDishesListUseCase,
    private val deleteDishesUseCase: DeleteDishesUseCase
) : BaseViewModel() {

    private val _dishes = MutableLiveData<List<Dish>>()
    val dishes: LiveData<List<Dish>> get() = _dishes

    fun loadDishes() {
        vmScope.launch(Dispatchers.IO) {
            val items = getDishesListUseCase()
            _dishes.postValue(items)
        }
    }

    fun removeSelectedDishes() {
        vmScope.launch {
            val currentItems = _dishes.value ?: emptyList()
            val selectedItems = withContext(Dispatchers.Default) {
                currentItems.filter { it.selected }
            }

            launch(Dispatchers.Default) {
                deleteDishesUseCase(selectedItems)
            }
            
            val remainingItems = currentItems - selectedItems
            _dishes.postValue(remainingItems)
        }
    }

    fun Dish.updateState(value: Boolean) {
        val currentItems = _dishes.value ?: emptyList()

        val newItems = currentItems.toMutableList()
        newItems.firstOrNull { this.id == it.id }?.let {
            it.selected = value
            _dishes.postValue(newItems)
        }
    }

}