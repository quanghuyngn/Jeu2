package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log

class Vie(private val player: Player, private var enemy: ArrayList<Enemy>, private val bossInstance : Boss):
    Drawable {

    //attributs
    private val largeur = 100
    private val hauteur = 20
    private val distanceJoueur = 40
    private val largeur_boss = 800F
    private val hauteur_boss = 60F

    override fun draw(canvas: Canvas) {

        //Barre de vie du joueur

        //bord
        val paint = Paint()
        paint.color = Color.GRAY

        val x = player.returnPositionX().toFloat()
        val y = player.returnPositionY().toFloat()

        canvas.drawRect(x-largeur/2,y-distanceJoueur-hauteur,x+largeur/2,y-distanceJoueur,paint)

        //vie
        val paint2 = Paint()
        paint2.color = Color.GREEN
        val pourcentageDeVie = player.returnVie() / Entite.VIE_MAX.toFloat()
        canvas.drawRect(x-largeur/2,y-distanceJoueur-hauteur,(x-largeur/2)+largeur*pourcentageDeVie,y-distanceJoueur,paint2)


        //barre de vie de l'enemi
        for (mob in enemy){

            val paint3 = Paint()
            paint3.color = Color.GRAY

            val x2 = mob.returnPositionX().toFloat()
            val y2 = mob.returnPositionY().toFloat()

            canvas.drawRect(x2-largeur/2,y2-distanceJoueur-hauteur,x2+largeur/2,y2-distanceJoueur,paint3)

            //vie
            val paint4 = Paint()
            paint4.color = Color.GREEN
            val percentageDeVie = mob.returnVie() / Entite.VIE_MAX.toFloat()
            canvas.drawRect(x2-largeur/2,y2-distanceJoueur-hauteur,(x2-largeur/2)+largeur*percentageDeVie,y2-distanceJoueur,paint4)

        }


        //barre de vie du boss
        if (Boss.isBoss){
            val paint5 = Paint()
            paint5.color = Color.GRAY



            canvas.drawRect(1100-largeur_boss/2,100F,1100+largeur_boss/2,100F+hauteur_boss,paint5)

            //vie
            val pourcentageBoss = bossInstance.returnVie() / Entite.VIE_MAX.toFloat()
            val paint6 = Paint()
            paint6.color = Color.GREEN
            canvas.drawRect(1100-largeur_boss/2,100F,(1100-largeur_boss/2)+largeur_boss*pourcentageBoss,100F+hauteur_boss,paint6)
            Log.d("TAG",bossInstance.returnVie().toString())

        }

    }

}