package me.gs42.despensapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


class MainActivity : AppCompatActivity() {

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.mainmenu, menu)
        return true
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonInventory = findViewById<Button>(R.id.InventoryButton)
        //val buttonacomprar = findViewById<Button>(R.id.ComprarButton)
        //val buttonStores = findViewById<Button>(R.id.TiendasButton)
        buttonInventory.setOnClickListener {
                val intent = Intent(this, InventoryActivity::class.java)
                startActivity(intent)
        }
        /*buttonacomprar.setOnClickListener {
            val intent = Intent(this, AcomprarActivity::class.java)
            startActivity(intent)
        }
        buttonStores.setOnClickListener {
            val intent = Intent(this, StoresActivity::class.java)
            startActivity(intent)
        }*/



    }

}