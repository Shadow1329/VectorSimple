package com.test.vectorsimple.presentation.main

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnTouch
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.test.vectorsimple.R
import com.test.vectorsimple.domain.logic.VectorShape
import com.test.vectorsimple.presentation.gl.GLSurface

class MainActivity : MvpAppCompatActivity(), MainView, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    @InjectPresenter lateinit var mMainPresenter: MainPresenter
    @BindView(R.id.glSurface) lateinit var mVectorSurface: GLSurface
    @BindView(R.id.addShapeButton) lateinit var mAddShapeButton: Button
    @BindView(R.id.saveShapeButton) lateinit var mSaveShapeButton: Button
    @BindView(R.id.deleteShapeButton) lateinit var mDeleteShapeButton: Button
    @BindView(R.id.pointTooltip) lateinit var mPointTooltip: TextView
    private lateinit var mGestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        mGestureDetector = GestureDetectorCompat(this, this)
        mGestureDetector.setOnDoubleTapListener(this)
    }

    override fun onShowPress(e: MotionEvent?) {}

    override fun onSingleTapUp(e: MotionEvent?): Boolean { return true }

    override fun onDown(e: MotionEvent?): Boolean { return true }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, p2: Float, p3: Float): Boolean { return true }

    override fun onLongPress(e: MotionEvent?) {}

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean { return true }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        e?.run {
            mMainPresenter.onSurfaceSingleTap(e.x, e.y)
        }
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, p2: Float, p3: Float): Boolean {
        e2?.run {
            mMainPresenter.onSurfaceMove(e2.x, e2.y)
        }
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        e?.run {
            mMainPresenter.onSurfaceDoubleTap(e.x, e.y)
        }
        return true
    }

    @OnClick(R.id.addShapeButton)
    fun addShapeClick() {
        mMainPresenter.onAddShapeClicked()
    }

    @OnClick(R.id.saveShapeButton)
    fun saveShapeClick() {
        mMainPresenter.onSaveShapeClicked()
    }

    @OnClick(R.id.deleteShapeButton)
    fun deleteShapeClick() {
        mMainPresenter.onDeleteShapeClicked()
    }

    @OnTouch(R.id.glSurface)
    fun surfaceTouch(e: MotionEvent): Boolean {
        mGestureDetector.onTouchEvent(e)
        return true
    }

    override fun onShowAddShapeButton(show: Boolean) {
        mAddShapeButton.visibility = when(show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun onShowSaveShapeButton(show: Boolean) {
        mSaveShapeButton.visibility = when(show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun onShowDeleteShapeButton(show: Boolean) {
        mDeleteShapeButton.visibility = when(show) {
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

    override fun onEnableSaveShapeButton(enabled: Boolean) {
        mSaveShapeButton.isEnabled = enabled
    }

    override fun onUpdateShapes(shapes: List<VectorShape>) {
        mVectorSurface.updateShapes(shapes)
    }
}
