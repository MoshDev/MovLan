package space.ersan.movlan.data.source.local

import dagger.Component
import space.ersan.movlan.app.builder.AppComponent
import javax.inject.Scope

@Scope
annotation class ConvertersScope

@Component(dependencies = [AppComponent::class])
@ConvertersScope
interface ConvertersComponent {
  fun inject(obj: GenreTypeConverter)
  fun inject(obj: IntArrayTypeConverter)
}
