package com.example.a641

import java.io.Serializable

class Contact : Serializable {

    var id:Int?=null
    var phone_name:String?=null
    var phone_number:String?=null

    constructor(id: Int?, phone_name: String?, phone_number: String?) {
        this.id = id
        this.phone_name = phone_name
        this.phone_number = phone_number
    }
}