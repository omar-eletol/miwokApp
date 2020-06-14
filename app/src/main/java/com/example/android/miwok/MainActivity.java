/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this,getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

       TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

       tabLayout.setupWithViewPager(viewPager);
    }
}











      /**  // make an onclickListener to numbers

        TextView number = (TextView) findViewById(R.id.numbers);
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,numbersActivity.class);
                startActivity(intent);

            }
        });

        // make an onclickListener to family

        TextView family = (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(intent);

            }
        });

        // make an onclickListener to phrases

        TextView phrases = (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,phrasesActivity.class);
                startActivity(intent);

            }
        });

        // make an onclickListener to colors

        TextView colors = (TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(intent);

            }
        });



    }
}

**/




//    public void openNumberCategory (View view){
//
//        Intent intent = new Intent(this,numbersActivity.class);
//        TextView editText = (TextView) findViewById(R.id.numbers);
//        startActivity(intent);
//
//    }

//    public void openFamilyCategory (View view){
//
//        Intent intent = new Intent(this,FamilyActivity.class);
//        startActivity(intent);
//
//    }
//
//    public void openColorsCategory (View view){
//
//        Intent intent = new Intent(this,ColorsActivity.class);
//        startActivity(intent);
//
//    }
//    public void openPhrasesCategory (View view){
//
//        Intent intent = new Intent(this,phrasesActivity.class);
//        startActivity(intent);
//
//    }
//}
