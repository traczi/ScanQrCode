package be.heh.qr_code_app.bdd.material

class Material(i : Int) {

    var id: Int = 0
        public get
        private set
    var type: String = "null"
        public get
        private set
    var model: String = "null"
        public get
        private set
    var ref: String = "null"
        public get
        private set
    var lien: String = "null"
        public get
        private set
    var qrCode: ByteArray ?= null
        public get
        private set
    var isValide: Boolean = true
        public get
        private set


    constructor(id: Int, type: String, model: String, ref: String, lien: String, qrCode: ByteArray, isValide: Boolean) : this(id){
        this.id = id
        this.type = type
        this.model = model
        this.ref = ref
        this.lien = lien
        this.qrCode = qrCode
        this.isValide = isValide
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("ID : " + id.toString() + "\n" + "type : " + type + "\n" + "model : " + model + "\n" + "ref : " + ref + "\n" + "lien : " + lien )
        return sb.toString()
    }
}