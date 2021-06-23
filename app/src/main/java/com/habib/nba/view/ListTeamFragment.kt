package com.habib.nba.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.habib.nba.*
import com.habib.nba.model.TeamResponse
import com.habib.nba.net.ApiService
import com.habib.nba.net.RetrofitClient
import kotlinx.android.synthetic.main.fragment_list_team.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListTeamFragment : Fragment() {

    private val api by lazy {
        RetrofitClient.createService(ApiService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTeam()
    }

    private fun getTeam(){
        val request = api.getTeams()
        request.enqueue(object : Callback<TeamResponse>{
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                val data = response.body()?.teams
                val adapterTeam = ListTeamAdapter(data!!)
                listTeam.adapter = adapterTeam
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}