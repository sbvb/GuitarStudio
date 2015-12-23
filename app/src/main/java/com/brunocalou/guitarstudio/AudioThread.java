package com.brunocalou.guitarstudio;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.util.Log;

/**
 * Created by bruno on 21/12/2015.
 */
public class AudioThread extends Thread {

    //Input
    short[] buffer;
    int sample_rate_in_hz = 44100;
    int input_channel_config = AudioFormat.CHANNEL_IN_MONO;
    int buffer_size = 0;
    int audio_format = AudioFormat.ENCODING_PCM_16BIT;
    int audio_source = MediaRecorder.AudioSource.MIC;
    AudioRecord input_audio;

    //Output
    int stream_type = AudioManager.STREAM_MUSIC;
    int output_channel_config = AudioFormat.CHANNEL_OUT_MONO;
    AudioTrack output_audio;

    private boolean is_playing = true;
    String LOG_TAG = "audio_thread";

    public AudioThread() {
        buffer_size = AudioRecord.getMinBufferSize(sample_rate_in_hz, input_channel_config, audio_format);
        buffer = new short[buffer_size];
        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void run() {
        super.run();
        input_audio = new AudioRecord(audio_source, sample_rate_in_hz, input_channel_config, audio_format, buffer_size);
        output_audio = new AudioTrack(stream_type, sample_rate_in_hz, output_channel_config, audio_format, buffer_size, AudioTrack.MODE_STREAM);

        input_audio.startRecording();
        output_audio.play();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                while (is_playing) {
                    input_audio.read(buffer, 0, buffer_size);
                    output_audio.write(buffer, 0, buffer_size);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        input_audio.release();
        output_audio.flush();
        output_audio.release();
        Log.d(LOG_TAG, "Finished thread");
    }

    public boolean isPlaying() {
        return is_playing;
    }

    public void pauseAudio() {
        is_playing = false;
    }

    public void playAudio() {
        is_playing = true;
    }
}
