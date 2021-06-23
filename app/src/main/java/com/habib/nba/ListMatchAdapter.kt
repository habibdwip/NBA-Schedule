package com.habib.nba

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.habib.nba.databinding.ItemMatchBinding
import com.habib.nba.model.MatchResponse

class ListMatchAdapter(private val listSport: List<MatchResponse.Event?>) :
    RecyclerView.Adapter<ListMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listSport.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSport[position])
    }

    class ViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: MatchResponse.Event?) {
            binding.tvAwayTeamMatch.text = event?.strAwayTeam
            binding.tvDateMatch.text = event?.dateEvent
            binding.tvAwayTeamMatch.text = event?.strAwayTeam
            binding.tvHomeTeamMatch.text = event?.strHomeTeam
            binding.tvScoreAwayTeamMatch.text = (event?.intAwayScore ?: "-").toString()
            binding.tvScoreHomeTeamMatch.text = (event?.intHomeScore ?: "-").toString()
        }

    }
}
