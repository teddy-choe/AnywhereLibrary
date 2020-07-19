package com.yourssu.anywherelibrary.presentation.library_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yourssu.anywherelibrary.databinding.ItemRankingBinding
import com.yourssu.anywherelibrary.domain.entity.UniversityRanking

class LibraryRankingAdapter(
    private val rankingList: ArrayList<UniversityRanking>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ViewHolder(
                ItemRankingBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ))
    }

    override fun getItemCount(): Int {
        return if (rankingList != null) rankingList!!.size else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ranking: UniversityRanking = rankingList!![position]
        (holder as ViewHolder).bindHolder(ranking, position)
    }

    inner class ViewHolder(
        private val binding: ItemRankingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(itemRanking: UniversityRanking, position: Int) {
            binding.rankingCount.text = (position+1).toString()
            binding.rankingName.text = itemRanking.universityName
            binding.rankingTime.text = itemRanking.learningTime.toString()
        }
    }
}