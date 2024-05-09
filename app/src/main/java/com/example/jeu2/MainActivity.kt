package com.example.jeu2

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.ActivityInfo




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fenetre = getWindow()
        fenetre.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        //permet de mettre la fenetre en plein ecran avec la methode setFlags
        // Affichage de la page d'accueil
        setContentView(R.layout.pageaccueil)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            // Lorsque l'utilisateur clique sur le bouton "Start Game",
            // passer à l'activité de jeu
            setContentView(Game(this))
        }
    }
}

