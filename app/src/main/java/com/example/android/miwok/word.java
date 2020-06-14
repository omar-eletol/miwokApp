package com.example.android.miwok;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

public class word {

   private String  mMiwokTranslation  ;
   private String  mDefaultTranslation ;
   private int     mImageResourceId = noImageResource ;
   private static final  int noImageResource = -1 ;
   private int mMediaPlayer ;





   public word (String defaultTranslation , String miwokTranslation , int mediaPlayer ){

       mDefaultTranslation=defaultTranslation;
       mMiwokTranslation=miwokTranslation;
       mMediaPlayer=mediaPlayer;
   }

    public word(String miwokTranslation, String defaultTranslation, int imageResourceId , int mediaPlayer ) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageResourceId = imageResourceId;
        mMediaPlayer=mediaPlayer;

    }




    public String  getDefaultTranslation (){

        return mDefaultTranslation ;

    }

    public String getMiwokTranslation () {

        return mMiwokTranslation ;
    }

    public int getImageId () {

       return mImageResourceId ;
    }

    public boolean hasImage () {

       return mImageResourceId != noImageResource ;
    }


    public int getMediaPlayer(){

       return mMediaPlayer ;
    }


}
