package be.heh.qr_code_app.bdd.user

import javax.xml.transform.sax.SAXTransformerFactory

class User(i : Int) {
    var id : Int = 0
        public get
        private set

    var pwd: String = "null"
        public get
        private set

    var email: String = "null"
        public get
        private set
    var isAdmin: Boolean = false
        public get
        private set


    constructor(i: Int, p:String, e:String, a:Boolean) : this(i){
        id = i
        pwd = p
        email = e
        isAdmin = a
    }

    override fun toString(): String {

        val sb = StringBuilder()

        sb.append("ID : " + id.toString() +"\n" + "Password : " + pwd + "\n" + "Email : " + email)
        return sb.toString()
    }
}