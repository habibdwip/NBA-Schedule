package com.habib.nba.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.habib.nba.model.TeamResponse

@Dao
interface AppDao {
    @Query("SELECT * FROM team")
    fun getTeamNba(): List<TeamResponse.Team>

    @Query("SELECT * FROM team WHERE id_team=:id")
    fun Favorite(id: Int): List<TeamResponse.Team>

    @Insert
    fun TambahFavorite(team: TeamResponse.Team)

    @Delete
    fun HapusFavorite(team: TeamResponse.Team)
}
