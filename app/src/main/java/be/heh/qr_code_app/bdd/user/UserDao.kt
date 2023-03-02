package be.heh.qr_code_app.bdd.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM UserTable")
    fun get(): List<UserRecord>

    @Query("SELECT * FROM UserTable WHERE email = :email")
    fun getByEMail(email: String): UserRecord

    @Insert
    fun insertUser(vararg listCategories: UserRecord)

    @Update
    fun updateUser(task: UserRecord)

    @Delete
    fun deleteUser(task: UserRecord)
}