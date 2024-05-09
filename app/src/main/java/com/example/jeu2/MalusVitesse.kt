package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class MalusVitesse(): Drawable, Object() {

    override fun draw(canvas: Canvas){
        val paint = Paint().apply { color = Color.MAGENTA }
        canvas.drawRect(rO, paint)
    }

    override fun affectPlayer(player: Player) {
        player.retraitVitesse()
    }
}