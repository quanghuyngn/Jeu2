package com.example.jeu2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat



// la clase game gere les differents états des objets et les affiche
class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback,Drawable {

    //attributs
    private var joystickPointerId: Int = 0
    private val gameLoop: GameLoop


    // création des différents objets du jeu
    private val joystick = Joystick.Builder()
        .setPosition(400.00, 750.00)
        .setRayonExt(70.00)
        .setRayonInt(50.00)
        .build()

    private val player= Player(1000.0,500.0,joystick)  //création d'un objet joueur en initialisant sa poisition a 1000, 500
    private var enemyList = ArrayList<Enemy>()
    private var sortList = ArrayList<Sort>()
    private var objectList = ArrayList<Object>()
    private val bossInstance = Boss(player)
    private val barreDeVie = Vie(player,enemyList,bossInstance)
    private val GameOver = gameOver()
    private val GameWinner = gameWinner()

    //lance la gameloop
    init {
        holder.addCallback(this)
        gameLoop = GameLoop(this, holder)
        Score.registerObserver(bossInstance)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }



    // affiche les différents éléments sur l'écran
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawUPS(canvas)
        player.draw(canvas)
        joystick.draw(canvas)
        for (enemy in enemyList){
            if (enemy is Drawable){
                enemy.draw(canvas)
            }
        }
        for (sort in sortList){
            sort.draw(canvas)
        }
        for (obj in objectList) {
            if (obj is Drawable){
                obj.draw(canvas)
            }
        }
        barreDeVie.draw(canvas)

        if (player.returnVie()<=0){
            GameOver.draw(canvas)
            restartGame()
        }
        Score.draw(canvas)

        if (Boss.isBoss){
            bossInstance.draw(canvas)
        }
        if(bossInstance.returnVie()<=0){
            GameWinner.draw(canvas)
            restartGame()
        }
    }


    //gere les interactions de l'utilisateur avec l'écran
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            // L'utilisateur commence à toucher l'écran
            MotionEvent.ACTION_DOWN,MotionEvent.ACTION_POINTER_DOWN -> { //action down pour le premier doigt qui touche l'écran et action pointer down pour ceux qui quittent momentaneemnt

                if (joystick.getIsPressed()){

                    sortList.add(Sort(player))
                }

                else if (joystick.isPressed(event.x.toDouble(), event.y.toDouble())) {
                    joystickPointerId = event.getPointerId(event.actionIndex)   //enregistre l'identifiant du joystick
                    joystick.setIsPressed(true)
                }
                else{
                    sortList.add(Sort(player))
                }
                return true
            }
            // L'utilisateur déplace son doigt sur l'écran
            MotionEvent.ACTION_MOVE -> {
                // Si le joystick est pressé
                if (joystick.getIsPressed()) {
                    // Mettre à jour la position du joystick
                    joystick.setActuator(event.x.toDouble(), event.y.toDouble())
                }
                return true

            }
            // L'utilisateur arrête de toucher l'écran
            MotionEvent.ACTION_UP,MotionEvent.ACTION_POINTER_UP -> {
                if (joystickPointerId == event.getPointerId(event.actionIndex)) { //verifie si on lache bien le joystick et pas autre part sur l'écran
                    // Marquer le joystick comme n'étant pas pressé
                    joystick.setIsPressed(false)
                    // Réinitialiser l'actuateur du joystick
                    joystick.resetActuator()
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }


    //affiche les updates/sec a l'écran
    fun drawUPS(canvas : Canvas){
        val averageUPS : String = gameLoop.averageUPS.toString()
        val paint = Paint()
        val color : Int = ContextCompat.getColor(context, R.color.magenta)
        paint.color = color
        paint.textSize = 50F
        canvas.drawText("UPS : $averageUPS", 100F, 100F, paint)
    }
    fun restartGame() {
        // Réinitialisation des paramètres du jeu
        player.reset() // Réinitialise la position du joueur
        enemyList.clear() // Efface la liste des ennemis
        objectList.clear()
        sortList.clear()
        Score.reset()

        gameLoop.startLoop()

    }


    //update tous les éléments du jeu
    fun update() {

        // si le jeu n'a plus de vie l'update du jeu s'arrete
        if (player.returnVie()<=0 || bossInstance.returnVie()<=0 ){
            return
        }




        player.update()
        joystick.update()

        //on va verifier si d'abord on a pas atteint le stade du jeu ou le boss doit apparaitre et
        //  verifier alors si chacun des enemies est readytoSpawn

        if (Boss.isBoss == false){
            if (FastEnemy.readyToSpawn()){
                enemyList.add(FastEnemy(player))
            }
            else if (StrongEnemy.readyToSpawn()){
                enemyList.add(StrongEnemy(player))
            }
            else if (Object.readyToSpawn()){
                when(Object.createRandomObject()) {
                    0 -> objectList.add(BonusVie())
                    1 -> objectList.add(BonusAttaque())
                    2 -> objectList.add(MalusVitesse())
                }
            }
        }




        for (enemy in enemyList) {
            enemy.update()
        }

        for (sort in sortList){
            sort.update()
        }

        if (Boss.isBoss){
            bossInstance.update()
        }
        if(Boss.readyToHEAL()){
            bossInstance.Heal()
        }


        // Vérification de collision avec les ennemis (mobs)
        val iteratorEnemi = enemyList.iterator()

        while (iteratorEnemi.hasNext()) {
            val mob = iteratorEnemi.next()
            if (mob.isColliding(mob, player)) {
                mob.attaque(player) // Le mob attaque le joueur s'il y a collision
                iteratorEnemi.remove() // Supprime le mob de la liste
            } else if (mob.returnVie() <= 0) {
                iteratorEnemi.remove() // Si la vie du mob est nulle ou négative, supprime le mob
                Score.incremente(10) // Incrémente le compteur de 50
                if (Score.returnCompteur() >= 100) {
                    Score.notifyObserver()// Si le compteur atteint 100, active le boss
                }
            }

            // Vérification de collision avec les sorts
            val iteratorSpell = sortList.iterator()
            while (iteratorSpell.hasNext()) {
                val spell = iteratorSpell.next()
                if (mob.isColliding(mob, spell)) {
                    iteratorSpell.remove() // Supprime le sort de la liste
                    player.attaque(mob) // Le joueur attaque le mob
                }
            }
        }

        //Vérification de collision avec le boss (indépendamment des ennemis)
        val iteratorSpellBoss = sortList.iterator()
        while (iteratorSpellBoss.hasNext()) {
            val spell = iteratorSpellBoss.next()
            if (bossInstance.isColliding(bossInstance, spell)) {
                iteratorSpellBoss.remove() // Supprime le sort de la liste
                player.attaque(bossInstance) // Le joueur attaque le boss
            }
        }
        if(bossInstance.isColliding(bossInstance,player)){
            bossInstance.attaque(player)
        }

        // Vérifiez si le joueur entre en collision avec chaque objet
        val iterator = objectList.iterator()
        while (iterator.hasNext()) {
            val obj = iterator.next()
            if (player.isColliding(obj,player)) {
                // Si une collision est détectée, appliquez l'effet de l'objet sur le joueur
                obj.affectPlayer(player)
                // Supprimez l'objet de la liste des objets

                iterator.remove()
            }
        }
    }
}