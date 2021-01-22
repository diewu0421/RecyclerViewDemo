package com.example.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dalvik.system.DexFile
import java.io.File
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: RecyclerView.Adapter<MyViewHolder>

    private val data by lazy { MutableList(4) { "content = $it" } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = object : RecyclerView.Adapter<MyViewHolder>() {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): MyViewHolder {
                val view = layoutInflater.inflate(R.layout.item_view_layout, parent, false)
                return MyViewHolder(view)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                Log.e("MainActivity", "onBindViewHolder ${position} ${data[position]}")

                holder.getView<TextView>(R.id.tv).text = data[position]
            }

            override fun getItemCount(): Int {
                return data.size
            }
        }

//        findViewById<ViewPager2>(R.id.vp).apply {
//            adapter = mAdapter
//            this.offscreenPageLimit = 3
//            this.isFakeDragging
//        }

        findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = object : LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false) {
                override fun canScrollHorizontally(): Boolean {
                    return false
                }
            }
//            mAdapter = object : RecyclerView.Adapter<MyViewHolder> () {
//                private val data by lazy { MutableList(100){ "content = $it"} }
//
//                override fun onCreateViewHolder(
//                    parent: ViewGroup,
//                    viewType: Int
//                ): MyViewHolder {
//                    val view = layoutInflater.inflate(R.layout.item_view_layout, parent, false)
//                    view.animate().interpolator = CycleInterpolator(10f)
//                    return MyViewHolder(view)
//                }
//
//                override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//                    holder.getView<TextView>(R.id.tv).text = data[position]
//                }
//
//                override fun getItemCount(): Int {
//                    return data.size
//                }
//            }
            itemAnimator = ColorItemAnimator()

            adapter = mAdapter
            Log.e("MainActivity", "onCreate $itemAnimator")
        }
    }

    private val mHandler = Handler(Looper.getMainLooper())

    fun onClick(view: View) {

        val get = data.get(0)

        data.removeAt(0)
//        data.add(get)

        mAdapter.notifyItemRemoved(0)
        mHandler.postDelayed({
            data.add(get)
            mAdapter.notifyItemInserted(data.size - 1)
            mHandler.postDelayed({
                onClick(view)
            }, 100)
        }, 1100)
//        mAdapter.notifyItemRangeChanged(0, data.size)
//        mHandler.postDelayed({
//
//            onClick(view)
//        }, 1100)
    }

    fun printDex(view: View) {
        Log.e("MainActivity", "printDex ${Boy()}")
        thread {

            val measureTimeMillis = measureTimeMillis {

                DexFile(File(applicationContext.applicationInfo.sourceDir)).entries().run {
                    while (hasMoreElements()) {
//                Log.e("MainActivity", "printDex ${nextElement()}")
                        nextElement()
                            .takeIf {
                                it.contains("com.example.recyclerviewdemo.Boy")
                                        && it.contains("$")

                            }
                            ?.takeIf {
                                try {
                                    Class.forName(it).getDeclaredField("this$0").isSynthetic
                                } catch (e: Exception) {
                                    false
                                }
                            }
//                    ?.takeIf { it.getDeclaredField("this$0").isSynthetic }
                            ?.let {
                                Log.e("MainActivity", "printDex result $it")
                            }

                    }
                }
            }
            Log.e("MainActivity", "printDex  time  $measureTimeMillis")
        }
    }

    fun cancel(view: View) {
        mHandler.removeCallbacksAndMessages(null)
    }
}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun <T : View> getView(id: Int) = view.findViewById<T>(id)

}
