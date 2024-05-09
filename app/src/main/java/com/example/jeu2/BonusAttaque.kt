package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class BonusAttaque(): Drawable, Object() {


    override fun draw(canvas: Canvas){
        val red = 0x1D
        val green = 0xE9
        val blue = 0xB6
        val tealFunkyInt = Color.rgb(red, green, blue)

        val paint = Paint().apply { color = tealFunkyInt }
        canvas.drawRect(rO, paint)
    }

    override fun affectPlayer(player: Player) {
        player.ajoutAttaque()
    }

}