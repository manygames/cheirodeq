package br.com.manygames.cheirodeq.model

import java.io.Serializable
import java.math.BigDecimal

class Produto (val imagem: String,
               val tipo: String,
               val dias: Int,
               val preco: BigDecimal) : Serializable{
}