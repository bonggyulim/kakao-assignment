package com.example.toyprojectkakaoapi.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.toyprojectkakaoapi.databinding.RvSearchBinding
import com.example.toyprojectkakaoapi.presentation.model.SearchModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SearchRVAdapter(private var items: List<SearchModel>): RecyclerView.Adapter<SearchRVAdapter.LatestGroupListAdapterHolder>() {

    interface ItemClick{
        fun itemClick(position: Int)
    }
    var itemClick: ItemClick? = null

    class LatestGroupListAdapterHolder(binding: RvSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivItem
        val date = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestGroupListAdapterHolder {
        val binding = RvSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestGroupListAdapterHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LatestGroupListAdapterHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.itemClick(position)
        }
        holder.image.load(items[position].thumbnail)
        holder.date.text = format(items[position].datetime)

    }

    private fun format(date: Date): String {
        val pattern = SimpleDateFormat("yyyy-MM-dd E HH:mm", Locale.KOREAN)
        return pattern.format(date)
    }


}