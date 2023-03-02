package be.heh.qr_code_app.Activity.material

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.material.MaterialRecord

class ListAdaptateurMaterial(
    private val context: Activity,
    private val materials: List<MaterialRecord>
) : ArrayAdapter<MaterialRecord>(context, R.layout.list_support_material, materials) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_support_material, null)
        val material = getItem(position)
        val txt_model: TextView = view.findViewById(R.id.txt_model)
        val txt_type: TextView = view.findViewById(R.id.txt_type)
        val txt_ref: TextView = view.findViewById(R.id.txt_ref)
        val txt_lien: TextView = view.findViewById(R.id.txt_lien)
        val qrCode: ImageView = view.findViewById(R.id.img_qrCode)
        val txt_valide: TextView = view.findViewById(R.id.txt_isValide)

        txt_type.text = material!!.type
        txt_model.text = material.model
        txt_ref.text = material.ref
        txt_lien.text = material.lien
        if (material.isValide) {
            txt_valide.setTextColor(Color.GREEN)
            txt_valide.text = "Disponible"
        } else {
            txt_valide.setTextColor(Color.RED)
            txt_valide.text = "Indisponible"
        }
        val bitmap = BitmapFactory.decodeByteArray(material.qrCode, 0, material.qrCode.size)
        qrCode.setImageBitmap(bitmap)
        return view
    }

}