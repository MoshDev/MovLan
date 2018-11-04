package space.ersan.movlan

import kotlinx.coroutines.Dispatchers
import org.mockito.Mockito
import space.ersan.movlan.utils.AppCoroutineDispatchers

inline fun <reified T> mock() = Mockito.mock(T::class.java)!!

private val nowExecutor = Dispatchers.Unconfined

object TestCoroutineDispatchers : AppCoroutineDispatchers(nowExecutor, nowExecutor, nowExecutor)