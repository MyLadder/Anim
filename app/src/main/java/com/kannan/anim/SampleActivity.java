package com.kannan.anim;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kannan.ornate.MenuBuilder;
import com.kannan.ornate.MenuData;
import com.kannan.ornate.MenuDialogFragment;
import com.kannan.ornate.MenuOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        attachMenu();
    }


    private void attachMenu() {
        List<MenuOption> options = new ArrayList<>();
        for (int i = 0; i < 10; i += 1) {
            options.add(
                    new MenuOption("Label").withDescription("Description")
            );
        }

        MenuData menuData = new MenuData();
        menuData.setMainTitleLeft("ORNATE");
        menuData.setMainTitleRight("MENU");
        menuData.setSubTitle("PICK ONE OF THE OPTIONS");
        menuData.setImageColor(Color.BLACK);
        menuData.setMarginLineColor(Color.YELLOW);
        menuData.setMarginLineWidthDP(5);
        menuData.setmBackgroundColor(Color.GRAY);
        menuData.setMenuOptions(options);

        MenuDialogFragment frag = new MenuBuilder(getApplicationContext())
                .withMenuData(menuData)
                .withMarginDP(0f, 56f, 0f, 0f)
                .build();

        frag.show(getSupportFragmentManager(), "ckajhkdhkd");
    }

    private float dp(int pix) {
        return pix / getApplicationContext().getResources().getDisplayMetrics().density;
    }
}
