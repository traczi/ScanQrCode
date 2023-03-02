package be.heh.qr_code_app.bdd

import androidx.room.Database
import androidx.room.RoomDatabase
import be.heh.qr_code_app.bdd.material.MaterialDao
import be.heh.qr_code_app.bdd.material.MaterialRecord
import be.heh.qr_code_app.bdd.user.UserDao
import be.heh.qr_code_app.bdd.user.UserRecord

@Database(entities = [UserRecord::class, MaterialRecord::class], version = 1)
abstract class MyDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun materialDao() : MaterialDao
}