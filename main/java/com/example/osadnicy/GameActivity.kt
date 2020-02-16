package com.example.osadnicy

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.osadnicy.Player
import com.example.osadnicy.R
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_game.view.*

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

        //val myCanva = GameCanv(this)

       /* var a :Canvas= Canvas()
        var myp: Bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.kamien)

         val textPaint =
            Paint().apply {
                isAntiAlias = true
                color = Color.RED
                style = Paint.Style.STROKE
            }
        a.drawBitmap(myp,0f,0f, textPaint)
*/
        //canv.draw(myCanva.extraCanvas)
       // canv.draw(a)
    }






    fun timeUp(player: Player): Unit {
       // Log.d("abc", player.login)
        ref.document(player.login).update("time",FieldValue.serverTimestamp()).addOnSuccessListener {
            Log.d("abc", "b")
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
