package com.example.jeu2

interface Subject {
    fun registerObserver(o: Entite)
    fun unregisterObserver(o: Entite)
    fun notifyObserver()
}