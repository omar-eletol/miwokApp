package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class wordAdapter extends ArrayAdapter<word> {

    private int mColorResourceId ;

    public wordAdapter(@NonNull Context context,  @NonNull ArrayList<word> objects ,int colorResourceId  ) {
        super(context, 0, objects );

        mColorResourceId=colorResourceId;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        word currentword = getItem(position);

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
    }


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.miwokWord);
        // Get the miwok Word  from the current word object and
        // set this text on the name TextView
        nameTextView.setText(currentword.getMiwokTranslation());

        TextView name2TextView = (TextView) listItemView.findViewById(R.id.englishWord);
        // Get the english word  from the current word object object and
        // set this text on the name TextView
        name2TextView.setText(currentword.getDefaultTranslation());

        ImageView iconView = (ImageView)  listItemView.findViewById(R.id.image);
        // Get the image resource id  from the current word object
        // And set this image resource on the Image view

        if (currentword.hasImage()) {

            //set image into wanted catagory
            iconView.setImageResource(currentword.getImageId());

            iconView.setVisibility(View.VISIBLE);
        }
        else {

            //remove the image from wanted catagory
            iconView.setVisibility(View.GONE);

        }


        View textcontainer =listItemView.findViewById(R.id.text_container);

        int color = ContextCompat.getColor(getContext(),mColorResourceId);

        textcontainer.setBackgroundColor(color);





        return listItemView ;
}





}
