package tv.danmaku.ijk.media.sample;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.sample.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.sample.widget.media.IjkVideoView;

/**
 * Created by shouwang on 15/12/11.
 */
public class MyVideoActivity extends Activity{
    private AndroidMediaController mAndroidMediaController;
    private IjkVideoView mIjkVideoView;
    private TextView mToastTextView;
    private IjkVideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mAndroidMediaController = new AndroidMediaController(this, false);
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        mVideoView.setMediaController(mAndroidMediaController);
        mVideoView.setVideoPath(" http://pili-media.qn-live.meiyaapp.com/recordings/z1.meiyaapp.5667c93bd409d2904f00054e/83.mp4");
        mVideoView.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!mVideoView.isBackgroundPlayEnabled()){
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
