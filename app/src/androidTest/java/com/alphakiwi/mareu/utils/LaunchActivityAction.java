package com.alphakiwi.mareu.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;


import com.alphakiwi.mareu.R;

import org.hamcrest.Matcher;

public class LaunchActivityAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() { return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_list_name);
        button.performClick();
    }
}
