package com.test.vectorsimple.domain.logic

class VectorLogic(listener: OnVectorLogicUpdateListener) {
    private val mShapes = mutableListOf<VectorShape>()
    private val mListener = listener
    private var mEditingShape = -1

    fun addShape() {
        if (!isEditing()) {
            mShapes.add(VectorShape(ArrayList()))
            startEditShape(mShapes.size - 1)
        }
    }

    fun saveShape() {
        if (isEditing()) {
            finishEditShape(false)
        }
    }

    fun deleteShape() {
        if (isEditing()) {
            finishEditShape(true)
        }
    }

    fun handleSingleTap(x: Float, y: Float) {
        if (isEditing()) {
            addPoint(x, y)
        } else {
            checkSelection(x, y)
        }
    }

    fun handleDoubleTap(x: Float, y: Float) {
        if (isEditing()) {
            deletePoint(x, y)
        }
    }

    fun handleMove(x: Float, y: Float) {
        if (isEditing()) {
            movePoint(x, y)
        }
    }

    private fun checkSelection(x: Float, y: Float) {
        for (i in 0 until mShapes.size) {
            if (mShapes[i].isInside(x, y)) {
                startEditShape(i)
                break
            }
        }
    }

    private fun addPoint(x: Float, y: Float) {
        mShapes[mEditingShape].addPoint(x, y)
        mListener.onUpdateShapes(mShapes)
        checkSavable()
    }

    private fun movePoint(x: Float, y: Float) {
        val pointIndex = mShapes[mEditingShape].getPointAt(x, y)
        if (pointIndex >= 0) {
            mShapes[mEditingShape].setPoint(pointIndex, x, y)
            mListener.onUpdateShapes(mShapes)
        }
    }

    private fun deletePoint(x: Float, y: Float) {
        val pointIndex = mShapes[mEditingShape].getPointAt(x, y)
        if (pointIndex >= 0) {
            mShapes[mEditingShape].removePoint(pointIndex)
            mListener.onUpdateShapes(mShapes)
            checkSavable()
        }
    }

    private fun checkSavable() {
        if (mShapes[mEditingShape].mPoints.size > 2) {
            mListener.onAllowSave(true)
        } else {
            mListener.onAllowSave(false)
        }
    }

    private fun isEditing(): Boolean {
        return mEditingShape >= 0
    }

    private fun startEditShape(shapeNumber: Int) {
        mEditingShape = shapeNumber
        mShapes[mEditingShape].mEdit = true
        checkSavable()
        mListener.onSetEditMode(true)
        mListener.onUpdateShapes(mShapes)
    }

    private fun finishEditShape(delete: Boolean) {
        if (delete) {
            mShapes.removeAt(mEditingShape)
        } else {
            mShapes[mEditingShape].mEdit = false
        }
        mEditingShape = -1
        mListener.onSetEditMode(false)
        mListener.onUpdateShapes(mShapes)
    }

    interface OnVectorLogicUpdateListener {
        fun onSetEditMode(edit: Boolean)
        fun onAllowSave(save: Boolean)
        fun onUpdateShapes(shapes: List<VectorShape>)
    }
}