package be.heh.qr_code_app.bdd.material

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MaterialDao {
    @Query("SELECT * FROM material")
    fun get(): List<MaterialRecord>

    @Query("SELECT * FROM material WHERE ref = :ref")
    fun getByRef(ref : String): MaterialRecord

    @Insert
    fun insertMaterial(vararg listCategory: MaterialRecord)

    @Update
    fun updateMaterial(material: MaterialRecord)

    @Delete
    fun deleteMaterial(material: MaterialRecord)
}