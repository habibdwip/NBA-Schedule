package com.habib.nba.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.habib.nba.App.Companion.db
import com.habib.nba.R
import com.habib.nba.model.TeamResponse
import kotlinx.android.synthetic.main.activity_detail_nba.*

class DetailNbaActivity : AppCompatActivity() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channelid"
        private const val CHANNEL_NAME = "channel"
    }

    private var team: TeamResponse.Team? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_nba)
        team = intent.getParcelableExtra("Detail")
        setupView(team)
        ifFavorite()
    }

    private fun ifFavorite() {
        val fav = db.appDao().Favorite(team?.idTeam ?: 0)
        if (fav.isNotEmpty()) isFavorite = true
    }

    private fun setupView(team: TeamResponse.Team?) {
        tv_team_title.text = team?.strTeam
        Glide.with(this)
            .load(team?.strTeamBanner)
            .into(img_banner)
        tv_desc_detail_team.text = team?.strDescriptor
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favourite, menu)
        menuItem = menu
        iconfavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_favorite -> {
                if (isFavorite) {
                    db.appDao().HapusFavorite(team!!)
                    Toast.makeText(this, "${team?.strTeam} berhasil di hapus dari favorite", Toast.LENGTH_SHORT).show()
                } else {
                    sendNotification(team?.strTeam.toString(), team?.strWebsite.toString())
                    db.appDao().TambahFavorite(team!!)
                }
                isFavorite = !isFavorite
                iconfavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun iconfavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorited)
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }


    private fun sendNotification(team: String, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$url"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setContentTitle("Sukses")
            .setContentText("Anda berhasil menambahkan $team ke favorit")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notification = mBuilder.build()
        mNotificationManager.notify(NOTIFICATION_ID, notification)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_NAME
            mBuilder.setChannelId(CHANNEL_ID)
            mNotificationManager.createNotificationChannel(channel)
        }
    }
}