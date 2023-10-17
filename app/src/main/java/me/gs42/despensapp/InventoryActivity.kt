package me.gs42.despensapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class InventoryActivity : AppCompatActivity() {

    private lateinit var listInventory: ListView
    private lateinit var products: MutableList<Product>
    private lateinit var adapter : ArrayAdapter<Product>


    companion object {
        const val REQUEST_REGISTER = 1 // You can use any unique code
        const val REQUEST_EDITER = 2 // You can use any unique code
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        listInventory = findViewById(R.id.listInventory)

        // Create a sample list of patients (you should replace this with your data source)
        products = mutableListOf(
            Product("Zanahorias",10),
            Product("Ketchup",2),
            Product("Legumbres",1),
            Product("Pepsis",4),
            Product("Galletas",17),
            // Add more patients as needed
        )


        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, products)

        listInventory.adapter = adapter


        val AddProductbutton = findViewById<Button>(R.id.AddproductButton)
        AddProductbutton.setOnClickListener {
            val intentj = Intent(this, AddProductToInventoryActivity::class.java)
            startActivityForResult(intentj, REQUEST_REGISTER)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newPatient = data?.getParcelableExtra<Product>("newProduct")
            if (newPatient != null) {
                products.add(newPatient)
                //!!!!!
                adapter.notifyDataSetChanged()
            }
        }
    }
}