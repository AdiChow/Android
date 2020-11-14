package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
    var PLAYER = true
    var TURN_COUNT =0
    var boradStatus =Array(3){IntArray(3)}
    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(button,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i in board)
        {
            for(button in i)
            {
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        resetBtn.setOnClickListener {
            PLAYER=true
            TURN_COUNT=0
            initializeBoardStatus()
            updateDisplay("Player X Turn")
        }

    }



    private fun initializeBoardStatus() {
       for(i in 0..2)
       {
           for(j in 0..2)
           {
               boradStatus[i][j]=-1

           }
       }
        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled=true
                button.text=""
            }
        }
    }

    override fun onClick(view: View) {
       when(view.id)
       {
           R.id.button ->{
             updateValue(row=0,col=0,player=PLAYER)
           }
           R.id.button2 ->{
               updateValue(row=0,col=1,player=PLAYER)
           }
           R.id.button3 ->{
               updateValue(row=0,col=2,player=PLAYER)
           }
           R.id.button4 ->{
               updateValue(row=1,col=0,player=PLAYER)
           }
           R.id.button5 ->{
               updateValue(row=1,col=1,player=PLAYER)
           }
           R.id.button6 ->{
               updateValue(row=1,col=2,player=PLAYER)
           }
           R.id.button7 ->{
               updateValue(row=2,col=0,player=PLAYER)
           }
           R.id.button8 ->{
               updateValue(row=2,col=1,player=PLAYER)
           }
           R.id.button9 ->{
               updateValue(row=2,col=2,player=PLAYER)
           }

       }
        PLAYER=!PLAYER
        TURN_COUNT++
        if(PLAYER)
        {
            updateDisplay("Player X Turn")
        }
        else
        {
            updateDisplay("Player O Turn")
        }
        if(TURN_COUNT==9)
        {
            updateDisplay("Game Draw")
        }
        hasWon()
    }
    private fun hasWon() {
        for(i in 0..2)
        {
            if(boradStatus[i][0]==boradStatus[i][1] && boradStatus[i][0]==boradStatus[i][2])
            {
                if(boradStatus[i][0]==1) {
                    updateDisplay("Player X Winner")
                    break
                }
                else if(boradStatus[i][0]==0)
                {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        for(i in 0..2)
        {
            if(boradStatus[0][i]==boradStatus[1][i] && boradStatus[0][i]==boradStatus[2][i])
            {
                if(boradStatus[0][i]==1) {
                    updateDisplay("Player X Winner")
                    break
                }
                else if(boradStatus[0][i]==0)
                {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
       if(boradStatus[0][0]==boradStatus[1][1] && boradStatus[0][0]==boradStatus[2][2])
       {
           if(boradStatus[0][0]==1) {
               updateDisplay("Player X Winner")

           }
           else if(boradStatus[0][0]==0)
           {
               updateDisplay("Player O Winner")

           }
       }
        if(boradStatus[2][0]==boradStatus[1][1] && boradStatus[2][0]==boradStatus[0][2])
        {
            if(boradStatus[1][1]==1) {
                updateDisplay("Player X Winner")

            }
            else if(boradStatus[1][1]==0)
            {
                updateDisplay("Player O Winner")

            }
        }
    }


    private fun updateDisplay(text: String) {
        displayTV.setText(text)
        if(text.contains("Winner")){
            disableButton()
        }
    }

    private fun disableButton() {
        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
       val text =if(player)"X" else "O"
        val value=if(player)1 else 0
       // PLAYER=!player
      board[row][col].apply {
          isEnabled=false
          setText(text)

      }
        boradStatus[row][col]=value

        if(TURN_COUNT==9)
            Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show()
    }
}