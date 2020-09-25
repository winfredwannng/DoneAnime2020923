package swu.w1nfred.doneanime2020923

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Display
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.view.isInvisible
import java.util.*


class DoneAnime:View {
    constructor(context: Context):super(context){}
    constructor(context: Context,attrs: AttributeSet?):super(context,attrs){}


    //private var backAlpha = 255
    private val mPaint: Paint by lazy {
        Paint().apply {
            setARGB(255,43,43,43)
            style = Paint.Style.FILL
        }
    }
    /*private val BackPaint: Paint by lazy {
        Paint().apply {
            setARGB(backAlpha,43,43,43)
            style = Paint.Style.FILL
        }
    }*/
    private val FrontPaint: Paint by lazy {
        Paint().apply {
            setARGB(255,104,151,187)
            style = Paint.Style.FILL
        }
    }
    private val TickPaint: Paint by lazy {
        Paint().apply {
            setARGB(255,255,255,255)
            style = Paint.Style.STROKE
            strokeWidth = 7f
        }
    }
    private var startx = 0f
    private var starty = 0f
    override fun onDraw(canvas: Canvas?) {
        /*canvas?.drawRoundRect(
            RectF(originx,originy,topx,bottomy),
            50f,50f,mPaint)*/
       /* canvas?.drawRoundRect(RectF(0f,0f,width.toFloat(),height.toFloat())
        ,1f,1f,BackPaint)
        */
        /*canvas?.drawRoundRect(
            RectF(0f,0f,width.toFloat()/2 - processWidth,height.toFloat())
            ,0f,0f,FrontPaint)*/
        canvas?.drawRoundRect(RectF(
            startx,starty,processWidth,height.toFloat()),
            roundLevel,roundLevel,FrontPaint)

        canvas?.drawLine(width/2 - 67f, (height/2).toFloat(),tickx1,
            ticky1,TickPaint)
        canvas?.drawLine(
            (width/2).toFloat(), (height/2 +67).toFloat(),tickx2,
            ticky2,TickPaint)



    }
    private var tickx1 = (width/2 - 67).toFloat()
    private var ticky1 = (height/2).toFloat()
    private var tickx2 = (width/2).toFloat()
    private var ticky2 = (height/2 + 67).toFloat()
    private var roundLevel = 0f
    private var processWidth = 0f
    private var processAnim:ValueAnimator? = null
    private var animators = AnimatorSet()
   // private var alphaAnim:ValueAnimator? = null
    private var tocircleAnim:ValueAnimator? = null
    private var tocircleAnim2:ValueAnimator? = null
    private var tocircleAnim3:ValueAnimator? = null
    private var tickAnim1:ValueAnimator? = null
    private var tickAnim2:ValueAnimator? = null
    private var tickAnim3:ValueAnimator? = null
    private var tickAnim4:ValueAnimator? = null

    val lists = mutableListOf<ValueAnimator>()
     fun createAnim(){
             processAnim = ValueAnimator.ofFloat(0f, width.toFloat()).apply {
                 duration = 1200
                 repeatCount = 0
                 addUpdateListener {
                     processWidth = it.animatedValue as Float
                     invalidate()
                 }
                 lists.add(this)


             }

            /* alphaAnim = ValueAnimator.ofInt(255, 0).apply {
                 duration = 0
                 repeatCount = 0
                 startDelay = 1500
                 addUpdateListener {
                     backAlpha = it.animatedValue as Int
                     invalidate()
                 }
                 lists.add(this)

             }*/


            tocircleAnim = ValueAnimator.ofFloat(0f,100f).apply {
                duration = 300
                repeatCount = 0
                startDelay = 1200
                addUpdateListener {
                    roundLevel = it.animatedValue as Float
                    invalidate()
                }
                lists.add(this)
            }
            tocircleAnim2 = ValueAnimator.ofFloat(width.toFloat(),(width/2 + 95f).toFloat()).apply {
                 duration = 600
                 repeatCount = 0
                 startDelay = 1500
                 addUpdateListener {
                     processWidth = it.animatedValue as Float
                     invalidate()
                 }
                 lists.add(this)
             }
             tocircleAnim3 = ValueAnimator.ofFloat(0f, (width/2 - 95f).toFloat()).apply {
                 duration = 600
                 repeatCount = 0
                 startDelay = 1500
                 addUpdateListener {
                     startx = it.animatedValue as Float
                     invalidate()
                 }
                 lists.add(this)
             }
            tickAnim1 = ValueAnimator.ofFloat((width/2 -67f).toFloat(), (width/2).toFloat()).apply {
                duration = 500
                repeatCount = 0
                startDelay = 2100
                addUpdateListener {
                    tickx1 = it.animatedValue as Float
                    invalidate()
                }
                lists.add(this)
            }
         tickAnim2 = ValueAnimator.ofFloat((height/2).toFloat(), (height/2 + 67f).toFloat()).apply {
             duration = 500
             repeatCount = 0
             startDelay = 2100
             addUpdateListener {
                 ticky1 = it.animatedValue as Float
                 invalidate()
             }
             lists.add(this)
         }
         tickAnim3 = ValueAnimator.ofFloat((width/2).toFloat(), (width/2 + 67).toFloat()).apply {
             duration = 500
             repeatCount = 0
             startDelay = 2600
             addUpdateListener {
                 tickx2 = it.animatedValue as Float
                 invalidate()
             }
             lists.add(this)
         }
         tickAnim4 = ValueAnimator.ofFloat((height/2 + 67).toFloat(), (height/2 - 67).toFloat()).apply {
             duration = 500
             repeatCount = 0
             startDelay = 2600
             addUpdateListener {
                 ticky2 = it.animatedValue as Float
                 invalidate()
             }
             lists.add(this)
         }

         for (list in lists){
             animators.play(list)
         }
        /* backAlpha = 0*/
         roundLevel = 0f


    }

    fun startAnim(){
        createAnim()

        if (animators.isPaused){
            animators.resume()
        }else {
            animators.start()
        }
    }

}