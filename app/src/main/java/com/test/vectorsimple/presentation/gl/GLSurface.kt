package com.test.vectorsimple.presentation.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import com.test.vectorsimple.domain.logic.VectorShape

class GLSurface : GLSurfaceView {

    private val mRenderer = GLRenderer()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setEGLContextClientVersion(2)
        setRenderer(mRenderer)
        renderMode = RENDERMODE_WHEN_DIRTY
    }

    fun updateShapes(shapes: List<VectorShape>) {
        mRenderer.updateShapes(shapes)
        requestRender()
    }
}