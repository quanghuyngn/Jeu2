package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.jeu2.GameLoop
import com.example.jeu2.Drawable

class FastEnemy(player: Player) : Enemy(player), Drawable {

    //changement de la vitesse
    init {
        VITESSE_MAX = super.VITESSE_MAX
    }


    // ici on crée un bloc d'attributs et méthodes statiques car ceux-ci ne vont pas dépendre
    // de l'état des instances mais sont des choses qui sont générals à la classe elle-meme
    // On va definir un nombre de spawn par minutes et d'update par spawn. On definit un nombre
    // d'update avant le prochain spawn qui decroit de 1 a chaque update. Lorsqu'il descend en dessous de
    // zero il se renitialise et la methode renvoie true permettant d'ajouter un enemi"
    companion object{

        private val SPAWN_PER_MINUTE = 10.00
        private val UPDATES_PER_SPAWN = GameLoop.MAX_UPS /(SPAWN_PER_MINUTE /60.00)
        private var updateUntilNextSpawn = UPDATES_PER_SPAWN

        fun readyToSpawn() : Boolean {
            return if(updateUntilNextSpawn <= 0) {
                updateUntilNextSpawn += UPDATES_PER_SPAWN
                true
            } else {
                updateUntilNextSpawn -= 1
                false
            }
        }

    }


    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.RED
        canvas.drawCircle(positionX.toFloat(),positionY.toFloat(),30F,paint)

        val paint2 = Paint()
        paint2.color = Color.BLACK
        paint2.textSize = 30F


        canvas.drawText("RAPIDE",positionX.toFloat(),positionY.toFloat(),paint2)
    }

}