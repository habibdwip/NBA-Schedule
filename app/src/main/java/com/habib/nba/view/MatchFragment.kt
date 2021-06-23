package com.habib.nba.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.habib.nba.*
import com.habib.nba.model.MatchResponse
import com.habib.nba.net.ApiService
import com.habib.nba.net.RetrofitClient
import kotlinx.android.synthetic.main.fragment_match.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchFragment : Fragment() {

    private val api by lazy {
        RetrofitClient.createService(ApiService::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = api.getMatchNba()
        request.enqueue(object : Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                val data = response.body()?.events
                val matchAdapter = ListMatchAdapter(data!!)
                listMatch.adapter = matchAdapter
            }

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}