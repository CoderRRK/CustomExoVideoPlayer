package android.coderrrk.customvideoplayer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView

class NewIntentClass: AppCompatActivity(), Player.Listener {

    lateinit var exoPlayer: ExoPlayer
    lateinit var playerView: PlayerView
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView
    lateinit var request: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        request = intent.getSerializableExtra("WEB_VIEW").toString()

        Log.d("AAA", request)

        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.title)

        setupPlayer()
        addVideoFiles(request)

        if (savedInstanceState !== null){
            savedInstanceState.getInt("mediaItem").let { restoredMedia ->
                val seekTime = savedInstanceState.getLong("SeekTime")
                exoPlayer.seekTo(restoredMedia, seekTime)
                exoPlayer.play()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("SeekTime", exoPlayer.currentPosition)
        outState.putInt("media", exoPlayer.currentMediaItemIndex)
    }

    private fun setupPlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.video_view)
        playerView.player = exoPlayer
        exoPlayer.addListener(this)
    }

    private fun addVideoFiles(request: String) {
        val mediaItem = MediaItem.fromUri(request)
        exoPlayer.addMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility - View.VISIBLE
            }
            Player.STATE_READY -> {
                progressBar.visibility - View.INVISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.release()
    }

    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
        super.onMediaMetadataChanged(mediaMetadata)
        textView.text = mediaMetadata.title?: mediaMetadata.displayTitle?: "not found"
    }


}