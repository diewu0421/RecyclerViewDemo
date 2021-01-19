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

    override fun recordPreLayoutInformation(
        state: RecyclerView.State,
        viewHolder: RecyclerView.ViewHolder,
        changeFlags: Int,
        payloads: MutableList<Any>
    ): ItemHolderInfo {


        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads).apply {

        }
    }

    override fun recordPostLayoutInformation(
        state: RecyclerView.State,
        viewHolder: RecyclerView.ViewHolder
    ): ItemHolderInfo {
        return super.recordPostLayoutInformation(state, viewHolder)
    }

    override fun obtainHolderInfo(): ItemHolderInfo {
        return ColorTextInfo()

    }

    override fun animateRemove(holder: RecyclerView.ViewHolder): Boolean {

        val animateRemove = super.animateRemove(holder)
        holder.itemView.animate().interpolator =  LinearInterpolator()
        mDuration = 1000
        Log.e("ColorItemAnimator","animateRemove $mDuration")
        return animateRemove
    }
    var mDuration = 1000

    override fun animateMove(
        holder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        mDuration = 0
        Log.e("ColorItemAnimator","animateMove $mDuration")
        return super.animateMove(holder, fromX, fromY, toX, toY)
    }

    override fun getRemoveDuration(): Long {
        return mDuration.toLong()
    }

    override fun getMoveDuration(): Long {
        return 500
    }
    class ColorTextInfo : ItemHolderInfo() {

        var text:String? = null
        var color: Int? = 0

    }
}