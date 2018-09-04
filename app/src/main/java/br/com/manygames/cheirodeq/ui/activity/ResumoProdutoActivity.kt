package br.com.manygames.cheirodeq.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import br.com.manygames.cheirodeq.R
import br.com.manygames.cheirodeq.model.Produto
import br.com.manygames.cheirodeq.ui.activity.ProdutoConstantes.Companion.CHAVE_PRODUTO
import kotlinx.android.synthetic.main.activity_resumo_produto.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ResumoProdutoActivity : AppCompatActivity() {

    private val tituloAppBar = "Resumo do Produto"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo_produto)
        title = tituloAppBar

        carregaPacoteRecebido(intent)
    }

    fun carregaPacoteRecebido(intent: Intent) {
        if (intent.hasExtra(CHAVE_PRODUTO)) {
            val produto: Produto = intent.getSerializableExtra(CHAVE_PRODUTO) as Produto
            inicializaCampos(produto)
            configuraBotao(produto)
        }
    }

    fun configuraBotao(produto: Produto) {
        resumo_produto_botao_realiza_pagamento.setOnClickListener {
            vaiParaPagamento(produto)
        }
    }

    fun vaiParaPagamento(produto: Produto) {
        val intent = Intent(this@ResumoProdutoActivity,
                PagamentoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivity(intent)
    }

    fun inicializaCampos(produto: Produto) {
        mostraTipo(produto)
        mostraImagem(produto)
        mostraDias(produto)
        mostraPreco(produto)
        mostraData(produto)
    }

    fun mostraDias(produto: Produto) {
        resumo_produto_dias.text = "${produto.dias} dias"
    }

    fun mostraData(produto: Produto) {
        val dataIda = Calendar.getInstance()
        val dataVolta = Calendar.getInstance()
        dataVolta.add(Calendar.DAY_OF_MONTH, produto.dias)
        val formatoBrasileiro = SimpleDateFormat("dd/MM")
        val dataFormatadaIda = formatoBrasileiro.format(dataIda.time)
        val dataFormatadaVolta = formatoBrasileiro.format(dataVolta.time)
        val dataFormatadaFinal = "${dataFormatadaIda} - ${dataFormatadaVolta} de ${dataVolta.get(Calendar.YEAR)}"
        resumo_produto_data.text = dataFormatadaFinal
    }

    fun mostraPreco(produto: Produto) {
        val precoDoProduto = formataParaBrasileiro(produto)
        resumo_produto_preco.text = precoDoProduto
    }

    fun mostraImagem(produto: Produto) {
        val drawable = devolveDrawable(produto, this)
        resumo_produto_imagem.setImageDrawable(drawable)
    }

    fun mostraTipo(produto: Produto) {
        resumo_produto_tipo.text = produto.tipo
    }

    fun devolveDrawable(produto: Produto, context: Context): Drawable? {
        val resources = context.resources
        val idDrawable = resources.getIdentifier(produto.imagem, "drawable", context.packageName)
        val drawable = ContextCompat.getDrawable(context, idDrawable)
        return drawable
    }

    fun formataParaBrasileiro(produto: Produto): String {
        val formatoBrasileiro = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
        val precoDoProduto = formatoBrasileiro.format(produto.preco).replace("R$", "R$ ")
        return precoDoProduto
    }
}
