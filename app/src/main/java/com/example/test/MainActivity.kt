package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = FirebaseFirestore.getInstance().document("Icecreams/Flavours")
        save.setOnClickListener {
            store()
        }
        next.setOnClickListener {
            Next()
        }
    }

    private fun store() {
        val flavour = flavor.text.toString()
        val ingredient1 = Ingredient1.text.toString()
        val ingredient2 = Ingredient2.text.toString()
        val ingredient3 = Ingredient3.text.toString()
        if (!flavour.isEmpty() && !ingredient1.isEmpty() && !ingredient2.isEmpty() && !ingredient3.isEmpty()){
            try {
                val items = HashMap<String, Any>()
                items.put("Ingredient-1", ingredient1)
                items.put("Ingredient-2", ingredient2)
                items.put("Ingredient-3", ingredient3)
                db.collection(flavour).document("ingredients").set(items).addOnSuccessListener {
                    Toast.makeText(this, "Details Uploaded Successfully", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this, "Details Upload Failed", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
    fun Next(){
        startActivity(Intent(applicationContext,UploadImage::class.java))
    }

}