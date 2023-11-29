package me.gs42.despensapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase


class InventoryActivity : AppCompatActivity() {

    private lateinit var listInventory: ListView
    private lateinit var products: MutableList<Product>
    private lateinit var adapter : ArrayAdapter<Product>
    private lateinit var db: DataBase

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
            //Product("Zanahorias",10),
            //Product("Ketchup",2),
            //Product("Legumbres",1),
            //Product("Pepsis",4),
            //Product("Galletas",17),
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

        //ORDENAR
        val ordButto = findViewById<Button>(R.id.button2)
        ordButto.setOnClickListener {
            products.sortBy { it.cantidad }
            adapter.notifyDataSetChanged()
        }

        val nProduct = Producto(1, "soda",1)

        //database
        db = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java, "database"
        ).allowMainThreadQueries().build()

        val ProdDao = db.productoDao()
        //ProdDao.insertAll(nProduct)
        val users: List<Producto> = ProdDao.getAll()
        for (prod: Producto in users){
            Log.d("PROD", prod.nombre.toString())
            products.add(Product(prod.nombre, prod.precio!!))
        }


        //edit list
        listInventory.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var selectedItem = parent.getItemAtPosition(position) as Product

            // Aquí accedes al elemento seleccionado y puedes realizar acciones con él
            //Toast.makeText(this, "Elemento seleccionado: ${selectedItem.name}", Toast.LENGTH_SHORT).show()
            editProducto(selectedItem)
        }

    }
    fun editProducto(itemSeleccionado: Product) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Detalles del Producto")
        builder.setMessage("Producto seleccionado: $itemSeleccionado")

        builder.setPositiveButton("+") { dialog, _ ->
            val ProdDao = db.productoDao()
            ProdDao.actualizarPrecio(itemSeleccionado.name!!,itemSeleccionado.cantidad.plus(1))
            //itemSeleccionado.cantidad.plus(1)
            for(pr : Product in products){
                if(pr.name==itemSeleccionado.name){
                    pr.cantidad.plus(1)
                }
            }
            val users: List<Producto> = ProdDao.getAll()
            products.clear()
            for (prod: Producto in users){
                Log.d("PROD", prod.nombre.toString())
                products.add(Product(prod.nombre, prod.precio!!))
            }
            adapter.notifyDataSetChanged()
            dialog.dismiss()

        }

        builder.setNegativeButton("-") { dialog, _ ->
            val ProdDao = db.productoDao()
            ProdDao.actualizarPrecio(itemSeleccionado.name!!,itemSeleccionado.cantidad.minus(1))
            //itemSeleccionado.cantidad.plus(1)
            for(pr : Product in products){
                if(pr.name==itemSeleccionado.name){
                    pr.cantidad.minus(1)
                }
            }
            val users: List<Producto> = ProdDao.getAll()
            products.clear()
            for (prod: Producto in users){
                Log.d("PROD", prod.nombre.toString())
                products.add(Product(prod.nombre, prod.precio!!))
            }
            adapter.notifyDataSetChanged()
            dialog.dismiss()
            // Puedes realizar acciones adicionales al hacer clic en Cancelar
        }
        builder.setNeutralButton("Eliminar producto") { dialog, _ ->

            val ProdDao = db.productoDao()
            ProdDao.delete(ProdDao.findByName(itemSeleccionado.name!!,itemSeleccionado.cantidad))
            products.remove(itemSeleccionado)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }


        val dialog = builder.create()
        dialog.show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newPatient = data?.getParcelableExtra<Product>("newProduct")
            if (newPatient != null) {
                products.add(newPatient)

                val ProdDao = db.productoDao()
                val produ = Producto(ProdDao.getAll().size.toInt()+1,newPatient.name,newPatient.cantidad)
                ProdDao.insertAll(produ)

                //!!!!!
                adapter.notifyDataSetChanged()
            }
        }
    }

}