package com.example.projektowanieobiektowe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ProductListActivity : AppCompatActivity() {

    private val allProducts = listOf(
        Product(1, "Laptop", 3000.0, 1),
        Product(2, "Smartfon", 2000.0, 1),
        Product(3, "Harry Potter", 40.0, 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val categoryId = intent.getIntExtra("categoryId", -1)
        val products = allProducts.filter { it.categoryId == categoryId }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ProductAdapter(products) { product ->
            Cart.add(product)
            Toast.makeText(this, "${product.name} dodano do koszyka", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnGoToCart).setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}
