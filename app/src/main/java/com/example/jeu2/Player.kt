package com.example.jeu2
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.jeu2.Drawable
import com.example.jeu2.Joystick
import kotlin.math.pow
import kotlin.math.sqrt

class Player(positionX : Double, positionY: Double,//réferent vers joystick
             private val joystick: Joystick,) : Drawable, Entite(positionX,positionY) {
    private val INITIAL_POINT_DE_VIE = 100
    private val INITIAL_VITESSE_MAX = 10.0
    private val INITIAL_POINT_ATTAQUE = 30


    init {
        pointAttaque = 30
    }


    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.YELLOW
        canvas.drawCircle(positionX.toFloat(), positionY.toFloat(), 30F, paint)
    }


    //Les deplacements du joueurs sont conditionnés par la position du joystick
    override fun update() {

        //update la vitesse
        vitesseX = joystick.getActuatorX() * VITESSE_MAX
        vitesseY = joystick.getActuatorY() * VITESSE_MAX

        //update la position
        positionX = positionX + vitesseX
        positionY = positionY + vitesseY

        //update la direction
        if (vitesseX != 0.00 || vitesseY != 0.00) {

            val distance = sqrt((0 - vitesseX).pow(2) + (0 - vitesseY).pow(2))
            directionX = vitesseX / distance
            directionY = vitesseY / distance
        }


    }
    fun reset() {
        pointDeVie = INITIAL_POINT_DE_VIE
        VITESSE_MAX = INITIAL_VITESSE_MAX
        pointAttaque = INITIAL_POINT_ATTAQUE
    }


    fun ajoutVie() {

        pointDeVie += 30

    }


    fun ajoutAttaque() {

        pointAttaque += 10

    }

    fun retraitVitesse() {

        VITESSE_MAX -= 5
    }
}