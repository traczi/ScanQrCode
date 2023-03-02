package be.heh.qr_code_app.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import be.heh.qr_code_app.Activity.material.AddMaterial
import be.heh.qr_code_app.Activity.material.ListMaterial
import be.heh.qr_code_app.Activity.material.ScanMaterial
import be.heh.qr_code_app.Activity.user.ListUser
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.MyDB
import be.heh.qr_code_app.bdd.user.UserDao
import be.heh.qr_code_app.bdd.user.UserRecord

class MainActivity : AppCompatActivity() {

    private var user: UserRecord? = null
    private var userDao: UserDao? = null
    private var bt_listUser: Button? = null
    private var bt_addMaterial: Button? = null
    private var db: MyDB? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.db = Room.databaseBuilder(
            applicationContext,
            MyDB::class.java, "MyDataBase"
        ).allowMainThreadQueries().build()
        this.userDao = db?.userDao()
        bt_listUser = findViewById<View>(R.id.bt_listUser) as Button
        bt_addMaterial = findViewById<View>(R.id.bt_addMaterial) as Button
        this.user = INTENT_USER_MAIL?.let { this.userDao?.getByEMail(it) }
        Log.i("info", this.user.toString())

        if (user?.isAdmin == true) {
            val params: LinearLayout.LayoutParams =
                bt_listUser!!.layoutParams as LinearLayout.LayoutParams
            bt_listUser!!.layoutParams = params
            bt_listUser!!.visibility = View.VISIBLE
            bt_listUser!!.isClickable = true
            bt_addMaterial!!.layoutParams = params
            bt_addMaterial!!.visibility = View.VISIBLE
            bt_addMaterial!!.isClickable = true
        } else {
            val params: LinearLayout.LayoutParams =
                bt_listUser!!.layoutParams as LinearLayout.LayoutParams
            bt_listUser!!.layoutParams = params
            bt_listUser!!.visibility = View.INVISIBLE
            bt_listUser!!.isClickable = false
            bt_addMaterial!!.layoutParams = params
            bt_addMaterial!!.visibility = View.INVISIBLE
            bt_addMaterial!!.isClickable = false
        }


    }

    fun bt_listMaterial(v: View) {
        val intentListMaterial = Intent(this, ListMaterial::class.java)
        startActivity(intentListMaterial)
    }

    fun bt_listUser(v: View) {
        val intentListUser = Intent(this, ListUser::class.java)
        startActivity(intentListUser)
    }

    fun bt_addMaterial(v: View) {
        val addMaterial = AddMaterial.addMaterialIntent(this, INTENT_USER_MAIL.toString())
        startActivity(addMaterial)
    }

    fun bt_scanner(v: View) {
        val scanner = Intent(this, ScanMaterial::class.java)
        startActivity(scanner)
    }

    companion object {
        private var INTENT_USER_MAIL: String? = null
        fun newIntent(context: Context, userEmail: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(INTENT_USER_MAIL, userEmail)
            this.INTENT_USER_MAIL = userEmail
            return intent
        }
    }
}