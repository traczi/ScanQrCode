package be.heh.qr_code_app.bdd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.TimerTask

@Dao
interface UserDao {
    @Query("SELECT * FROM UserTable")
    fun get(): List<UserRecord>

    @Query("SELECT * FROM UserTable WHERE email = :email")
    fun get(email: String): UserRecord

    @Insert
    fun insertUser(vararg listCategories: UserRecord)

    @Update
    fun updateUser(task: UserRecord)

    @Delete
    fun deleteUser(task: UserRecord)
}