package com.example.lamng.material_animations;

import android.app.Application;

/**
 * Created by Lam Nguyen on 6/21/17.
 * Siclo Mobile company.
 */

public class AnimApp extends Application {

    private static AnimApp instance;

    public static AnimApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
