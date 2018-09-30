package com.test.vectorsimple.domain.logic

class VectorShape(points: ArrayList<Pair<Float, Float>>) {
    val mPoints = points
    var mEdit = false

    fun addPoint(x: Float, y: Float) {
        mPoints.add(Pair(x, y))
    }

    fun setPoint(index: Int, x: Float, y: Float) {
        mPoints[index] = Pair(x, y)
    }

    fun removePoint(index: Int) {
        mPoints.removeAt(index)
    }

    fun getPointAt(x: Float, y: Float): Int {
        for (i in 0 until mPoints.size) {
            if (isInCircle(x, y, mPoints[i].first, mPoints[i].second)) {
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
                    (((mPoints[i].second <= y) && ( y < mPoints[j].second)) || ((mPoints[j].second <= y) && (y < mPoints[i].second))) &&
                    (x > (mPoints[j].first - mPoints[i].first) * (y - mPoints[i].second) / (mPoints[j].second - mPoints[i].second) + mPoints[i].first)) {
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

    companion object {
        const val DRAG_RADIUS = 50f
        const val POINT_RADIUS = 8f
    }
}