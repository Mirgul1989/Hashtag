package com.tradehouse.hashtag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.tradehouse.hashtag.adapter.HashtagAdapter
import com.tradehouse.hashtag.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list1 = arrayListOf<String>()
    private lateinit var adapter: HashtagAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = HashtagAdapter()
        binding.recyclerView.adapter = adapter
        initClicker()
    }

    private fun initClicker() {
        with(binding) {
            btnSend.setOnClickListener {
                saved()
            }
            editText.addTextChangedListener {
                if (editText.text.isNotEmpty()) {
                    recyclerView.isVisible = editText.text.toString()[0] == '#'
                    filter(editText.text.toString())
                } else {
                    recyclerView.isVisible = false
                }
            }
        }
    }

    private fun filter(text: String) {
        val newList: ArrayList<String> = ArrayList()
        for (item in list1)
            if (item.contains(text)) {
                newList.add(item)
            }
    adapter.addList(newList)
}

    private fun saved() {
        val words: List<String> = binding.editText.text.split("")
        for (word in words) {
            if (word.startsWith("#")){
                list1.add(word)
            }
        }
        binding.editText.text.clear()
        adapter.addList(list1)
    }
}