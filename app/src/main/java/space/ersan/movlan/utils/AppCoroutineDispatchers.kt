package space.ersan.movlan.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

open class AppCoroutineDispatchers(
    val IO: CoroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher(),
    val NETWORK: CoroutineDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher(),
    val UI: CoroutineDispatcher = Dispatchers.Main
)