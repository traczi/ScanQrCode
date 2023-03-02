package be.heh.qr_code_app.Activity.material

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import androidx.room.Room
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.MyDB
import be.heh.qr_code_app.bdd.material.MaterialDao
import be.heh.qr_code_app.bdd.material.MaterialRecord

class ListMaterial : Activity() {

    private var listMaterial: List<MaterialRecord>? = null
    private var db: MyDB? = null
    private var dao: MaterialDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_material)

        this.db = Room.databaseBuilder(
            applicationContext,
            MyDB::class.java, "MyDataBase"
        ).allowMainThreadQueries().build()
        this.dao = db?.materialDao()
        listMaterial = this.dao?.get()
        listMaterial?.let { listView(it) }
    }

    fun listView(listMaterial: List<MaterialRecord>) {
        var listView = findViewById<ListView>(R.id.listMaterial)
        listView.adapter = ListAdaptateurMaterial(this, listMaterial as ArrayList<MaterialRecord>)
    }


}