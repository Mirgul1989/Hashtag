package com.tradehouse.hashtag.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tradehouse.hashtag.databinding.ItemHashBinding

class HashtagAdapter :RecyclerView.Adapter<HashtagAdapter.HashtagViewHolder>() {

    private val list: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashtagViewHolder {
        return HashtagViewHolder(
            ItemHashBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HashtagViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addList(data: List<String>) {
        this.list.clear()
        this.list.addAll(data)
        this.list.sort()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HashtagViewHolder(private val binding: ItemHashBinding) :
      RecyclerView.ViewHolder(binding.root) {
        fun bind(s: String) {
            val ss = SpannableString(s)
            val words: List<String> = ss.split(" ")
            for (word in words) {
                if (word.startsWith("#")) {
                    val clickableSpan: ClickableSpan = object : ClickableSpan() {
                        override fun onClick(textView: View) {
                        }
                    }
                    ss.setSpan(
                        clickableSpan,
                        ss.indexOf(word),
                        ss.indexOf(word) + word.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            binding.tvHash.text=ss
            binding.tvHash.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}