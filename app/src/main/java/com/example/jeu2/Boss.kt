package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.jeu2.GameLoop
import com.example.jeu2.Drawable

//Le boss est le dernier enemie qu'il faut battre
class Boss(player: Player): Drawable, Enemy(player),Observer {


    //attributs statique de la meme maniere que les differents types d enemies
    //verifie a chaque update si le boss est pret a se soigner
    companion object{
        var isBoss : Boolean = false

        private val HEAL_PER_MINUTE = 10.00
        private val UPDATES_PER_HEAL = GameLoop.MAX_UPS /(HEAL_PER_MINUTE /60.00)
        private var updateUntilNextHEAL = UPDATES_PER_HEAL

        fun readyToHEAL() : Boolean {
            return if(updateUntilNextHEAL <= 0) {
                updateUntilNextHEAL += UPDATES_PER_HEAL
                true
            } else {
                updateUntilNextHEAL -= 1
                false
            }
        }
        private fun setIsBoss(){   //dit que le Boss est pret à apparaitre
            isBoss = true
        }

    }

    init {
        pointAttaque = 1
    }

    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.CYAN
        canvas.drawCircle(positionX.toFloat(),positionY.toFloat(),60F,paint)
    }

    fun Heal(){             //se soigne
        pointDeVie+=10
    }

    override fun isNotified() {
        setIsBoss()
    }

}