package me.gs42.despensapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class InventoryActivity : AppCompatActivity() {

    private lateinit var listInventory: ListView
    private lateinit var products: MutableList<Product>
    private lateinit var adapter : ArrayAdapter<Product>
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

    }
}