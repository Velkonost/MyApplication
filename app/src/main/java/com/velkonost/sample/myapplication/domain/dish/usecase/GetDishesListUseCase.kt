package com.velkonost.sample.myapplication.domain.dish.usecase

import com.velkonost.sample.myapplication.domain.dish.DishRepository
import com.velkonost.sample.myapplication.domain.entity.Dish
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDishesListUseCase
@Inject constructor(
    private val dishRepository: DishRepository,
) {

    suspend operator fun invoke(): List<Dish> = withContext(Dispatchers.IO) {
        dishRepository.getDishes()
    }

}