package com.example.osadnicy

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*



class RegisterActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val ref = db.collection("users")


    private fun reg(login:String, passs:String){
        val pass = passs.hashCode().toString()

        val data: HashMap<String, Any> = hashMapOf(
            "Login" to login,
            "Password" to pass,
            "wood" to 0,
            "stone" to 0,
            "wheat" to 0,
            "time" to FieldValue.serverTimestamp()
        )

        ref.document(login).set(data)
        textRegister.text="Zarejestrowano pomyślnie"
        textRegister.setTextColor(Color.rgb(0,124,0))
    }

    private fun check(login :String, pass:String)  {

        val docRef = db.collection("users").document(login)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data == null) {
                    reg(login, pass)
                }
                else{
                    textRegister.text="Użytkownik $login istnieje"
                    textRegister.setTextColor(Color.rgb(255,0,0))

                }
            }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        buttonRegister.setOnClickListener {
            val login = inputLogin.text.toString()
            val pass = inputPassword.text.toString()

            check(login, pass)

        }

        buttonReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
