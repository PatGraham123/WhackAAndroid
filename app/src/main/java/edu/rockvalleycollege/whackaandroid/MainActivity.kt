package edu.rockvalleycollege.whackaandroid

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity() {

    var x:Float = 0.00F
    var y:Float = 0.00F
    var random = Random()
    var score:Int = 0
    var timer = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var txtScore = findViewById<TextView>(R.id.txtScore)
        var GameBackGround = findViewById<ConstraintLayout>(R.id.GameCanvas)
        var btnControl = findViewById<Button>(R.id.btnControl)
        var btnImgButton = findViewById<ImageButton>(R.id.imgMole)


        btnImgButton.setTranslationX(-300F)
        btnImgButton.setTranslationY(-300F)

        btnImgButton.visibility = View.INVISIBLE
        btnImgButton.bringToFront()


        btnControl.setOnClickListener{
            if (btnControl.text == "Start"){
                Toast.makeText (this, "Tap Android To Score¡",Toast.LENGTH_SHORT) .show ()
                btnControl.text = "Stop"
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer = Timer()
                timer.schedule(timerTask {changeImage()},3000)
                changeImage()
                btnImgButton.visibility = View.VISIBLE
            }else{
                btnImgButton.visibility = View.INVISIBLE
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer.cancel()
            }

        }

        btnImgButton.setOnClickListener{
            score += 100
            if (score == 1000){
                timer.cancel()
                txtScore.text = "You Have Won!"
                score = 0
                btnControl.text = "Start"
                btnImgButton.visibility = View.INVISIBLE
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
            }else {
                txtScore.text = "Score: " + score.toString()
            }

        }

        GameBackGround.setOnClickListener{
            println("Click")
            score -= 100
            txtScore.text = "Score: " + score.toString()
            if (score == 0 || score == -100){
                btnImgButton.visibility = View.INVISIBLE
                Toast.makeText (this, "Game Over", Toast.LENGTH_SHORT).show();
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer.cancel()
                btnControl.text = "Start"

            }
        }
    }

    fun changeImage(){

        y = ((Math.random () * getScreenHeight()) + 50) .toFloat ()
        x = ((Math.random () * getScreenWidth()) + 50) .toFloat ()
        var imgMole = findViewById<ImageButton>(R.id.imgMole)
        imgMole.setTranslationX(x)
        imgMole.setTranslationY(y)
        timer.schedule(timerTask {changeImage()},1000)
    }
    fun getScreenWidth(): Float {
        return Resources.getSystem().getDisplayMetrics().widthPixels / 1.4F

    }

    fun getScreenHeight(): Float {
        return Resources.getSystem().getDisplayMetrics().heightPixels / 1.4F
    }
}