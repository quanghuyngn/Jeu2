package com.example.jeu2

// La superclasse Enemy englobe le comportement general de tous les enemies peu importe leur type
abstract class Enemy( private val player: Player): Entite(positionX = Math.random()*1000, positionY = Math.random()*1000) {

    // initialisation des attributs de base
    init {
        vitesseX = 0.00
        vitesseY = 0.00
        VITESSE_MAX = super.VITESSE_MAX * 0.5
        pointAttaque = 10
    }


    // a chaque update, l'enemie ajuste sa position et sa vitesse

    override fun update() {
        //*************************************************************************
        // ajuste la vitesse de l'enemi afin de se dirigier vers le joueur*********
        //*************************************************************************


        //calcul des composantes du vecteur Joueur-Enemy
        val DistanceToPlayerX = player.returnPositionX() - positionX
        val DistanceToPlayerY = player.returnPositionY() - positionY

        //Norme du vecteur Joueur-Enemy
        val DistanceToPlayer = getDistanceBetweenP1AndP2(this, player)

        //Normalisation du vecteur Joueur-Enemy afin d'avoir la direction
        val DirectionX = DistanceToPlayerX / DistanceToPlayer
        val DirectionY = DistanceToPlayerY / DistanceToPlayer

        //vecteur vitesse
        vitesseX = DirectionX * VITESSE_MAX
        vitesseY = DirectionY * VITESSE_MAX

        //update de la position
        positionX = positionX + vitesseX
        positionY = positionY + vitesseY
    }



}