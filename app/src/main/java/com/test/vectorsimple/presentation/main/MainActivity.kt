package com.test.vectorsimple.presentation.main

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.test.vectorsimple.R

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter lateinit var mMainPresenter: MainPresenter
    @BindView(R.id.glSurface) lateinit var mVectorSurface: GLSurfaceView
    @BindView(R.id.addFigureButton) lateinit var mAddFigureButton: Button
    @BindView(R.id.saveFigureButton) lateinit var mSaveFigureButton: Button
    @BindView(R.id.deleteFigureButton) lateinit var mDeleteFigureButton: Button
    @BindView(R.id.pointTooltip) lateinit var mPointTooltip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.addFigureButton)
    fun addFigureClick() {
        mMainPresenter.onAddFigureClicked()
    }

    @OnClick(R.id.saveFigureButton)
    fun saveFigureClick() {
        mMainPresenter.onSaveFigureClicked()
    }

    @OnClick(R.id.deleteFigureButton)
    fun deleteFigureClick() {
        mMainPresenter.onDeleteFigureClicked()
    }

    override fun onShowAddFigureButton(show: Boolean) {
        mAddFigureButton.visibility = when(show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun onShowSaveFigureButton(show: Boolean) {
        mSaveFigureButton.visibility = when(show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun onShowDeleteFigureButton(show: Boolean) {
        mDeleteFigureButton.visibility = when(show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun onShowPointTooltip(show: Boolean) {
        mPointTooltip.visibility = when(show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun onEnableSaveFigureButton(enabled: Boolean) {
        mSaveFigureButton.isEnabled = enabled
    }
}
