package com.example.toolbarapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val variants = listOf<String>(
        "user1",
        "user2",
        "fakeUser",
        "unknown"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolBar.setNavigationOnClickListener {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
        }

        val searchItem = toolBar.menu.findItem(R.id.menu_search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                Toast.makeText(this@MainActivity, "Expanded", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                Toast.makeText(this@MainActivity, "Collapsed", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        (searchItem.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                variants.filter { it -> it.contains(newText ?: "", ignoreCase = true) }
                        .joinToString(separator = ", ")
                        .let { it -> searchResult.text = it }
                return true
            }
        })
    }
}
