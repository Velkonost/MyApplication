package com.velkonost.sample.myapplication

import com.velkonost.sample.myapplication.data.dish.FakeDishRepository
import com.velkonost.sample.myapplication.domain.dish.DishRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DishRepositoryTest {

    private lateinit var dishRepository: DishRepository

    @Before
    fun setup() {
        dishRepository = FakeDishRepository()
    }

    @Test
    fun testGettingDishes() {
        runTest(UnconfinedTestDispatcher()) {
            val items = dishRepository.getDishes()
            assertEquals(12, items.size)
            assertEquals("Бургер \"Америка\"", items[0].name)
            assertEquals("Бургер \"Мексика\"", items[1].name)
            assertEquals("Бургер \"Русский\"", items[2].name)
        }
    }
}