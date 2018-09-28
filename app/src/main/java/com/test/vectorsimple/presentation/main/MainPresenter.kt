package com.test.vectorsimple.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    fun onAddFigureClicked() {
        setEditMode(true)
    }

    fun onSaveFigureClicked() {
        setEditMode(false)
    }

    fun onDeleteFigureClicked() {
        setEditMode(false)
    }

    private fun setEditMode(edit: Boolean) {
        viewState.onShowAddFigureButton(!edit)
        viewState.onShowSaveFigureButton(edit)
        viewState.onShowDeleteFigureButton(edit)
        viewState.onShowPointTooltip(edit)
        //viewState.onEnableSaveFigureButton()
    }
}