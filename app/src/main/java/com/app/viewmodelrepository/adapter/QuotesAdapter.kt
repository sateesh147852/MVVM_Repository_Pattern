package com.app.viewmodelrepository.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.viewmodelrepository.databinding.QuotesItemBinding
import com.app.viewmodelrepository.model.Result

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {

  private var result: List<Result> = ArrayList()

  fun setData(results: List<Result>) {
    this.result = results
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
    val binding = QuotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return QuotesViewHolder(binding)

  }

  override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
    holder.binding.tvContent.text = result[position].content
    holder.binding.tvAuthor.text = result[position].author
  }

  override fun getItemCount(): Int {
    return result?.size ?: 0
  }

  class QuotesViewHolder(val binding: QuotesItemBinding) : RecyclerView.ViewHolder(binding.root)

}