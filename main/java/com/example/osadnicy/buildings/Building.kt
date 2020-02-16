package com.example.osadnicy.buildings

import android.graphics.PointF

abstract class Building {
    private var pos = PointF(10000F, 10000F)
    var lvl = 0






    public fun setPos(x:Float, y:Float) {
        this.pos.x = x
        this.pos.y = y
    }
    public fun getPos(): PointF{
        return this.pos
    }


}