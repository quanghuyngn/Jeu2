package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


class BonusVie(): Drawable, Object() {


    override fun draw(canvas: Canvas){
        val paint = Paint().apply { color = Color.BLUE }
        canvas.drawRect(rO, paint)
    }

    override fun affectPlayer(player: Player) {
        player.ajoutVie()
    }
}