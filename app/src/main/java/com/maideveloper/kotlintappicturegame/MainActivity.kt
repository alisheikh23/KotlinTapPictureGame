package com.maideveloper.kotlintappicturegame

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var score = 0
    var handler:Handler=Handler()
    var runnable:Runnable= Runnable {  }
    var imageArray= ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray= arrayListOf(imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8)



        Game()




    }




    fun Game(){

        object: CountDownTimer(10000,1000){
            override fun onFinish() {

                TimeText.text= "Time: 0"
                score=0
                ScoreText.text="Score: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                var alertDialog=AlertDialog.Builder(this@MainActivity)
                alertDialog.setTitle("Game Over")
                alertDialog.setMessage("Do you want to play again?")
                alertDialog.setPositiveButton("Yes, Play Again") { dialog: DialogInterface?, which: Int ->
                    Game()
                }
                alertDialog.setNegativeButton("No"){dialog: DialogInterface?, which: Int -> finish() }
                alertDialog.show()


            }

            override fun onTick(millisUntilFinished: Long) {
                TimeText.text= "Time:"+millisUntilFinished/1000
            }

        }.start()

        //use to hide images for given interval here 0.5 sec or 500ms
        runnable= object: Runnable{
            override fun run() {

                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random=Random()//use to select random image
                val index=random.nextInt(8-0)//0-8 values store randomly in index variable
                imageArray[index].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)

            }

        }
        handler.post(runnable)
    }
    fun RightImageTap(view: View){

        score++
        ScoreText.text= "Score:"+score

    }
    fun WrongImageTap(view: View){
        score--
        ScoreText.text= "Score:"+score
    }
}
