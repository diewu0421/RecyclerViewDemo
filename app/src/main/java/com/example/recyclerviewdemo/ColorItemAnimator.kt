package com.example.recyclerviewdemo

import android.util.Log
import android.view.animation.BounceInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

/**
 * 浙江集商优选电子商务有限公司
 * @author zenglw
 * @date   21-1-19 下午4:25
 */
class ColorItemAnimator : DefaultItemAnimator(){

    override fun obtainHolderInfo(): ItemHolderInfo {
        return ColorTextInfo()
    }

    override fun getRemoveDuration(): Long {
        val allStackTraces = Thread.getAllStackTraces()
        val isRemove =
            Thread.currentThread().stackTrace.map { it.methodName }.contains("animateRemoveImpl")

        return if (isRemove) 1000 else 0
//        return 0
    }

    override fun getAddDuration(): Long {
        return 0
    }

    override fun getMoveDuration(): Long {
        return 1000
    }
    class ColorTextInfo : ItemHolderInfo() {

        var text:String? = null
        var color: Int? = 0

    }
}