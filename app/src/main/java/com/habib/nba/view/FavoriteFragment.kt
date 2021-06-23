package com.habib.nba.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.habib.nba.App.Companion.db
import com.habib.nba.ListTeamAdapter
import com.habib.nba.R
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tampilData()
    }

    private fun tampilData() {
        val datafavorit = db.appDao().getTeamNba()
        val adapterTeam = ListTeamAdapter(datafavorit)
        val linearLayoutManager =
            LinearLayoutManager(requireContext()).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
        listTeam.apply {
            adapter = adapterTeam
            layoutManager = linearLayoutManager
        }
    }

    override fun onResume() {
        super.onResume()
        tampilData()
    }
}