package com.example.osadnicy

import android.view.View
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scale
import androidx.core.graphics.toColor
import kotlin.math.abs


class GameCanv(context: Context, attr: AttributeSet? = null ) : View(context, attr) {

    private var paint = Paint()
    var circle: Circle = Circle()
    val circles = ArrayList<Circle>()
    var can = false
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event!=null){

            when(event.action){

                MotionEvent.ACTION_DOWN ->{
                    if((abs( event.x-circle.originPointF.x)<100 && abs( event.y-circle.originPointF.y)<100) || circle.originPointF.x==10000F ){
                        val originPointF = PointF(event.x, event.y)
                        //Log.d("abc", "aa")
                        //circle = Circle()
                        //circles.add(circle)
                        circle.originPointF = originPointF
                        can = true
                        invalidate()
                    }
                }
                MotionEvent.ACTION_UP ->{
                    if(can) {
                        val currentPointF = PointF(event.x, event.y)
                        circle.originPointF = currentPointF
                        //Log.d("abc", can.toString())
                        can = false
                        invalidate()
                    }
                }
                MotionEvent.ACTION_MOVE->{
                    if(can) {
                        val currentPointF = PointF(event.x, event.y)
                        circle.originPointF = currentPointF
                        //Log.d("abc", can.toString())

                        invalidate()
                    }
                }
            }
        }


        return true
    }



    override fun onDraw(canvas: Canvas) {
        paint.color = ContextCompat.getColor(context, R.color.colorPath)
        paint.setStrokeJoin(Paint.Join.ROUND)

        paint.setStrokeWidth(40F)




       /* for(circle in circles){
            val radius = Math.sqrt(Math.pow(circle.originPointF.x - circle.currentPointF.x.toDouble(), 2.0 )+
            Math.pow(circle.originPointF.y - circle.currentPointF.y.toDouble(), 2.0))
            canvas.drawCircle(circle.originPointF.x, circle.originPointF.y, radius.toFloat(), paint)
        }*/
        canvas.drawLine(canvas.width.toFloat()/2,canvas.height.toFloat()/2,circle.originPointF.x.toFloat(), circle.originPointF.y.toFloat(), paint)

        var myp: Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zamek)
        myp = Bitmap.createScaledBitmap(myp, (canvas.width/5).toInt(),(canvas.width/5).toInt(), false)
        canvas.drawBitmap(
            myp,
            circle.originPointF.x.toFloat()-(myp.width/2),
            circle.originPointF.y.toFloat()-(myp.height/2),
            paint
        )

        //var rect = RectF(canvas.width.toFloat()/2, canvas.height.toFloat()/2, circle.originPointF.x.toFloat(), circle.originPointF.y.toFloat())

       // var p = Path()
        //p.addArc(rect,270f,90f)
        //canvas.drawPath(p, paint)
        //canvas.drawArc(canvas.width.toFloat()/2, canvas.height.toFloat()/2, circle.originPointF.x.toFloat(), circle.originPointF.y.toFloat(),90f,45f,true,paint)

        super.onDraw(canvas)
    }


}