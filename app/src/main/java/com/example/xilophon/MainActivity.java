package com.example.xilophon;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final int NR_OF_SIMULTANEOUS_SOUND = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    private SoundPool mSoundPool;

    private int mDoSoundId;
    private int mReSoundId;
    private int mMiSoundId;
    private int mFaSoundId;
    private int mSolSoundId;
    private int mLaSoundId;
    private int mSiSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(NR_OF_SIMULTANEOUS_SOUND)
                .setAudioAttributes(attributes)
                .build();


        mDoSoundId  = mSoundPool.load(this, R.raw.note1_c, 1);
        mReSoundId  = mSoundPool.load(this, R.raw.note2_d, 1);
        mMiSoundId  = mSoundPool.load(this, R.raw.note3_e, 1);
        mFaSoundId  = mSoundPool.load(this, R.raw.note4_f, 1);
        mSolSoundId = mSoundPool.load(this, R.raw.note5_g, 1);
        mLaSoundId  = mSoundPool.load(this, R.raw.note6_a, 1);
        mSiSoundId  = mSoundPool.load(this, R.raw.note7_b, 1);


        View.OnClickListener tastoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();

                if (id == R.id.tasto_do) {
                    play(mDoSoundId);
                } else if (id == R.id.tasto_re) {
                    play(mReSoundId);
                } else if (id == R.id.tasto_mi) {
                    play(mMiSoundId);
                } else if (id == R.id.tasto_fa) {
                    play(mFaSoundId);
                } else if (id == R.id.tasto_sol) {
                    play(mSolSoundId);
                } else if (id == R.id.tasto_la) {
                    play(mLaSoundId);
                } else if (id == R.id.tasto_si) {
                    play(mSiSoundId);
                }
            }
        };

        findViewById(R.id.tasto_do).setOnClickListener(tastoListener);
        findViewById(R.id.tasto_re).setOnClickListener(tastoListener);
        findViewById(R.id.tasto_mi).setOnClickListener(tastoListener);
        findViewById(R.id.tasto_fa).setOnClickListener(tastoListener);
        findViewById(R.id.tasto_sol).setOnClickListener(tastoListener);
        findViewById(R.id.tasto_la).setOnClickListener(tastoListener);
        findViewById(R.id.tasto_si).setOnClickListener(tastoListener);
    }

    private void play(int soundId) {
        mSoundPool.play(soundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSoundPool != null) {
            mSoundPool.release();
            mSoundPool = null;
        }
    }
}