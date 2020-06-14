package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {



    private MediaPlayer mediaPlayer ;

    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionlistener =  new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Permanent loss of audio focus
                        // Pause playback immediately
                        mediaPlayer.stop();
                        mediaPlayer.seekTo(0);

                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // resume playback
                        mediaPlayer.start();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                        releaseMediaPlayer();

                    }
                }
            };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.world_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<word> words = new ArrayList<word>() ;

        words.add(new word( "father" ,"әpә" ,R.drawable.family_father , R.raw.family_father ));
        words.add(new word( "mother" ,"әṭa" ,R.drawable.family_mother , R.raw.family_mother));
        words.add(new word( "son" ,"angsi" ,R.drawable.family_son , R.raw.family_son ));
        words.add(new word( "daughter" ,"tune" ,R.drawable.family_daughter , R.raw.family_daughter ));
        words.add(new word( "older brother" ,"taachi" ,R.drawable.family_older_brother
                , R.raw.family_older_brother ));
        words.add(new word( "younger brother" ,"chalitti" ,R.drawable.family_younger_brother
                , R.raw.family_younger_brother ));
        words.add(new word( "older sister","teṭe" ,R.drawable.family_older_sister
                , R.raw.family_older_sister ));
        words.add(new word( "younger sister" ,"kolliti" ,R.drawable.family_younger_sister
                , R.raw.family_younger_sister ));
        words.add(new word( "grandmother " ,"ama" ,R.drawable.family_grandmother
                , R.raw.family_grandmother ));
        words.add(new word( "grandfather" ,"paapa" ,R.drawable.family_grandfather
                , R.raw.family_grandfather ));


        wordAdapter itemsAdapter = new wordAdapter( getActivity() ,words,R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                word word = words.get(position);
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(getActivity(), word.getMediaPlayer());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionlistener);
                }
            }
        });

        return rootView;
    }



    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

}
