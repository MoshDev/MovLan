package space.ersan.movlan.common.mvp


interface LifeCycleAware {
  fun onVisibilityChangeRequest(visible: Boolean)
}