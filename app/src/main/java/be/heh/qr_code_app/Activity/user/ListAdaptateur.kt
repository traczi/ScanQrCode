package be.heh.qr_code_app.Activity.user

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import be.heh.qr_code_app.R
import be.heh.qr_code_app.bdd.user.UserRecord

class ListAdaptateur(private val context: Activity, private val users: List<UserRecord>) :
    ArrayAdapter<UserRecord>(context, R.layout.list_support, users) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_support, null)
        val user = getItem(position)
        val txt_mail: TextView = view.findViewById(R.id.txt_mail)
        val txt_isAdmin: TextView = view.findViewById(R.id.txt_isAdmin)

        txt_mail.text = user!!.email
        if (user.isAdmin) txt_isAdmin.text = "admin"
        else txt_isAdmin.text = "user"
        return view
    }
}