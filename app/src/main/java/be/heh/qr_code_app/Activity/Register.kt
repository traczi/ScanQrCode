package be.heh.qr_code_app.Activity

import android.app.Activity
import android.app.PictureInPictureUiState
import android.content.ClipData.newIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.MyDB
import be.heh.qr_code_app.bdd.User
import be.heh.qr_code_app.bdd.UserDao
import be.heh.qr_code_app.bdd.UserRecord

class Register : Activity() {

    private var txtMail : EditText? = null
    private var txtPwd : EditText? = null
    private var txtRetypePwd : EditText? = null

    private var db : MyDB?=null
    private var dao : UserDao?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        this.txtMail = findViewById<View>(R.id.user_email) as EditText
        this.txtPwd = findViewById<View>(R.id.user_pwd) as EditText
        this.txtRetypePwd = findViewById<View>(R.id.user_retypePwd) as EditText

        this.db = Room.databaseBuilder(
            applicationContext,
            MyDB::class.java, "MyDataBase"
        ).allowMainThreadQueries().build()
        this.dao = db?.userDao()
    }

    fun btRegisterManager(v : View){
        val userEmail = this.txtMail?.text.toString()
        val userPwd = this.txtPwd?.text.toString()
        val userRetypePwd = this.txtRetypePwd?.text.toString()

        if(userEmail == "" || userPwd == "" || userRetypePwd == ""){
            Toast.makeText(this, "Please complete field", Toast.LENGTH_LONG).show()
        }else{
            if(userPwd != userRetypePwd){
                Toast.makeText(this, "Password not match", Toast.LENGTH_LONG).show()
            }else{
                if(userPwd.length >= 4){
                    val u = User(0, userPwd, userEmail)
                    val ur = UserRecord(0, u.pwd, u.email)
                    this.dao?.insertUser(ur)

                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}