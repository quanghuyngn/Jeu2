package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.jeu2.Drawable

class Sort(caster : Player) : Drawable, GameObject(positionX = caster.returnPositionX(), positionY = caster.returnPositionY()){

    val vitesse_x = caster.returnDirectionX()*caster.returnVitesse()*2
    val vitesse_y = caster.returnDirectionY()*caster.returnVitesse()*2

   override fun update() {

        positionX += vitesse_x
        positionY += vitesse_y
    }

    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.GREEN
        canvas.drawCircle(positionX.toFloat(), positionY.toFloat(), 30F, paint)
    }

}