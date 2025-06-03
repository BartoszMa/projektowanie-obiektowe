package com.example.projektowanieobiektowe

object Cart {
    val items = mutableListOf<Product>()
    fun add(product: Product) = items.add(product)
    fun remove(product: Product) = items.remove(product)
}

