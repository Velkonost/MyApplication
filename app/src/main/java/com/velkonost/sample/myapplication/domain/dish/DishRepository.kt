package com.velkonost.sample.myapplication.domain.dish

import com.velkonost.sample.myapplication.domain.entity.Dish

interface DishRepository {

    suspend fun getDishes(): List<Dish>

    suspend fun getDishById(id: String): Dish?

    suspend fun deleteDish(id: String)
}