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
public class PhrasesFragment extends Fragment {



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

        words.add(new word( "Where are you going?" ,"minto wuksus" ,R.raw.phrase_where_are_you_going ));
        words.add(new word( "What is your name?" ,"tinnә oyaase'nә" ,R.raw.phrase_what_is_your_name ));
        words.add(new word( "My name is..." ,"oyaaset..." ,R.raw.phrase_my_name_is ));
        words.add(new word( "How are you feeling?" ,"michәksәs?" ,R.raw.phrase_how_are_you_feeling ));
        words.add(new word( "I’m feeling good." ,"kuchi achit" ,R.raw.phrase_im_feeling_good ));
        words.add(new word( "Are you coming?" ,"әәnәs'aa?" ,R.raw.phrase_are_you_coming ));
        words.add(new word( "Yes, I’m coming." ,"hәә’ әәnәm" ,R.raw.phrase_yes_im_coming ));
        words.add(new word( "I’m coming." ,"әәnәm" ,R.raw.phrase_im_coming ));
        words.add(new word( "Let’s go." ,"yoowutis" ,R.raw.phrase_lets_go ));
        words.add(new word( "Come here." ,"әnni'nem" ,R.raw.phrase_come_here ));

        wordAdapter itemsAdapter = new wordAdapter( getActivity(),words,R.color.category_phrases);

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
