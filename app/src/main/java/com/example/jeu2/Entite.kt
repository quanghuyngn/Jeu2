package com.example.jeu2

import kotlin.math.max
import kotlin.math.min

// la classe entite qui herite de la classe gameobject concerne tous les "personnages" du jeu qui ont une vie , une attaque, etc
abstract class Entite(positionX : Double, positionY : Double) : GameObject(positionX, positionY) {

    //attributs
    companion object{
        val VIE_MAX = 100         //attribut statique qui concerne la classe
        //en general et pas les instances
    }

    protected var pointDeVie = 100
        set(value) {
            field = max(min(value, 100), 0) // Assure que la vie reste comprise entre 0 et 100
        }
        get() = field

    protected var pointAttaque = 10
        set(value) {
            field = max(min(value, 80), 1) // Assure que les points d'attaque restent compris entre 0 et 80
        }
        get() = field

    protected var vitesseX = 0.00
    protected var vitesseY= 0.00
    protected var VITESSE_MAX = 0.00
        set(value) {
            field = max(min(value, 400.00), 10.00) // Assure que la vitesse reste comprise entre 10 et 400
        }
        get() = field

    protected var directionX = 1.00
    protected var directionY = 1.00

    init {
        val PIXEL_SECONDE = 400
        VITESSE_MAX = PIXEL_SECONDE/ GameLoop.MAX_UPS     //le resultat donne des pixels/update soit
        //l'équivalent d'une vitesse
    }

    //blocs de methodes getter garantissant que seul la classe Entite permet l'accès a ses attributs

    fun returnDirectionX(): Double{
        return directionX
    }

    fun returnDirectionY(): Double{
        return directionY
    }

    fun returnVie() : Float{
        return pointDeVie.toFloat()
    }

    fun returnPointAttaque() : Int{
        return pointAttaque
    }

    fun returnVitesse() : Double{
        return VITESSE_MAX
    }

    // verifie la collision entre 2 entites au moyen de la methode getDistanceBetween
    fun isColliding(p1 : GameObject, p2 : GameObject) : Boolean {
        return p1.getDistanceBetweenP1AndP2(p1, p2) < 100
    }

    fun perdVie(degat : Int) {
        pointDeVie -= degat
    }

    fun attaque(mob : Entite) {
        mob.perdVie(pointAttaque)
    }
}