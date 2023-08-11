package com.example.navigationfirstproject.ui.bookdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.navigationfirstproject.databinding.BookauthorListItemBinding

class BookAuthorAdapter(val context: Context,private val books:List<BookAuthorModel>):RecyclerView.Adapter<BookAuthorAdapter.CustomViewHolder>() {


    class CustomViewHolder(binding: BookauthorListItemBinding):RecyclerView.ViewHolder(binding.root) {
        val tvBookName=binding.tvBookName
        val tvAuthorName=binding.tvAuthorName
        val ivBookImage=binding.ivBookImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = BookauthorListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  books.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val books =books[position]

        holder.tvBookName.text = books.bookName
        holder.tvAuthorName.text =books.authorName
        holder.ivBookImage.load(books.imageUrl)
    }
}