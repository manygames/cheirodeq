package com.stone.transition

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.ViewGroup
import android.view.WindowManager
import br.com.manygames.cheirodeq.R
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType
import com.nostra13.universalimageloader.core.download.BaseImageDownloader
import com.stone.transition.CommonFragment
import com.stone.transition.CustPagerTransformer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val fragments = ArrayList<CommonFragment>()
    private val imageArray : Array<String> = arrayOf("assets://image1.jpg", "assets://image2.jpg", "assets://image3.jpg", "assets://image4.jpg", "assets://image5.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )

        dealStatusBar()
        initImageLoader()
        fillViewPager()
    }

    private fun fillViewPager() {
        view_pager.setPageTransformer(false, CustPagerTransformer(this))

        for (i in 1..10){
            fragments.add(CommonFragment())
        }

        view_pager.setAdapter(object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val fragment = fragments[position % 10]
                fragment.bindData(imageArray[position % imageArray.size])
                return fragment
            }

            override fun getCount(): Int {
                return 666
            }
        })

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                updateIndicatorTv()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun updateIndicatorTv() {
        val totalNum = view_pager.adapter?.count
        val currentItem = view_pager.currentItem + 1
        indicator_tv.text = Html.fromHtml("<font color='#12edf0'>" + currentItem + "</font>  /  " + totalNum)
    }

    private fun initImageLoader() {
        val config = ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(400, 800)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
                .discCacheSize(50 * 1024 * 1024) // 缓冲大小
                .discCacheFileCount(100) // 缓冲文件数目
                .discCacheFileNameGenerator(HashCodeFileNameGenerator()) // default
                .imageDownloader(BaseImageDownloader(this)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build()
        val imageLoader = ImageLoader.getInstance()
        imageLoader.init(config)
    }

    private fun dealStatusBar() {
        val statusBarHeight = getStatusBarHeight()
        val lp: ViewGroup.LayoutParams = position_view.layoutParams
        lp.height = statusBarHeight
        position_view.layoutParams = lp
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}
