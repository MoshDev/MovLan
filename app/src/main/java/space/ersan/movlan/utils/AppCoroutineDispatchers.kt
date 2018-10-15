package space.ersan.movlan.utils

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.asCoroutineDispatcher
import java.util.concurrent.Executors

open class AppCoroutineDispatchers(
    val IO: CoroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher(),
    val NETWORK: CoroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher(),
    val UI: CoroutineDispatcher = kotlinx.coroutines.experimental.android.UI
)