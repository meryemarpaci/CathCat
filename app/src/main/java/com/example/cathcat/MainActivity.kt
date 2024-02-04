package com.example.cathcat

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.cathcat.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var score =0
    var imageArray = ArrayList<ImageView>()
    var runnable = kotlinx.coroutines.Runnable {  }
    var handler= Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //image array
        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)

        hideImages()

        object : CountDownTimer(15800,1000) {
            override fun onFinish() {
                binding.t3.text="Time : 0"
                handler.removeCallbacks(runnable)
                for(img in imageArray){
                    img.visibility=View.INVISIBLE
                }
                //alert dialog
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Hehe Game Over")
                alert.setMessage("Cat wants you to play again")
                alert.setPositiveButton("Accept",DialogInterface.OnClickListener{dialogInterface, i ->
                    // restart
                    val intentMain = intent
                    finish()
                    startActivity(intentMain)
                })
                alert.setNegativeButton("Annoy the cat",DialogInterface.OnClickListener{dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"Game over",Toast.LENGTH_LONG).show()
                })
                alert.show()
            }

            override fun onTick(p0: Long) {
                binding.t3.text="Time : ${p0/1000}"
            }

        }.start()

    }
    fun hideImages(){
        runnable = object : Runnable {
            override fun run() {
                for (img in imageArray){
                    img.visibility=View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(12) //12 dahil degil
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,280)
            }

        }

        handler.post(runnable)



    }
    fun i1(view: View){
        score = score+1
        binding.scoreT.text="Score : ${score}"
    }
}