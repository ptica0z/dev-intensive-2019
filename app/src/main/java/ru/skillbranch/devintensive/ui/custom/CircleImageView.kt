package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import ru.skillbranch.devintensive.R

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    companion object{
        private const val CV_BORDERCOLOR = Color.WHITE
        private const val CV_BORDERWIDTH = 2f
    }

    private var cv_bordercolor = CV_BORDERCOLOR
    private var cv_borderwidth = CV_BORDERWIDTH

    init {
        if(attrs != null){
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            cv_bordercolor = a.getColor(R.styleable.CircleImageView_cv_bordercolor, CV_BORDERCOLOR)
            cv_borderwidth = a.getDimension(R.styleable.CircleImageView_cv_borderwidth, CV_BORDERWIDTH)
            a.recycle()
        }
    }

    /*@Dimension fun getBorderWidth():Int{

        return 2
    }

    fun setBorderWidth(@Dimension dp:Int){}

    fun getBorderColor():Int{
        return Color.WHITE
    }

    fun setBorderColor(hex:String){}

    fun setBorderColor(@ColorRes colorId: Int){}*/
}