package be.heh.qr_code_app.Activity.user

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import androidx.room.Room
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.MyDB
import be.heh.qr_code_app.bdd.user.UserDao
import be.heh.qr_code_app.bdd.user.UserRecord

class ListUser : Activity() {

    private var listUser: List<UserRecord>? = null
    private var db: MyDB? = null
    private var dao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        this.db = Room.databaseBuilder(
            applicationContext,
            MyDB::class.java, "MyDataBase"
        ).allowMainThreadQueries().build()
        this.dao = db?.userDao()
        listUser = this.dao?.get()
        listUser?.let { listView(it) }
    }

    fun listView(listUser: List<UserRecord>) {
        var listView = findViewById<ListView>(R.id.listUser)
        listView.adapter = ListAdaptateur(this, listUser as ArrayList<UserRecord>)
    }
}