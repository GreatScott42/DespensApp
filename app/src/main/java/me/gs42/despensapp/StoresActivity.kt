package me.gs42.despensapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class StoresActivity : AppCompatActivity() {

    private lateinit var liststores: ListView
    private lateinit var stores: MutableList<Store>
    private lateinit var adapter : ArrayAdapter<Store>


    companion object {
        const val REQUEST_REGISTER = 1 // You can use any unique code
        const val REQUEST_EDITER = 2 // You can use any unique code
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)

        liststores = findViewById(R.id.listStores)

        // Create a sample list of patients (you should replace this with your data source)
        //cencania en metros y 0 cerrado 1 abierto
        stores = mutableListOf(
            Store("Jumbo",100,"Abierto"),
            Store("Lider",234,"Abierto"),
            Store("Acuenta",733,"Abierto"),
            Store("Tottus",409,"Cerrado"),

            // Add more patients as needed
        )


        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter<Store>(this, android.R.layout.simple_list_item_1, stores)

        liststores.adapter = adapter


        val AddProductbutton = findViewById<Button>(R.id.AddstoreButton)
        AddProductbutton.setOnClickListener {
            val intentj = Intent(this, AddStoretoStoresActivity::class.java)
            startActivityForResult(intentj, REQUEST_REGISTER)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newPatient = data?.getParcelableExtra<Store>("newStore")
            if (newPatient != null) {
                stores.add(newPatient)
                //!!!!!
                adapter.notifyDataSetChanged()
            }
        }
    }
}