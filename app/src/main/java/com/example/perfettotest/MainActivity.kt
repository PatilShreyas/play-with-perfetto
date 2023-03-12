package com.example.perfettotest

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.tracing.trace
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private val statuses = arrayOf("Hello World", "Lorem Ipsum", "Hey developer", "Android is Love")
    private val colors = intArrayOf(Color.BLUE, Color.GRAY, Color.GREEN, Color.WHITE, Color.YELLOW)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        textView.setOnClickListener { changeTextSize() }
        animateTextBackground()
    }

    override fun onResume() {
        super.onResume()
        setStatusText()
        setStatusTextColor()
    }

    private fun animateTextBackground() {
        val animator = ValueAnimator.ofObject(ArgbEvaluator(), Color.BLACK, Color.MAGENTA).apply {
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            duration = 1000
        }
        animator.addUpdateListener {
            textView.setBackgroundColor(it.animatedValue as Int)
        }
        animator.start()
    }

    private fun changeTextSize() = trace("changeTextSize") {
        pretendHeavyComputation()
        textView.textSize = Random.nextInt(20, 60).toFloat()
    }

    private fun setStatusTextColor() {
        textView.setTextColor(getCurrentColor())
    }

    private fun setStatusText() {
        val text = getCurrentStatus()
        textView.text = text
    }

    private fun getCurrentStatus(): String = trace("getCurrentStatus") {
        // This is very heavy task. Just pretend that this is very very heavy!
        pretendHeavyComputation()
        statuses.random()
    }

    private fun getCurrentColor(): Int = trace("getCurrentColor") {
        // This is very heavy task. Just pretend that this is very very heavy!
        pretendHeavyComputation()
        colors.random()
    }

    private fun pretendHeavyComputation() {
        Thread.sleep(Random.nextLong(500, 700))
    }
}