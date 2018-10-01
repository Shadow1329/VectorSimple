package com.test.vectorsimple.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.test.vectorsimple.domain.logic.VectorLogic
import com.test.vectorsimple.domain.logic.VectorShape

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), VectorLogic.OnVectorLogicUpdateListener {
    private val mVectorLogic = VectorLogic(this)

    fun onAddShapeClicked() {
        mVectorLogic.addShape()
    }

    fun onSaveShapeClicked() {
        mVectorLogic.saveShape()
    }

    fun onDeleteShapeClicked() {
        mVectorLogic.deleteShape()
    }

    fun onSurfaceSingleTap(x: Float, y: Float) {
        mVectorLogic.handleSingleTap(x, y)
    }

    fun onSurfaceLongPress(x: Float, y: Float) {
        mVectorLogic.handleLongPress(x, y)
    }

    fun onSurfaceMove(x: Float, y: Float) {
        mVectorLogic.handleMove(x, y)
    }

    private fun showEditMode(show: Boolean) {
        viewState.onShowAddShapeButton(!show)
        viewState.onShowSaveShapeButton(show)
        viewState.onShowDeleteShapeButton(show)
        viewState.onShowPointTooltip(show)
    }

    private fun enableSaveButton(enable: Boolean) {
        viewState.onEnableSaveShapeButton(enable)
    }

    override fun onSetEditMode(edit: Boolean) {
        showEditMode(edit)
    }

    override fun onAllowSave(save: Boolean) {
        enableSaveButton(save)
    }

    override fun onUpdateShapes(shapes: List<VectorShape>) {
        viewState.onUpdateShapes(shapes)
    }
}