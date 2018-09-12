package br.com.manygames.cheirodeq.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.manygames.cheirodeq.R
import com.gtomato.android.ui.transformer.FlatMerryGoRoundTransformer
import kotlinx.android.synthetic.main.activity_my_carousel.*

class MyCarouselActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_carousel)

        carousel.transformer = FlatMerryGoRoundTransformer()
//        carousel.adapter = MyAdapter()
    }
}
