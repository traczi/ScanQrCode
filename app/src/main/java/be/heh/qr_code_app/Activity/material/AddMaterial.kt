package be.heh.qr_code_app.Activity.material

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import be.heh.qr_code_app.Activity.MainActivity
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.MyDB
import be.heh.qr_code_app.bdd.material.Material
import be.heh.qr_code_app.bdd.material.MaterialDao
import be.heh.qr_code_app.bdd.material.MaterialRecord
import be.heh.qr_code_app.bdd.user.UserDao
import be.heh.qr_code_app.bdd.user.UserRecord
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream

class AddMaterial : Activity() {

    private var txtType: EditText? = null
    private var txtModel: EditText? = null
    private var txtRef: EditText? = null
    private var txtLien: EditText? = null
    private var user: UserRecord? = null
    private var userDao: UserDao? = null
    private var db: MyDB? = null
    private var dao: MaterialDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_material)

        this.txtType = findViewById<View>(R.id.type_material) as EditText
        this.txtModel = findViewById<View>(R.id.model_material) as EditText
        this.txtRef = findViewById<View>(R.id.ref_material) as EditText
        this.txtLien = findViewById<View>(R.id.lien_material) as EditText


        this.db = Room.databaseBuilder(
            applicationContext,
            MyDB::class.java, "MyDataBase"
        ).allowMainThreadQueries().build()
        this.dao = db?.materialDao()
        this.userDao = db?.userDao()
        this.user = INTENT_USER_MAIL?.let { this.userDao?.getByEMail(it) }
        Log.i("info", this.user.toString())
    }

    fun bt_addMaterialManager(v: View) {
        val matType = this.txtType?.text.toString()
        val matModel = this.txtModel?.text.toString()
        val matRef = this.txtRef?.text.toString()
        val matLien = this.txtLien?.text.toString()

        if (matType == "" || matModel == "" || matRef == "" || matLien == "") {
            Toast.makeText(this, "Please complete all field ", Toast.LENGTH_LONG).show()
        } else {
            val bitmap = view_qrCode(matRef)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            val imgBytes = outputStream.toByteArray()
            val m = Material(0, matType, matModel, matRef, matLien, imgBytes, true)
            val m1 = MaterialRecord(0, m.type, m.model, m.ref, m.lien, imgBytes, m.isValide)
            Toast.makeText(this, "Material created with success", Toast.LENGTH_LONG).show()
            Log.i("info", m1.toString())
            this.dao?.insertMaterial(m1)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun view_qrCode(ref: String): Bitmap {
        val data = ref
        val qrCode = QRCodeWriter()
        val bitMatrix = qrCode.encode(data, BarcodeFormat.QR_CODE, 400, 400)
        val bitmap = toBitmap(bitMatrix)
        return bitmap
    }

    private fun toBitmap(matrix: BitMatrix): Bitmap {
        val height = matrix.height
        val width = matrix.width
        val pixels = IntArray(width * height)
        for (i in 0 until height) {
            val offset = i * width
            for (e in 0 until width) {
                pixels[offset + e] = if (matrix[e, i]) Color.BLACK else Color.WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    companion object {
        private var INTENT_USER_MAIL: String? = null
        fun addMaterialIntent(context: Context, userEmail: String): Intent {
            val intent = Intent(context, AddMaterial::class.java)
            intent.putExtra("INTENT_USER_MAIL", userEmail)
            this.INTENT_USER_MAIL = userEmail
            return intent
        }
    }
}