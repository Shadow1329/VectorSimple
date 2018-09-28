package com.test.vectorsimple.presentation.gl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLRenderer : GLSurfaceView.Renderer {

    private val mShapes = mutableListOf<GLVectorObject>()
    private val mMtrxProjectionAndView = FloatArray(16)

    fun addPoint(x: Float, y: Float) {
        mShapes[0].addPoint(x, y)
    }

    private fun calculateScreenMatrix(left: Float, right: Float, top: Float, bottom: Float) {
        val mtrxProjection = FloatArray(16)
        val mtrxView = FloatArray(16)

        Matrix.orthoM(mtrxProjection, 0, left, right, bottom, top, -1f, 1f)
        Matrix.setLookAtM(mtrxView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        Matrix.multiplyMM(mMtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        mShapes.forEach {
            it.draw()
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
        mShapes.add(GLVectorObject(ArrayList(), mMtrxProjectionAndView, true))
    }
}