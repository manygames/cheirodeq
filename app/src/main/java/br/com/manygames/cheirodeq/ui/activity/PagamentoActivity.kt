package br.com.manygames.cheirodeq.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.manygames.cheirodeq.R
import br.com.manygames.cheirodeq.model.Produto
import br.com.manygames.cheirodeq.ui.activity.ProdutoConstantes.Companion.CHAVE_PRODUTO
import kotlinx.android.synthetic.main.activity_pagamento.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

class PagamentoActivity : AppCompatActivity() {

    private val tituloAppBar = "Pagamento"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagamento)
        title = tituloAppBar

        carregaProdutoRecebido()
    }

    fun carregaProdutoRecebido() {
        if (intent.hasExtra(CHAVE_PRODUTO)) {
            val produto = intent.getSerializableExtra(CHAVE_PRODUTO) as Produto
            pagamento_preco_pacote.text = formataParaBrasileiro(produto)

            configuraBotao(produto)
        }
    }

    fun configuraBotao(produto: Produto) {
        pagamento_botao_finaliza_compra.setOnClickListener {
            vaiParaResumoCompra(produto)
        }
    }

    fun vaiParaResumoCompra(produto: Produto) {
        val intent = Intent(this@PagamentoActivity,
                ResumoCompraActivity::class.java)
        intent.putExtra(CHAVE_PRODUTO, produto)
        startActivity(intent)
    }


    fun formataParaBrasileiro(produto: Produto): String {
        val formatoBrasileiro = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
        val precoDoProduto = formatoBrasileiro.format(produto.preco).replace("R$", "R$ ")
        return precoDoProduto
    }
}
