package space.ersan.movlan.utils

sealed class Maybe<T> {
  class Some<T>(val value: T) : Maybe<T>()
  class Error<T>(val error: Exception) : Maybe<T>()
}