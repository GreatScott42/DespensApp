package me.gs42.despensapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddProductToInventoryActivity : AppCompatActivity() {


    private lateinit var editTextName: EditText
    private lateinit var editTextAmount: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_to_inventory)


        editTextName = findViewById(R.id.ProductNameText)
        editTextAmount = findViewById(R.id.ProductAmountText)
        buttonSave = findViewById(R.id.SaveProductButton)


        buttonSave.setOnClickListener {
            // Retrieve user input
            val name = editTextName.text.toString();
            val amount = editTextAmount.text.toString().toIntOrNull() ?: 0; // Default to 0 if parsing fails

            // Create a new Patient object
            val product = Product(name, amount);

            val intent = Intent()
            intent.putExtra("newProduct", product);
            setResult(RESULT_OK, intent)
            //startActivity(intent)
            //setResult(RESULT_OK, resultIntent)
            finish() // Close the registration activity.
        }
    }


}