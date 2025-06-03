package com.example.projektowanieobiektowe

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ProductAdapter(Cart.items) {}

        val total = Cart.items.sumOf { it.price }
        findViewById<TextView>(R.id.totalPrice).text = "Suma: $total zł"
    }
}
