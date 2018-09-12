package com.stone.transition

import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar

import com.nostra13.universalimageloader.core.ImageLoader

import android.R.attr.rating
import br.com.manygames.cheirodeq.R

/**
 * Created by xmuSistone on 2016/9/19.
 */
class DetailActivity : FragmentActivity() {

    private var address1: View? = null
    private var address2: View? = null
    private var address3: View? = null
    private var address4: View? = null
    private var address5: View? = null
    private var imageView: ImageView? = null
    private var ratingBar: RatingBar? = null

    private var listContainer: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageView = findViewById(R.id.image) as ImageView
        address1 = findViewById(R.id.address1)
        address2 = findViewById(R.id.address2)
        address3 = findViewById(R.id.address3)
        address4 = findViewById(R.id.address4)
        address5 = findViewById(R.id.address5)
        ratingBar = findViewById(R.id.rating) as RatingBar
        listContainer = findViewById(R.id.detail_list_container) as LinearLayout

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window = window
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        ImageLoader.getInstance().displayImage(imageUrl, imageView!!)

        ViewCompat.setTransitionName(imageView, IMAGE_TRANSITION_NAME)
        ViewCompat.setTransitionName(address1, ADDRESS1_TRANSITION_NAME)
        ViewCompat.setTransitionName(address2, ADDRESS2_TRANSITION_NAME)
        ViewCompat.setTransitionName(address3, ADDRESS3_TRANSITION_NAME)
        ViewCompat.setTransitionName(address4, ADDRESS4_TRANSITION_NAME)
        ViewCompat.setTransitionName(address5, ADDRESS5_TRANSITION_NAME)
        ViewCompat.setTransitionName(ratingBar, RATINGBAR_TRANSITION_NAME)

        dealListView()
    }

    private fun dealListView() {
        val layoutInflater = LayoutInflater.from(this)

        for (i in 0..19) {
            val childView = layoutInflater.inflate(R.layout.detail_list_item, null)
            listContainer!!.addView(childView)
            val headView = childView.findViewById(R.id.head) as ImageView
            if (i < headStrs.size) {
                headView.setImageResource(imageIds[i % imageIds.size])
                ViewCompat.setTransitionName(headView, headStrs[i])
            }
        }
    }

    companion object {

        val EXTRA_IMAGE_URL = "detailImageUrl"

        val IMAGE_TRANSITION_NAME = "transitionImage"
        val ADDRESS1_TRANSITION_NAME = "address1"
        val ADDRESS2_TRANSITION_NAME = "address2"
        val ADDRESS3_TRANSITION_NAME = "address3"
        val ADDRESS4_TRANSITION_NAME = "address4"
        val ADDRESS5_TRANSITION_NAME = "address5"
        val RATINGBAR_TRANSITION_NAME = "ratingBar"

        val HEAD1_TRANSITION_NAME = "head1"
        val HEAD2_TRANSITION_NAME = "head2"
        val HEAD3_TRANSITION_NAME = "head3"
        val HEAD4_TRANSITION_NAME = "head4"
        private val headStrs = arrayOf(HEAD1_TRANSITION_NAME, HEAD2_TRANSITION_NAME, HEAD3_TRANSITION_NAME, HEAD4_TRANSITION_NAME)
        private val imageIds = intArrayOf(R.drawable.head1, R.drawable.head2, R.drawable.head3, R.drawable.head4)
    }
}