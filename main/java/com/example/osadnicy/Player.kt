package com.example.osadnicy

import java.io.Serializable

class Player :Serializable{
    var login =""
    var wood :Int = 0
    var stone :Int = 0
    var wheat :Int = 0
    var lTime :Long = 0
    var levCastle : Short = 0
    var levWheat : Short = 0
    var levWood : Short = 0
    var levStone : Short = 0

    constructor(map: MutableMap<String, Any>){
        this.login = map["login"].toString() as String
        this.wood = map["wood"].toString().toInt() as Int
        this.stone = map["stone"].toString().toInt() as Int
        this.wheat = map["wheat"].toString().toInt() as Int
        this.lTime = map["time1"].toString().toLong() as Long
        this.levWheat = map["levWheat"].toString().toShort() as Short
        this.levStone = map["levStone"].toString().toShort()
        this.levWood = map["levWood"].toString().toShort()
        this.levCastle = map["levCastle"].toString().toShort()
    }

}