package com.shaun.numberguessinggame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.os.Handler
import android.os.SystemClock
import android.view.ViewStructure
import android.widget.Button
import android.widget.EditText
import java.util.*
import kotlin.concurrent.schedule
import android.widget.TextView
import  kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import java.util.concurrent.Delayed
import kotlin.collections.RandomAccess
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private  var secretNumber:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vibe = this@MainActivity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            Hints.setText("This is a Number Guessing Game \n" +
                    " Click on Randomize to Start a new Game")
        var noOfTries=0
        val newNumberListener = View.OnClickListener { v->
            Hints.text = "Start Guessing \n" +
                    "Its in the range 1-100"

             secretNumber = Random().nextInt(100)
         //   Hints.setText(secretNumber.toString())
            noOfTries=0
        }

        val checkListener=View.OnClickListener { v->

            if(secretNumber==0)
                Hints.setText("Please Click the Randomize Button")
            else
            if(userInput.text.isNotEmpty() )
            {
                if(noOfTries ==-1){
                    Hints.setText("You already Guessed it correct,Press Randomize to play Again")
                    return@OnClickListener
                }
                if(noOfTries==-2) {
                    Hints.setText("Game Over,Press Randomize to Play Again")
                return@OnClickListener
                }

                noOfTries++
                if(secretNumber.toString()==userInput.text.toString()) {
                    Hints.setText("Hurray")
                    noOfTries=-1
                }
                else
                {
                    var dummy :Int =0
                    try {
                         dummy =Integer.parseInt(userInput.text.toString())
                    }catch (e:NumberFormatException)
                    {
                        Hints.setText("uh Oh ,what did you enterted?")
                    }


                    var randomGuess = Random().nextInt(7)
                        when(randomGuess){
                            0 -> Hints.setText("Uh Oh! \n")
                            1 -> Hints.setText("I think you're getting close, are you?\n")
                            2 -> Hints.setText("This isn't a easy Task, But can do it\n")
                            3 ->{
                                var dump =secretNumber+Random().nextInt(10)+3
                                 dump = min(100,dump)
                                Hints.setText("OK , Here is a hint, This Number is less than $dump \n")

                            }
                            4 -> Hints.setText("I am getting Bored\n")
                            5-> Hints.setText("I am still Waiting for the correct Number\n")
                            6->{
                                if(noOfTries>=10)
                                    Hints.setText("If you want to Reveal the Number then Enter 0 and press check\n")
                                else
                                    Hints.setText("Try Again Mate\n")
                            }
                        }



                        if(dummy==0)
                        {
                            Hints.setText("You Tried well , $secretNumber was the number")
                            noOfTries=-2
                        }
                        else

                    if(secretNumber<dummy)
                        Hints.append("Guess Little Lower")
                    else
                        Hints.append("Guess Little higher")

                }
            }
            else
            {

                Hints.setText("Enter the Number Please")
            }


        }


        check.setOnClickListener(checkListener)
        newNumber.setOnClickListener(newNumberListener)




    }
}
