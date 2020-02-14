package com.example.osadnicy

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.osadnicy.Player
import com.example.osadnicy.R
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {



    private val db = FirebaseFirestore.getInstance()
    private val ref = db.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var player = intent.getSerializableExtra("player") as Player
        //ref.document(player.login)
        numWood.text = getString(R.string.wood) + ": " + player.wood.toString()
        numStone.text = getString(R.string.stone) + ": " + player.stone.toString()
        numWheat.text = getString(R.string.wheat) + ": " + player.wheat.toString()
        tim.text = player.lTime.toString()

        timeUp(player)

        var a :Canvas= Canvas()
        //val myp = BitmapFactory.decodeResource(getResources(),R.drawable.abc)

        //a.drawBitmap(myp,100,100,null)

        //canv.draw(a)
    }






    fun timeUp(player: Player): Unit {

        ref.document(player.login).update("time",FieldValue.serverTimestamp()).addOnSuccessListener {
            val time = player.lTime
            var timen :Long = 0
            ref.document(player.login).get().addOnSuccessListener {
                    document ->
                timen = document.getTimestamp("time")!!.seconds
                timen = timen - time
                tim.text = timen.toString()
            }

        }

    }

    fun resourcesAdd(player: Player, time :Long){




    }


}
