package com.example.projektowanieobiektowe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektowanieobiektowe.ui.theme.ProjektowanieObiektoweTheme

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        Category(1, "Elektronika"),
        Category(2, "Książki"),
        Category(3, "Ubrania")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = CategoryAdapter(categories) { category ->
            val intent = Intent(this, ProductListActivity::class.java)
            intent.putExtra("categoryId", category.id)
            startActivity(intent)
        }
    }
}
