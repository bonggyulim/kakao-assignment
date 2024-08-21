package com.example.toyprojectkakaoapi.presentation.search

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

class SearchRVAdapter(private var items: List<SearchDocumentModel>) :
    RecyclerView.Adapter<SearchRVAdapter.Holder>() {

    interface ItemClick {
        fun itemClick(position: Int, isLiked: Boolean)
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

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.heart.apply {
            if (items[position].isLiked) {
                setImageResource(R.drawable.icon_favorite)
            } else {
                setImageResource(R.drawable.icon_favorite_border)
            }

            setOnClickListener {
                val isLiked = items[position].isLiked
                itemClick?.itemClick(position, !isLiked)
                items[position].isLiked = !isLiked

                if (isLiked) {
                    setImageResource(R.drawable.icon_favorite_border)
                } else {
                    setImageResource(R.drawable.icon_favorite)
                }
            }
        }

        holder.image.load(items[position].thumbnail)
        holder.date.text = format(items[position].datetime)

    }

    private fun format(date: Date): String {
        val pattern = SimpleDateFormat("yyyy-MM-dd E HH:mm", Locale.KOREAN)
        return pattern.format(date)
    }


}