package com.example.jeu2

import android.graphics.Canvas


// L'interface drawable permet l'implementation individuelle a toutes les classes du jeu
//qui sont affichées à l'écran
interface Drawable {
    abstract fun draw(canvas: Canvas)
}