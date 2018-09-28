package com.test.vectorsimple.presentation.gl

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder

class GLShape {
    companion object {
        private const val COORDS_PER_VERTEX = 2
        private const val CIRCLE_POINTS = 32

        fun drawCircle(x: Float, y: Float, radius: Float, matrix: FloatArray, color: FloatArray) {
            val shapeCoords = mutableListOf<Float>()
            for (angle in 1..CIRCLE_POINTS) {
                val percent = (angle / (CIRCLE_POINTS - 1).toFloat());
                val radians = (percent * 2 * Math.PI);

                // Vertex position
                shapeCoords.add((x + radius * Math.cos(radians)).toFloat())
                shapeCoords.add((y + radius * Math.sin(radians)).toFloat())
            }
            drawPolygon(shapeCoords.toFloatArray(), matrix, color)
        }

        fun drawPolygon(points: FloatArray, matrix: FloatArray, color: FloatArray) {
            val vertexCount: Int = points.size / COORDS_PER_VERTEX

            val program = GLShaderUtil.createProgram().also {
                GLES20.glUseProgram(it)
            }

            val vertexBuffer = ByteBuffer.allocateDirect(points.size * 4).run {
                order(ByteOrder.nativeOrder())
                asFloatBuffer().apply {
                    put(points)
                    position(0)
                }
            }

            val positionHandle = GLES20.glGetAttribLocation(program, "vPosition").also {
                GLES20.glVertexAttribPointer(
                        it,
                        COORDS_PER_VERTEX,
                        GLES20.GL_FLOAT,
                        false,
                        0,
                        vertexBuffer
                )
            }

            GLES20.glGetUniformLocation(program, "vColor").also {
                GLES20.glUniform4fv(it, 1, color, 0)
            }

            GLES20.glGetUniformLocation(program, "uMVPMatrix").also {
                GLES20.glUniformMatrix4fv(it, 1, false, matrix, 0)
            }

            GLES20.glEnableVertexAttribArray(positionHandle)
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount)
            GLES20.glDisableVertexAttribArray(positionHandle)
        }
    }
}