package be.heh.qr_code_app.bdd.material

import android.graphics.ColorSpace.Model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "material")
data class MaterialRecord(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Int=0,
    @ColumnInfo(name = "type") var type: String = "null",
    @ColumnInfo(name = "model") var model: String,
    @ColumnInfo(name = "ref") var ref: String,
    @ColumnInfo(name = "lien") var lien: String,
    @ColumnInfo(name = "grCode") var qrCode: ByteArray,
    @ColumnInfo(name = "isValide") var isValide: Boolean,
)
