package space.ersan.movlan.test.app.builder

import space.ersan.movlan.app.builder.GsonModule
import space.ersan.movlan.app.builder.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class, GsonModule::class])
interface TestAppComponent
