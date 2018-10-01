package com.test.vectorsimple.domain.logic

import java.util.*

class VectorShape(points: MutableList<VectorPoint>) {
    var mPoints = points
    var mEdit = false
    val mColor = generateColor()

    fun addPoint(x: Float, y: Float) {
        mPoints.add(VectorPoint(x, y))
        mPoints = sortVertices().toMutableList()
    }

    fun setPoint(index: Int, x: Float, y: Float) {
        mPoints[index].mX = x
        mPoints[index].mY = y
        mPoints = sortVertices().toMutableList()
    }

    fun removePoint(index: Int) {
        mPoints.removeAt(index)
        mPoints = sortVertices().toMutableList()
    }

    fun getPointAt(x: Float, y: Float): Int {
        for (i in 0 until mPoints.size) {
            if (isInCircle(x, y, mPoints[i].mX, mPoints[i].mY)) {
                return i
            }
        }
        return -1
    }

    fun isInside(x: Float, y: Float): Boolean {
        val polygonNumber = mPoints.size
        var j = polygonNumber - 1
        var c = false

        for (i in 0 until polygonNumber) {
            if (
                    (((mPoints[i].mY <= y) && ( y < mPoints[j].mY)) || ((mPoints[j].mY <= y) && (y < mPoints[i].mY))) &&
                    (x > (mPoints[j].mX - mPoints[i].mX) * (y - mPoints[i].mY) / (mPoints[j].mY - mPoints[i].mY) + mPoints[i].mX)) {
                c = !c
            }
            j = i
        }
        return c
    }

    private fun isInCircle(x: Float, y: Float, x0: Float, y0: Float): Boolean {
        val dx = Math.abs(x - x0)
        val dy = Math.abs(y - y0)
        return ( dx * dx + dy * dy <= DRAG_RADIUS * DRAG_RADIUS )
    }

    private fun findCentroid(): VectorPoint {
        return when(mPoints.size) {
            0 -> VectorPoint(0f, 0f)
            else -> mPoints[0]
        }
    }

    private fun sortVertices(): List<VectorPoint>  {
        val center = findCentroid()
        return mPoints.sortedWith(kotlin.Comparator { o1, o2 ->
            val a1 = (Math.toDegrees(Math.atan2((o1.mX - center.mX).toDouble(), (o1.mY - center.mY).toDouble())) + 360) % 360
            val a2 = (Math.toDegrees(Math.atan2((o2.mX - center.mX).toDouble(), (o2.mY - center.mY).toDouble())) + 360) % 360
            (a1 - a2).toInt()
        })
    }

    companion object {
        const val DRAG_RADIUS = 50f
        const val POINT_RADIUS = 8f

        private fun generateColor(): FloatArray {
            val r = randomFloat(0.55f, 0.9f)
            val g = randomFloat(0.55f, 0.9f)
            val b = randomFloat(0.55f, 0.9f)
            return floatArrayOf(r, g, b, 1.0f)
        }

        private fun randomFloat(min: Float, max: Float): Float {
            val rand = Random()
            return rand.nextFloat() * (max - min) + min

        }
    }
}