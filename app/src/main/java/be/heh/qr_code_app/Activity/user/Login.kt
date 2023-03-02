package be.heh.qr_code_app.Activity.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import be.heh.qr_code_app.Activity.MainActivity
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.MyDB
import be.heh.qr_code_app.bdd.user.UserDao

class Login : AppCompatActivity() {

    private var txtMail: EditText? = null
    private var txtPwd: EditText? = null
    private var db: MyDB? = null
    private var dao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtMail = findViewById<View>(R.id.user_email) as EditText
        txtPwd = findViewById<View>(R.id.user_pwd) as EditText
        this.db = Room.databaseBuilder(
            applicationContext,
            MyDB::class.java, "MyDataBase"
        ).allowMainThreadQueries().build()
        this.dao = db?.userDao()
    }

    fun btLoginManager(v: View) {
        val userEmail = this.txtMail?.text.toString()
        val userPwd = this.txtPwd?.text.toString()
        if (userEmail == "" || userPwd == "") {
            Toast.makeText(this, "Please completed field", Toast.LENGTH_LONG).show()
        } else {
            val userL = dao?.get()

            if (userL != null) {
                for (user in userL) {
                    if (user.email == userEmail) {
                        if (user.pwd == userPwd) {
                            val intent = MainActivity.newIntent(this, userEmail)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Password incorrect", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    fun btLoginToRegister(v: View) {
        val intentToRegister = Intent(this, Register::class.java)
        startActivity(intentToRegister)
    }
}