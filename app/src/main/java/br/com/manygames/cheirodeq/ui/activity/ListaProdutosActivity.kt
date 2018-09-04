package br.com.manygames.cheirodeq.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.manygames.cheirodeq.R
import br.com.manygames.cheirodeq.dao.ProdutoDAO
import br.com.manygames.cheirodeq.model.Produto
import br.com.manygames.cheirodeq.ui.activity.ProdutoConstantes.Companion.CHAVE_PRODUTO
import br.com.manygames.cheirodeq.ui.adapter.ListaProdutosAdapter
import kotlinx.android.synthetic.main.activity_lista_produtos.*

class ListaProdutosActivity : AppCompatActivity() {

    private val tituloAppBar = "Produtos"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)
        title = tituloAppBar

        configuraLista()
    }

    fun configuraLista() {
        val produtos = ProdutoDAO().lista(this)
        val listaProdutosAdapter = ListaProdutosAdapter(produtos, this)
        with(lista_produtos_listview){
            adapter = listaProdutosAdapter
            setOnItemClickListener {_, _, position, id ->
                val produtoClicado = produtos[position]
                vaiParaResumoProduto(produtoClicado)
            }
        }
    }


    fun vaiParaResumoProduto(produtoClicado: Produto) {
        val intent = Intent(this@ListaProdutosActivity,
                ResumoProdutoActivity::class.java)
        intent.putExtra(CHAVE_PRODUTO, produtoClicado)
        startActivity(intent)
    }
}
