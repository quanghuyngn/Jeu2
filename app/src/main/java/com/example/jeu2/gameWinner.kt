package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.jeu2.Drawable

//classe gameWinner affichant simplement un gameWinner
class gameWinner(): Drawable {
    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.RED
        paint.textSize = 150F


        canvas.drawText("Vous avez gagné !",500F,500F,paint)
    }
}