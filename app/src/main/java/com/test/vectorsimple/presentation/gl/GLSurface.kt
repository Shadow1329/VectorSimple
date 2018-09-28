package com.test.vectorsimple.presentation.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent

class GLSurface : GLSurfaceView {

    private val mRenderer = GLRenderer()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setEGLContextClientVersion(2)
        setRenderer(mRenderer)
        renderMode = RENDERMODE_WHEN_DIRTY
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        val x: Float = e.x
        val y: Float = e.y

        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                mRenderer.addPoint(x, y)
                requestRender()
            }
        }
        return true
    }
}