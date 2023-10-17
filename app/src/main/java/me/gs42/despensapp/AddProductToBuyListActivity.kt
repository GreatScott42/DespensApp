package me.gs42.despensapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddProductToBuyListActivity : AppCompatActivity() {


    private lateinit var editTextNameB: EditText
    private lateinit var editTextAmount: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var editTextStore: EditText
    private lateinit var buttonSave: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_to_buy_list)


        editTextNameB = findViewById(R.id.NameT)
        editTextAmount = findViewById(R.id.AmountT)
        editTextPrice = findViewById(R.id.PriceT)
        editTextStore = findViewById(R.id.StoreT)
        buttonSave = findViewById(R.id.SaveProductButtonB)


        buttonSave.setOnClickListener {
            // Retrieve user input
            val name = editTextNameB.text.toString();
            val amount = editTextAmount.text.toString().toIntOrNull() ?: 0; // Default to 0 if parsing fails
            val price = editTextPrice.text.toString().toIntOrNull() ?: 0; // Default to 0 if parsing fails
            val store = editTextStore.text.toString();
            // Create a new Patient object
            val product = ProductB(name, amount,price,store);

            val intent = Intent()
            intent.putExtra("newStore", product);
            setResult(RESULT_OK, intent)
            //startActivity(intent)
            //setResult(RESULT_OK, resultIntent)
            finish() // Close the registration activity.
        }
    }


}