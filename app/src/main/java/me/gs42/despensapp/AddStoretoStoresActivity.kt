package me.gs42.despensapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class AddStoretoStoresActivity : AppCompatActivity() {


    private lateinit var editName: EditText
    private lateinit var editTextUbicacion: EditText

    private lateinit var buttonSave: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_storeto_stores)


        editName = findViewById(R.id.storenametext)
        editTextUbicacion = findViewById(R.id.distancetext)

        buttonSave = findViewById(R.id.saveStoreButton)


        buttonSave.setOnClickListener {
            // Retrieve user input
            val name = editName.text.toString();
            val amount = Random.nextInt(100, 701)
            val isopen = "Abierto"
            // Create a new Patient object
            val product = Store(name, amount,isopen);

            val intent = Intent()
            intent.putExtra("newStore", product);
            setResult(RESULT_OK, intent)
            //startActivity(intent)
            //setResult(RESULT_OK, resultIntent)
            finish() // Close the registration activity.
        }
    }


}