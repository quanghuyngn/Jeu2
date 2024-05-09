package com.example.jeu2

import kotlin.math.pow
import kotlin.math.sqrt

//superclasse "ultime" qui concerne tous les elements du jeu ayant tous une position x et y sur l'écran
abstract class GameObject(protected var positionX : Double,protected var positionY : Double) {


    //getter pour les attributs positions afin que seuls les instances des
    //sous classes de GameObjets permettent l'acces a ces attributs.
    fun returnPositionX(): Double{

        return positionX
    }

    fun returnPositionY(): Double{

        return positionY
    }

    //renvoie la distance entre 2 GameObject
    fun getDistanceBetweenP1AndP2(p1 : GameObject, p2 : GameObject) : Double{
        return sqrt((p1.positionX-p2.positionX).pow(2)+(p1.positionY-p2.positionY).pow(2))
    }

    //Chaque GameObject sera mit a jour individuellement et d'une manière différente. POLYMORPHISME
    abstract fun update()

}