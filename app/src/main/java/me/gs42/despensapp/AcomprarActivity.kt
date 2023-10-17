package me.gs42.despensapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class AcomprarActivity : AppCompatActivity() {

    private lateinit var listComprar: ListView
    private lateinit var total: TextView
    private lateinit var presupuesto: EditText
    private lateinit var products: MutableList<ProductB>
    private lateinit var adapter : ArrayAdapter<ProductB>
    var totalvalor=0;
    private lateinit var presupuestoString: String
    private var presupuestoValue = 0
    companion object {
        const val REQUEST_REGISTER = 1 // You can use any unique code
        const val REQUEST_EDITER = 2 // You can use any unique code
    }

    fun updatePresupuesto(){
        presupuestoString = presupuesto.text.toString()
        presupuestoValue = Integer.parseInt(presupuestoString)
        totalvalor=0
        for (ProductB in products){
            totalvalor+=(ProductB.cantidad*ProductB.precio)
        }
        if(presupuestoValue<totalvalor){
            total.text="te pasaste de tu presupuesto por"+(totalvalor-presupuestoValue)+":("
        }else{
            total.text = "total:"+totalvalor.toString()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acomprar)

        listComprar = findViewById(R.id.listaComprar)
        total = findViewById(R.id.total)
        presupuesto=findViewById(R.id.presupuesto)

        // Create a sample list of patients (you should replace this with your data source)
        products = mutableListOf(
            ProductB("Cereales",10,3000,"Jumbo"),
            ProductB("Duraznos",2,800,"Lider"),
            ProductB("Snacks",1,1500,"Tottus"),
            ProductB("Aceitunas",4,300,"Santa Isabel"),
            ProductB("Arroz",17,1500,"Santa Isabel"),
            // Add more patients as needed
        )

        for (ProductB in products){
            totalvalor+=(ProductB.cantidad*ProductB.precio)
        }
        total.text = "total:"+totalvalor.toString()
        presupuestoString = presupuesto.text.toString()
        presupuestoValue = Integer.parseInt(presupuestoString)

        if(presupuestoValue<totalvalor){
            total.text="te pasaste de tu presupuesto por"+(totalvalor-presupuestoValue)+":("
        }
        val updatebutton = findViewById<Button>(R.id.UpdatePre)
        updatebutton.setOnClickListener {
            updatePresupuesto()
        }
        val AddProductTobuybutton = findViewById<Button>(R.id.addProducttoButlist)
        AddProductTobuybutton.setOnClickListener {
            val intentj = Intent(this, AddProductToBuyListActivity::class.java)
            startActivityForResult(intentj, AcomprarActivity.REQUEST_REGISTER)
        }

        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter<ProductB>(this, android.R.layout.simple_list_item_1, products)

        listComprar.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == InventoryActivity.REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newPatient = data?.getParcelableExtra<ProductB>("newProductB")
            if (newPatient != null) {
                products.add(newPatient)
                //!!!!!
               updatePresupuesto()
                ///
                adapter.notifyDataSetChanged()
            }
        }
    }

}