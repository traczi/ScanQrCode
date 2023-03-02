package be.heh.qr_code_app.Activity.material

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import be.heh.qr_code_app.Activity.MainActivity
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.MyDB
import be.heh.qr_code_app.bdd.material.MaterialDao
import com.google.zxing.integration.android.IntentIntegrator

class ScanMaterial : AppCompatActivity() {

    private var materialDao: MaterialDao? = null
    private var db: MyDB? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_material)
        this.db = Room.databaseBuilder(
            applicationContext,
            MyDB::class.java, "MyDataBase"
        ).allowMainThreadQueries().build()
        this.materialDao = db?.materialDao()

        var integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result.contents == null) {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        } else {
            val ref = result.contents
            val material = this.materialDao?.getByRef(ref)
            if (material != null) {
                material.isValide = material.isValide != true
                this.materialDao?.updateMaterial(material)
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}