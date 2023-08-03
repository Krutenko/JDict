package com.example.jdict;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jdict.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private final String[][] dict = {
            { "人", "ひと", "hito", "person" },
            { "人間", "にんげん", "ningen", "human" },
            { "私", "わたし", "watashi", "I" },
            { "僕", "ぼく", "boku", "I (masculine)" },
            { "前", "まえ", "mae", "before" },
            { "後", "ご", "go", "after" },
            { "口", "くち", "kuchi", "mouth" },
            { "日", "ひ", "hi", "day, sun" },
            { "日", "にち、か", "nichi, ka", "counter for days" },
            { "目", "め", "me", "eye" },
            { "月", "つき", "tsuki", "Moon, month" },
            { "先月", "せんげつ", "sengetsu", "last month" },
            { "今月", "こんげつ", "kongetsu", "this month" },
            { "来月", "らいげつ", "raigetsu", "next month" },
            { "毎月", "まいつき", "maitsuki", "every month" },
            { "週", "しゅう", "shuu", "week" },
            { "火", "ひ", "hi", "fire" },
            { "上", "うえ", "ue", "up" },
            { "下", "した", "shita", "down" },
            { "中", "なか", "naka", "middle" },
            { "左", "ひだり", "hidari", "left" },
            { "右", "みぎ", "migi", "right" }
    };
    public void init_words_list() {
        TableLayout stk = findViewById(R.id.table_main);
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(com.google.android.material.R.attr.colorOnSecondary, typedValue, true);
        int text_color = ContextCompat.getColor(this, typedValue.resourceId);
        int grid_color = text_color;
        int grid_thick = 5;
        int i;
        double[] widths = { 0.3, 0.3, 0.4 };
        for (i = 0; i < dict.length; i++) {
            TableRow tbrow = new TableRow(this);
            for (int j = 0; j < 5; j++) {
                TextView tv = new TextView(this);
                if (j % 2 == 1)
                {
                    tv.setBackgroundColor(grid_color);
                    tv.setWidth(grid_thick);
                    tbrow.addView(tv);
                }
                else
                {
                    if (j == 0)
                    {
                        tv.setText(dict[i][0]);
                        tv.setWidth((int) Math.round(widths[0] * (stk.getWidth() - 2 * grid_thick)));
                    }
                    else if (j == 2)
                    {
                        tv.setText(dict[i][1]);
                        tv.setWidth((int) Math.round(widths[1] * (stk.getWidth() - 2 * grid_thick)));
                    }
                    else
                    {
                        tv.setText(dict[i][3]);
                        tv.setWidth((int) Math.round(widths[2] * (stk.getWidth() - 2 * grid_thick)));
                    }
                    if (j == 0)
                    {
                        tv.setTextSize(30);
                        Typeface font = ResourcesCompat.getFont(this, R.font.ud_digi_kyokasho_nr);
                        tv.setTypeface(font, Typeface.BOLD);
                    }
                    else
                    {
                        tv.setTextSize(20);
                        tv.setTypeface(null, Typeface.BOLD);
                    }
                    tv.setTextColor(text_color);
                    tv.setGravity(Gravity.START);
                    tbrow.addView(tv);
                }
            }
            stk.addView(tbrow);
            TableRow grid_row = new TableRow(this);
            for (int j = 0; j < 5; j++)
            {
                TextView tv = new TextView(this);
                tv.setBackgroundColor(grid_color);
                tv.setHeight(grid_thick);
                grid_row.addView(tv);
            }
            stk.addView(grid_row);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        findViewById(R.id.table_main).post(new Runnable() {
            @Override
            public void run() {
                init_words_list();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}