package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.jeu2.Drawable



//Score qui augmente a chaque fois q'un enemie est tué
//Score est un singleton car on veut s'asurer qu'il y a une seule instance de score a chaque fois qui peut etre modifie
object Score: Drawable,Subject{
    //attributs
    private var compteur = 0
    private var observers: MutableList<Observer> = mutableListOf()

    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.RED
        paint.textSize = 50F
        canvas.drawText("Score : $compteur",1000F,50F,paint)

    }

    fun incremente(point : Int){
        compteur+=point
    }

    fun returnCompteur() : Int{
        return compteur
    }

    override fun registerObserver(o: Entite) {
        if(o is Observer)
            observers.add(o)
    }

    override fun unregisterObserver(o: Entite) {
        if(o is Observer)
            observers.remove(o)
    }

    override fun notifyObserver() {
        for(observer in observers){
            observer.isNotified()
        }
    }
    fun reset() {
        compteur = 0
    }


}