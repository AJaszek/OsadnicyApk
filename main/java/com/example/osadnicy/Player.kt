package com.example.osadnicy

import java.io.Serializable

class Player :Serializable{
    var login =""
    var wood :Int = 0
    var stone :Int = 0
    var wheat :Int = 0
    var lTime :Long = 0

    constructor(login :String, drewno :Int, kamien :Int, wheat :Int, time:Long){
        this.login= login
        this.wood = drewno
        this.stone = kamien
        this.wheat = wheat
        this.lTime = time
    }

}