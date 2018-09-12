package br.com.manygames.cheirodeq.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import br.com.manygames.cheirodeq.R
import br.com.manygames.cheirodeq.ui.controller.ProdutosController
import kotlinx.android.synthetic.main.activity_carousel.*

class CarouselActivity : AppCompatActivity() {
//    private val controller: ProdutosController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel)

        recycler_view_carousel.layoutManager = GridLayoutManager(this, 2)
//        recycler_view_carousel.setController(controller)
    }
}
