package me.gs42.despensapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class AcomprarActivity : AppCompatActivity() {

    private lateinit var listComprar: ListView
    private lateinit var total: TextView
    private lateinit var products: MutableList<ProductB>
    private lateinit var adapter : ArrayAdapter<ProductB>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acomprar)

        listComprar = findViewById(R.id.listaComprar)
        total = findViewById(R.id.total)

        // Create a sample list of patients (you should replace this with your data source)
        products = mutableListOf(
            ProductB("Cereales",10,3000),
            ProductB("Duraznos",2,800),
            ProductB("Snacks",1,1500),
            ProductB("Aceitunas",4,300),
            ProductB("Arroz",17,1500),
            // Add more patients as needed
        )
        var totalvalor=0;
        for (ProductB in products){
            totalvalor+=(ProductB.cantidad*ProductB.precio)
        }
        total.text = "total:"+totalvalor.toString()


        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter<ProductB>(this, android.R.layout.simple_list_item_1, products)

        listComprar.adapter = adapter
    }
}