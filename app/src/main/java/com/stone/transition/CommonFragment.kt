package com.stone.transition

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import br.com.manygames.cheirodeq.R
import com.nostra13.universalimageloader.core.ImageLoader

/**
 * Created by xmuSistone on 2016/9/18.
 */
class CommonFragment : Fragment(), DragLayout.GotoDetailListener {
    private var imageView: ImageView? = null
    private var address1: View? = null
    private var address2: View? = null
    private var address3: View? = null
    private var address4: View? = null
    private var address5: View? = null
    private var ratingBar: RatingBar? = null
    private var head1: View? = null
    private var head2: View? = null
    private var head3: View? = null
    private var head4: View? = null
    private var imageUrl: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_common, null)
        val dragLayout = rootView.findViewById(R.id.drag_layout) as DragLayout
        imageView = dragLayout.findViewById(R.id.image)
        ImageLoader.getInstance().displayImage(imageUrl, imageView!!)
        address1 = dragLayout.findViewById(R.id.address1)
        address2 = dragLayout.findViewById(R.id.address2)
        address3 = dragLayout.findViewById(R.id.address3)
        address4 = dragLayout.findViewById(R.id.address4)
        address5 = dragLayout.findViewById(R.id.address5)
        ratingBar = dragLayout.findViewById(R.id.rating)

        head1 = dragLayout.findViewById(R.id.head1)
        head2 = dragLayout.findViewById(R.id.head2)
        head3 = dragLayout.findViewById(R.id.head3)
        head4 = dragLayout.findViewById(R.id.head4)

        dragLayout.setGotoDetailListener(this)
        return rootView
    }

    override fun gotoDetail() {
        val activity = context as Activity?
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!,
                Pair(imageView, DetailActivity.IMAGE_TRANSITION_NAME),
                Pair(address1, DetailActivity.ADDRESS1_TRANSITION_NAME),
                Pair(address2, DetailActivity.ADDRESS2_TRANSITION_NAME),
                Pair(address3, DetailActivity.ADDRESS3_TRANSITION_NAME),
                Pair(address4, DetailActivity.ADDRESS4_TRANSITION_NAME),
                Pair(address5, DetailActivity.ADDRESS5_TRANSITION_NAME),
                Pair(ratingBar, DetailActivity.RATINGBAR_TRANSITION_NAME),
                Pair(head1, DetailActivity.HEAD1_TRANSITION_NAME),
                Pair(head2, DetailActivity.HEAD2_TRANSITION_NAME),
                Pair(head3, DetailActivity.HEAD3_TRANSITION_NAME),
                Pair(head4, DetailActivity.HEAD4_TRANSITION_NAME)
        )
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_IMAGE_URL, imageUrl)
        ActivityCompat.startActivity(activity!!, intent, options.toBundle())
    }

    fun bindData(imageUrl: String) {
        this.imageUrl = imageUrl
    }
}