package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.jeu2.Drawable

//classe gameOver affichant simplement un gameOver
class gameOver(): Drawable {
    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.RED
        paint.textSize = 300F


        canvas.drawText("Game Over",500F,500F,paint)
    }
}