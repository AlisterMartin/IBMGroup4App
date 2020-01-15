package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TalkSelector extends AppCompatActivity {
    private ArrayList<Integer> talkIndexes = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.talk_selector);

        int tempViewID;
        int prevIndex;
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        final int width = (dm.widthPixels - 120) / 3;

        List<Confrence> talks = Data.confrences;
        final ConstraintLayout layout = findViewById(R.id.ProgramaticConstraintLayout);

        TextView text1 = new TextView(getApplicationContext());
        text1.setId(View.generateViewId());
        text1.setText(Data.confrences.get(0).startTime + " - " + Data.confrences.get(0).endTime);
        text1.setTextSize(25);
        text1.setPadding(20, 20, 20, 20);
        text1.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(text1);
        ConstraintSet set1 = new ConstraintSet();
        set1.clone(layout);
        set1.connect(text1.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 400);
        set1.connect(text1.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
        tempViewID = text1.getId();
        set1.applyTo(layout);

        TextView text2 = new TextView(getApplicationContext());
        text2.setText(talks.get(0).title);
        text2.setId(View.generateViewId());
        text2.setBackgroundResource(R.color.lightRed);
        text2.setTextSize(20);
        text2.setPadding(20, 20, 20, 20);
        text2.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        final int temp2 = 0;
        final int tmpid2 = text2.getId();
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                for (int i = 0; i < talkIndexes.size(); i++) {
                    if (!flag && talkIndexes.get(i) == temp2) {
                        System.out.println(temp2 + " deactivate");
                        talkIndexes.remove(i);
                        layout.getViewById(tmpid2).setBackgroundResource(R.color.lightRed);
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println(temp2 + " activate");
                    talkIndexes.add(temp2);
                    layout.getViewById(tmpid2).setBackgroundResource(R.color.lightGreen);
                }
            }
        });
        layout.addView(text2);
        ConstraintSet set2 = new ConstraintSet();
        set2.clone(layout);
        set2.connect(text2.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.BOTTOM, 30);
        set2.connect(text2.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
        tempViewID = text2.getId();
        set2.applyTo(layout);

        for (int i = 1; i < talks.size(); i++) {
            if (!Data.confrences.get(i - 1).startTime.equalsIgnoreCase(Data.confrences.get(i).startTime)) {
                TextView text = new TextView(getApplicationContext());
                text.setText(Data.confrences.get(i).startTime);
                text.setId(View.generateViewId());
                text.setTextSize(25);
                text.setPadding(20, 20, 20, 20);
                text.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.addView(text);
                ConstraintSet set = new ConstraintSet();
                set.clone(layout);
                set.connect(text.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.BOTTOM, 30);
                set.connect(text.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
                tempViewID = text.getId();
                set.applyTo(layout);
            }
            TextView text = new TextView(getApplicationContext());
            text.setText(talks.get(i).title);
            text.setId(View.generateViewId());
            text.setBackgroundResource(R.color.lightRed);
            text.setTextSize(20);
            text.setPadding(20, 20, 20, 20);
            text.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final int temp = i;
            final int tmpid = text.getId();
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean flag = false;
                    for (int i = 0; i < talkIndexes.size(); i++) {
                        if (!flag && talkIndexes.get(i) == temp) {
                            System.out.println(temp + " deactivate");
                            talkIndexes.remove(i);
                            layout.getViewById(tmpid).setBackgroundResource(R.color.lightRed);
                            flag = true;
                        }
                    }
                    if (!flag) {
                        System.out.println(temp + " activate");
                        talkIndexes.add(temp);
                        layout.getViewById(tmpid).setBackgroundResource(R.color.lightGreen);
                    }
                }
            });
            layout.addView(text);
            ConstraintSet set = new ConstraintSet();
            set.clone(layout);
            set.connect(text.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.BOTTOM, 30);
            set.connect(text.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
            tempViewID = text.getId();
            set.applyTo(layout);

        }
        /*FloatingActionButton fab = findViewById(R.id.FABdone);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (talkIndexes.size() > 0) {
                    for (int i : talkIndexes) {
                        ArrayList<String> tags = Data.getUniqueTags();
                        Data.userSelectedTags.add(tags.get(i));
                    }
                    finish();
                }
            }
        });*/
    }
}
