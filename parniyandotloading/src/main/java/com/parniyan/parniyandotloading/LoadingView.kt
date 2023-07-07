package com.parniyan.parniyandotloading

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ArrayRes

class LoadingView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    // Define the default dot properties
    private val defaultDotCount = 5
    private val defaultDotRadius = 8f
    private val defaultDotSpacing = 120f
    private val defaultDotMaxSize = 32f
    private val defaultDotMinSize = 4f
    private val defaultDotColorList = intArrayOf(
        Color.parseColor("#9984D4"),
        Color.parseColor("#9984D4"),
        Color.parseColor("#592E83"),
        Color.parseColor("#592E83"),
        Color.parseColor("#592E83")
    )

    // Define the customizable dot properties
    private var dotCount = defaultDotCount
    private var dotRadius = defaultDotRadius
    private var dotSpacing = defaultDotSpacing
    private var dotMaxSize = defaultDotMaxSize
    private var dotMinSize = defaultDotMinSize
    private var dotColorList = defaultDotColorList

    // Define the dot positions and sizes
    private var dotXList = mutableListOf<Float>()
    private var dotY = 0f
    private var dotSizeList = mutableListOf<Float>()

    // Define the loading state and animators
    private var isLoading = false
    private var animators = mutableListOf<ValueAnimator>()

    // Define the paint object used for drawing
    private val paint = Paint()

    init {
        // Configure the paint object
        paint.isAntiAlias = true

        // Load custom attributes from XML
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.LoadingView)

            dotCount = typedArray.getInt(R.styleable.LoadingView_dotCount, defaultDotCount)
            dotRadius = typedArray.getDimension(R.styleable.LoadingView_dotRadius, defaultDotRadius)
            dotSpacing = typedArray.getDimension(R.styleable.LoadingView_dotSpacing, defaultDotSpacing)
            dotMaxSize = typedArray.getDimension(R.styleable.LoadingView_dotMaxSize, defaultDotMaxSize)
            dotMinSize = typedArray.getDimension(R.styleable.LoadingView_dotMinSize, defaultDotMinSize)
            dotColorList = typedArray.getColorArray(R.styleable.LoadingView_dotColors, defaultDotColorList)

            typedArray.recycle()
        }
    }

    // Utility function to get an array of colors from a color resource
    private fun TypedArray.getColorArray(index: Int, defaultArray: IntArray): IntArray {
        val resourceId = getResourceId(index, 0)
        return if (resourceId != 0) {
            context.resources.getIntArray(resourceId)
        } else {
            defaultArray
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Calculate the positions and sizes of the dots based on the view size
        val dotTotalWidth = (dotCount - 1) * dotSpacing + 2f * dotRadius
        val dotTotalHeight = 2f * dotRadius
        val dotLeft = (w - dotTotalWidth) / 2f
        val dotTop = (h - dotTotalHeight) / 2f
        dotXList.clear()
        for (i in 0 until dotCount) {
            dotXList.add(dotLeft + i * dotSpacing)
            dotSizeList.add(dotRadius)
        }
        dotY = dotTop + dotRadius
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (isLoading) {
            // Draw the loading animation
            for (i in 0 until dotCount) {
                val cx = dotXList[i]
                val cy = dotY
                val animator = animators.getOrNull(i)
                if (animator == null) {
                    // Create a new animator for this dot if it does not exist
                    val animator = ValueAnimator.ofFloat(dotRadius, dotMaxSize, dotRadius).apply {
                        duration = 800
                        repeatCount = ValueAnimator.INFINITE
                        repeatMode = ValueAnimator.RESTART
                        startDelay = i * 100L
                        addUpdateListener {
                            // Update the dot size on each animation frame
                            dotSizeList[i] = it.animatedValue as Float
                            invalidate()
                        }
                    }
                    animators.add(animator)
                    animator.start()
                }
                paint.color = dotColorList[i]
                var dotSize = dotSizeList[i]
                if (dotSize < dotMinSize) {
                    dotSize = dotMinSize
                }
                canvas?.drawCircle(cx, cy, dotSize, paint)
            }
        } else {
            // Draw a staticloading animation
            for (i in 0 until dotCount) {
                val cx = dotXList[i]
                val cy = dotY
                paint.color = dotColorList[i]
                canvas?.drawCircle(cx, cy, dotRadius, paint)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        // Stop all animators when the view is detached from the window
        for (animator in animators) {
            animator.cancel()
        }
        animators.clear()
    }

    fun startLoading() {
        if (!isLoading) {
            isLoading = true
            invalidate()
        }
    }

    fun stopLoading() {
        if (isLoading) {
            isLoading = false
            invalidate()
        }
    }

    fun setDotMinSize(minSize: Float) {
        dotMinSize = minSize
        invalidate()
    }
}