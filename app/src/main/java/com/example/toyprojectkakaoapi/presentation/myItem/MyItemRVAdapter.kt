package com.example.toyprojectkakaoapi.presentation.myItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.toyprojectkakaoapi.R
import com.example.toyprojectkakaoapi.databinding.RvSearchBinding
import com.example.toyprojectkakaoapi.presentation.model.SearchDocumentModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MyItemRVAdapter(private var items: MutableList<SearchDocumentModel>): RecyclerView.Adapter<MyItemRVAdapter.Holder>() {

    interface ItemClick{
        fun itemClick(position: Int)
    }
    var itemClick: ItemClick? = null

    class Holder(binding: RvSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivItem
        val date = binding.tvItem
        val heart = binding.ivHeart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RvSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.heart.setOnClickListener {
            itemClick?.itemClick(position)
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }
        holder.heart.setImageResource(R.drawable.icon_favorite)
        holder.image.load(items[position].thumbnail)
        holder.date.text = format(items[position].datetime)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    private fun format(date: Date): String {
        val pattern = SimpleDateFormat("yyyy-MM-dd E HH:mm", Locale.KOREAN)
        return pattern.format(date)
    }
}