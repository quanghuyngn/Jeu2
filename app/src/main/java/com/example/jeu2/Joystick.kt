package com.example.jeu2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.jeu2.GameObject
import kotlin.math.pow
import kotlin.math.sqrt
import com.example.jeu2.Drawable

class Joystick private constructor(
    positionX: Double,
    positionY: Double,
    rayonExt: Double,
    rayonInt: Double) : Drawable, GameObject(positionX, positionY) {

    //attributs
    private val positionXCercleExt = positionX
    private val positionYCercleExt = positionY
    private var positionXCercleInt = positionX
    private var positionYCercleInt = positionY
    private val rayonCercleExt = rayonExt
    private val rayonCercleInt = rayonInt
    private val cercleIntPaint = Paint()
    private val cercleExtPaint = Paint()
    private var isPressed = false
    private var actuatorX = 0.00
    private var actuatorY = 0.00

    init {
        cercleExtPaint.color = Color.GRAY
        cercleIntPaint.color = Color.BLUE
        cercleExtPaint.style = Paint.Style.FILL_AND_STROKE
        cercleIntPaint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(positionXCercleExt.toFloat(), positionYCercleExt.toFloat(), rayonCercleExt.toFloat(), cercleExtPaint)
        canvas.drawCircle(positionXCercleInt.toFloat(), positionYCercleInt.toFloat(), rayonCercleInt.toFloat(), cercleIntPaint)
    }

    //verifie si le joueur a toucher la zone du joystick
    fun isPressed(touchPositionX: Double, touchPositionY: Double): Boolean {
        val joystickCenterToTouchDistance = distanceCalculator(touchPositionX, touchPositionY, positionXCercleExt, positionYCercleExt)
        return joystickCenterToTouchDistance < rayonCercleExt
    }

    //le joystick est dans l'état "pressé"
    fun setIsPressed(isPressed: Boolean) {
        this.isPressed = isPressed
    }


    fun setActuator(touchPositionX: Double, touchPositionY: Double) {
        val distance = distanceCalculator(touchPositionX, touchPositionY, positionXCercleExt, positionYCercleExt)
        if (distance < rayonCercleExt) {
            actuatorX = (touchPositionX - positionXCercleExt) / rayonCercleExt
            actuatorY = (touchPositionY - positionYCercleExt) / rayonCercleExt
        } else {
            actuatorX = (touchPositionX - positionXCercleExt) / distance
            actuatorY = (touchPositionY - positionYCercleExt) / distance
        }
    }

    private fun distanceCalculator(x1: Double, y1: Double, x2: Double, y2: Double): Double {
        return sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2))
    }

    override fun update() {
        positionXCercleInt = positionXCercleExt + actuatorX * rayonCercleExt
        positionYCercleInt = positionYCercleExt + actuatorY * rayonCercleExt
    }

    fun resetActuator() {
        actuatorX = 0.00
        actuatorY = 0.00
    }


    //getter
    fun getActuatorX(): Double {
        return actuatorX
    }

    fun getActuatorY(): Double {
        return actuatorY
    }

    fun getIsPressed(): Boolean {
        return isPressed
    }


    //design pattern builder permettant la fragmentation de la creation de
    //l'objet joystick rendant celui-ci plus modulable
    class Builder {
        private var positionX = 0.0
        private var positionY = 0.0
        private var rayonExt = 0.0
        private var rayonInt = 0.0

        fun setPosition(x: Double, y: Double): Builder {
            positionX = x
            positionY = y
            return this
        }

        fun setRayonExt(rayon: Double): Builder {
            rayonExt = rayon
            return this
        }

        fun setRayonInt(rayon: Double): Builder {
            rayonInt = rayon
            return this
        }

        fun build(): Joystick {
            return Joystick(positionX, positionY, rayonExt, rayonInt)
        }
    }
}