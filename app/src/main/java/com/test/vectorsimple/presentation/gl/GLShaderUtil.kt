package com.test.vectorsimple.presentation.gl

import android.opengl.GLES20

class GLShaderUtil {
    companion object {
        private const val vertexShaderCode =
                "uniform   mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "void main() {" +
                "  gl_Position = uMVPMatrix * vPosition;" +
                "}"

        private const val fragmentShaderCode =
                "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}"

        fun createProgram(): Int {
            return GLES20.glCreateProgram().also {
                GLES20.glAttachShader(it, loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode))
                GLES20.glAttachShader(it, loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode))
                GLES20.glLinkProgram(it)
            }
        }

        private fun loadShader(type: Int, shaderCode: String): Int {
            return GLES20.glCreateShader(type).also { shader ->
                GLES20.glShaderSource(shader, shaderCode)
                GLES20.glCompileShader(shader)
            }
        }
    }
}