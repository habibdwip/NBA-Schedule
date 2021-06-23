package com.habib.nba

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.habib.nba.databinding.ItemTeamBinding
import com.habib.nba.model.TeamResponse
import com.habib.nba.view.DetailNbaActivity

class ListTeamAdapter(private val listTeam: List<TeamResponse.Team?>) :
        RecyclerView.Adapter<ListTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTeam[position])
    }

    class ViewHolder(private val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: TeamResponse.Team?) {
            binding.txtTeamName.text = team?.strTeam
            binding.txtTeamCountry.text = team?.strCountry
            glideAdapter(binding.root, team?.strTeamBadge, binding.imgTeam)
            binding.root.setOnClickListener {
                val intent = Intent(it.context, DetailNbaActivity::class.java)
                intent.putExtra("Detail", team)
                it.context.startActivity(intent)
            }
        }

        private fun glideAdapter(context: View, resource: String?, place: ImageView) =
                Glide.with(context)
                        .load(resource)
                        .into(place)
    }
}
