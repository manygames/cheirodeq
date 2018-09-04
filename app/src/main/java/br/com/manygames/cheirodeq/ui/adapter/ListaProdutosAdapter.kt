package br.com.manygames.cheirodeq.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.manygames.cheirodeq.R
import br.com.manygames.cheirodeq.model.Produto
import kotlinx.android.synthetic.main.item_produto.view.*
import java.text.DecimalFormat
import java.util.*

class ListaProdutosAdapter(private val produtos: List<Produto>,
                           private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada: View = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false)
        val produto = produtos[position]

        mostraImagem(viewCriada, produto)
        mostraTipo(viewCriada, produto)
        mostraDias(viewCriada, produto)
        mostraPreco(viewCriada, produto)

        return viewCriada
    }

    fun mostraPreco(viewCriada: View, produto: Produto) {
        val preco = viewCriada.item_pacote_preco
        val precoDoProduto = formataParaBrasileiro(produto)
        preco.text = precoDoProduto
    }

    fun formataParaBrasileiro(produto: Produto): String {
        val formatoBrasileiro = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
        val precoDoProduto = formatoBrasileiro.format(produto.preco).replace("R$", "R$ ")
        return precoDoProduto
    }

    fun mostraDias(viewCriada: View, produto: Produto) {
        val dias = viewCriada.item_pacote_dias
        dias.text = "${produto.dias} dias"
    }

    fun mostraImagem(viewCriada: View, produto: Produto) {
        val imagem = viewCriada.item_pacote_imagem
        val drawable = devolveDrawable(produto)
        imagem.setImageDrawable(drawable)
    }

    fun devolveDrawable(produto: Produto): Drawable? {
        val resources = context.resources
        val idDrawable = resources.getIdentifier(produto.imagem, "drawable", context.packageName)
        val drawable = ContextCompat.getDrawable(context, idDrawable)
        return drawable
    }

    fun mostraTipo(viewCriada: View, produto: Produto) {
        val tipo = viewCriada.item_pacote_local
        tipo.text = produto.tipo
    }

    override fun getItem(posicao: Int): Any {
        return produtos[posicao]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return produtos.size
    }

}
