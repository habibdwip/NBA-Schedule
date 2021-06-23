package com.habib.nba.net

import com.habib.nba.model.MatchResponse
import com.habib.nba.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("lookup_all_teams.php?id=4387")
    fun getTeams(): Call<TeamResponse>

    @GET("eventspastleague.php?id=4387")
    fun getMatchNba(): Call<MatchResponse>

}