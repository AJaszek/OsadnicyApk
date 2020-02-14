package com.example.osadnicy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.osadnicy.GameActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ServerTimestamp
import com.google.firebase.firestore.model.value.ServerTimestampValue
import com.google.firebase.firestore.model.value.TimestampValue
import com.google.firestore.v1beta1.DocumentTransform
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin.setOnClickListener{
            if(inputLogin.text.toString() != "" && inputPass.text.toString() != "")
                logowanie(inputLogin.text.toString(), inputPass.text.toString())
            else
                textLoginFail.text="Niepoprawne dane"
        }
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    fun logowanie (login: String, pass: String) {

        val docRef = db.collection("users").document(login)
        docRef.get()
            .addOnSuccessListener { document ->

                val fields = document.data
                if (fields!!.get("Password").toString()==pass.hashCode().toString()) {

//                    val time :Timestamp = fields["time"] as Timestamp


                    //val a = FieldValue.serverTimestamp()
                    Log.d("abc",document.getTimestamp("time")?.seconds.toString())
                    //var timestamp = ServerTimestampValue(12,32,)
                    //Log.d("abc", document.metadata.toString())
                    val player :Player= Player(document.get("Login").toString(), document.get("wood").toString().toInt(), document.get("stone").toString().toString().toInt(), document.get("wheat").toString().toInt(), document.getTimestamp("time")!!.seconds)

                    val intent = Intent(this, GameActivity::class.java)
                    intent.putExtra("player", player)
                    startActivity(intent)


                } else {
                    textLoginFail.text="Niepoprawne dane"
                }
            }
            .addOnFailureListener { exception ->
                Log.d("abc", "get failed with ", exception)

            }

    }
}
