package com.example.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator

class MainActivity: AppCompatActivity() {
    private lateinit var mAdapter: RecyclerView.Adapter<MyViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = object : RecyclerView.Adapter<MyViewHolder> () {
            private val data by lazy { MutableList(100){ "content = $it"} }

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): MyViewHolder {
                val view = layoutInflater.inflate(R.layout.item_view_layout, parent, false)
                return MyViewHolder(view)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            mAdapter = object : RecyclerView.Adapter<MyViewHolder> () {
                private val data by lazy { MutableList(100){ "content = $it"} }

                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): MyViewHolder {
                    val view = layoutInflater.inflate(R.layout.item_view_layout, parent, false)
                    view.animate().interpolator = CycleInterpolator(10f)
                    return MyViewHolder(view)
                }

                override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                    holder.getView<TextView>(R.id.tv).text = data[position]
                }

                override fun getItemCount(): Int {
                    return data.size
                }
            }
            itemAnimator = DefaultItemAnimator()

            adapter = mAdapter
            Log.e("MainActivity","onCreate $itemAnimator")
        }
    }

    fun onClick(view:View) {

        mAdapter.notifyItemRemoved(1)
    }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    fun <T : View> getView(id: Int) = view.findViewById<T>(id)

}
