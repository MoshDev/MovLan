package space.ersan.movlan.app

interface ComponentProvider<T> {

  fun getComponent(): T
}