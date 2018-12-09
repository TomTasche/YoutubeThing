package at.tomtasche.youtubething;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    private static final boolean CONTROL_VOLUME = false;

    private YouTubePlayerView playerView;
    private YouTubePlayer player;

    private AudioManager audioManager;

    private int quitCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player);
        playerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                playerView.initialize("AIzaSyCO4_yFazqBwoQrbBYOD9n-iTZlyIx0Ehk", MainActivity.this);
            }
        }, 5000);

        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer player,
                                        boolean wasRestored) {
        this.player = player;

        player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

        player.loadPlaylist("PLC8jQuLiB1XdIp0_sE_RfIZYNBaWU3BS0");

        playerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MainActivity.this.player.isPlaying()) {
                    return;
                }

                MainActivity.this.player.play();
            }
        }, 5000);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        restart();
    }

    private void restart() {
        playerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                recreate();
            }
        }, 3000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!CONTROL_VOLUME) {
            return super.onKeyDown(keyCode, event);
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (player == null) {
            return super.onKeyUp(keyCode, event);
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            player.next();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            player.previous();

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            if (player.isPlaying()) {
                player.pause();
            } else {
                player.play();
            }

            return true;
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            quitCounter++;

            if (quitCounter == 3) {
                finish();
            }

            playerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    quitCounter = 0;
                }
            }, 15000);

            return true;
        }

        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onBackPressed() {
       // do nothing to override default behavior
    }
}
