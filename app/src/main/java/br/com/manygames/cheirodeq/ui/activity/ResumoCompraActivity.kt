package br.com.manygames.cheirodeq.ui.activity

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import br.com.manygames.cheirodeq.R
import br.com.manygames.cheirodeq.model.Produto
import br.com.manygames.cheirodeq.ui.activity.ProdutoConstantes.Companion.CHAVE_PRODUTO
import kotlinx.android.synthetic.main.activity_resumo_compra.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ResumoCompraActivity : AppCompatActivity() {

    private val tituloAppBar = "Resumo da Compra"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo_compra)
        title = tituloAppBar

        if (intent.hasExtra(CHAVE_PRODUTO)) {
            val produto = intent.getSerializableExtra(CHAVE_PRODUTO) as Produto
            inicializaCampos(produto)
        }
    }

    fun inicializaCampos(produto: Produto) {
        mostraTipo(produto)
        mostraImagem(produto)
        mostraData(produto)
        mostraPreco(produto)
    }

    fun mostraPreco(produto: Produto) {
        resumo_compra_preco_pacote.text = formataParaBrasileiro(produto)
    }

    fun mostraData(produto: Produto) {
        val dataIda = Calendar.getInstance()
        val dataVolta = Calendar.getInstance()
        dataVolta.add(Calendar.DAY_OF_MONTH, produto.dias)
        val formatoBrasileiro = SimpleDateFormat("dd/MM")
        val dataFormatadaIda = formatoBrasileiro.format(dataIda.time)
        val dataFormatadaVolta = formatoBrasileiro.format(dataVolta.time)
        val dataFormatadaFinal = "${dataFormatadaIda} - ${dataFormatadaVolta} de ${dataVolta.get(Calendar.YEAR)}"
        resumo_compra_data.text = dataFormatadaFinal
    }

    fun mostraImagem(produto: Produto) {
        resumo_compra_imagem_pacote.setImageDrawable(devolveDrawable(produto, this))
    }

    fun mostraTipo(produto: Produto) {
        resumo_compra_tipo_produto.text = produto.tipo
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
