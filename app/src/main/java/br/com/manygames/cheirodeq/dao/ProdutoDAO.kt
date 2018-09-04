package br.com.manygames.cheirodeq.dao

import android.content.Context
import br.com.manygames.cheirodeq.model.Produto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProdutoDAO {

    fun lista(context: Context) : List<Produto>{
        val gson = Gson()
        val listaDeProdutos = context.assets.open("produtos.json").bufferedReader().readText()
        val listType = object : TypeToken<List<Produto>>() {}.type

        return gson.fromJson(listaDeProdutos, listType)
    }
}