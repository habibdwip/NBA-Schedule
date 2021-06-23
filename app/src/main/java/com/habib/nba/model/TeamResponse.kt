package com.habib.nba.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TeamResponse(
    @SerializedName(value = "teams")
    val teams: List<Team>
) {
    @Parcelize
    @Entity
    data class Team(
        @SerializedName(value = "idLeague")
        val idLeague: String?,
        @PrimaryKey
        @ColumnInfo(name = "id_team")
        @SerializedName(value = "idTeam")
        val idTeam: Int?,
        @SerializedName(value = "strTeam")
        val strTeam: String,
        @SerializedName(value = "strCountry")
        val strCountry: String,
        @SerializedName(value = "strDescriptionEN")
        val strDescriptor: String,
        @SerializedName(value = "strLeague")
        val strLeague: String,
        @SerializedName(value = "strSport")
        val strSport: String,
        @SerializedName(value = "strTeamBadge")
        val strTeamBadge: String,
        @SerializedName(value = "strTeamBanner")
        val strTeamBanner: String,
        @SerializedName(value = "strTeamLogo")
        val strTeamLogo: String,
        @SerializedName(value = "strWebsite")
        val strWebsite: String,
    ) : Parcelable
}
