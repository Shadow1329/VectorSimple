package com.test.vectorsimple.presentation.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.test.vectorsimple.domain.logic.VectorShape

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun onShowAddShapeButton(show: Boolean)
    fun onShowSaveShapeButton(show: Boolean)
    fun onShowDeleteShapeButton(show: Boolean)
    fun onShowPointTooltip(show: Boolean)

    fun onEnableSaveShapeButton(enabled: Boolean)

    fun onUpdateShapes(shapes: List<VectorShape>)
}