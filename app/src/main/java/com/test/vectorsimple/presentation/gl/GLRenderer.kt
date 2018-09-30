package com.test.vectorsimple.presentation.gl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import com.test.vectorsimple.domain.logic.VectorShape
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLRenderer : GLSurfaceView.Renderer {

    private var mShapes: List<VectorShape>? = null
    private val mMatrixProjectionAndView = FloatArray(16)

    fun updateShapes(shapes: List<VectorShape>) {
        mShapes = shapes
    }

    private fun calculateScreenMatrix(left: Float, right: Float, top: Float, bottom: Float) {
        val matrixProjection = FloatArray(16)
        val matrixView = FloatArray(16)

        Matrix.orthoM(matrixProjection, 0, left, right, bottom, top, -1f, 1f)
        Matrix.setLookAtM(matrixView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        Matrix.multiplyMM(mMatrixProjectionAndView, 0, matrixProjection, 0, matrixView, 0)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        mShapes?.forEach {
            GLShape.drawShape(it, mMatrixProjectionAndView)
        }
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        calculateScreenMatrix(0f, width.toFloat(), 0f, height.toFloat())
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
        GLES20.glEnable(GLES20.GL_BLEND)
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA)
    }
}