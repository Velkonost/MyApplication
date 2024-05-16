package com.velkonost.sample.myapplication.domain.dish.usecase

import com.velkonost.sample.myapplication.domain.dish.DishRepository
import com.velkonost.sample.myapplication.domain.entity.Dish
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DeleteDishesUseCase
@Inject constructor(
    private val dishRepository: DishRepository
) {

    suspend operator fun invoke(items: List<Dish>) = coroutineScope {
        for (item in items) {
            dishRepository.deleteDish(item.id)
        }
    }

}