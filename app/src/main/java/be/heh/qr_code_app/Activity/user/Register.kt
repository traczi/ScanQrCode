package be.heh.qr_code_app.Activity.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.MyDB
import be.heh.qr_code_app.bdd.user.User
import be.heh.qr_code_app.bdd.user.UserDao
import be.heh.qr_code_app.bdd.user.UserRecord

class Register : Activity() {

    private var txtMail: EditText? = null
    private var txtPwd: EditText? = null
    private var txtRetypePwd: EditText? = null

    private var db: MyDB? = null
    private var dao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.txtMail = findViewById<View>(R.id.user_email) as EditText
        this.txtPwd = findViewById<View>(R.id.user_pwd) as EditText
        this.txtRetypePwd = findViewById<View>(R.id.user_retypePwd) as EditText

        this.db = Room.databaseBuilder(
            applicationContext,
            MyDB::class.java, "MyDataBase"
        ).allowMainThreadQueries().build()
        this.dao = db?.userDao()
    }

    fun btRegisterToLogin(v: View) {
        val intentToLogin = Intent(this, Login::class.java)
        startActivity(intentToLogin)
    }

    fun btRegisterManager(v: View) {
        val userEmail = this.txtMail?.text.toString()
        val userPwd = this.txtPwd?.text.toString()
        val userRetypePwd = this.txtRetypePwd?.text.toString()

        if (userEmail == "" || userPwd == "" || userRetypePwd == "") {
            Toast.makeText(this, "Please complete field", Toast.LENGTH_LONG).show()
        } else {
            if (userPwd != userRetypePwd) {
                Toast.makeText(this, "Password not match", Toast.LENGTH_LONG).show()
            } else {
                if (userPwd.length >= 4) {
                    var userL = this.dao?.get()
                    var userExit = false
                    if (userL != null) {
                        for (user in userL) {
                            if (user.email == userEmail) {
                                userExit = true
                            }
                        }
                    }
                    if (userExit) {
                        Toast.makeText(this, "This email is already used", Toast.LENGTH_LONG).show()
                    } else {
                        val u = User(0, userPwd, userEmail, false)
                        val ur = UserRecord(0, u.pwd, u.email, false)
                        this.dao?.insertUser(ur)
                    }
                    Toast.makeText(this, "Your account is create", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Minimum 4 characters", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}