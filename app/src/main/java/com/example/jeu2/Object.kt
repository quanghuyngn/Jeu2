package com.example.jeu2

import android.graphics.RectF

abstract class Object: GameObject(positionX = Math.random()*1000, positionY = Math.random()*1000) {

    //attributs
    val largeur = 70F
    val longueur = 70F
    val rO = RectF(
        positionX.toFloat(), positionY.toFloat(), (positionX + largeur).toFloat(),
        (positionY+longueur).toFloat()
    )
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

        fun createRandomObject(): Int{
            return (Math.random()*3).toInt()
        }

    }

    override fun update() {
        positionX+=0
    }
    abstract fun affectPlayer(player: Player)

}