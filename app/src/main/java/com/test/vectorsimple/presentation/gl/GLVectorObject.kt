package com.test.vectorsimple.presentation.gl

class GLVectorObject(points: ArrayList<Pair<Float, Float>>, matrix: FloatArray, edit: Boolean) {
    private val mEdit = edit
    private val mPoints = points
    private val mMatrix = matrix
    private val mShapeColor = floatArrayOf(0.0f, 1.0f, 0.0f, 1.0f)
    private val mCircleColor = floatArrayOf(0.0f, 0.5f, 0.8f, 0.3f)
    private val mPointsColor = floatArrayOf(1.0f, 0.0f, 0.0f, 1.0f)

    fun addPoint(x: Float, y: Float) {
        mPoints.add(Pair(x, y))
    }

    fun draw() {
        // Draw shape
        val shapeCoords = mutableListOf<Float>()
        mPoints.forEach {
            shapeCoords.add(it.first)
            shapeCoords.add(it.second)
        }
        GLShape.drawPolygon(shapeCoords.toFloatArray(), mMatrix, mShapeColor)

        if (mEdit) {
            // Draw points
            mPoints.forEach {
                GLShape.drawCircle(it.first, it.second, 50f, mMatrix, mCircleColor)
                GLShape.drawCircle(it.first, it.second, 8f, mMatrix, mPointsColor)
            }
        }
    }
}