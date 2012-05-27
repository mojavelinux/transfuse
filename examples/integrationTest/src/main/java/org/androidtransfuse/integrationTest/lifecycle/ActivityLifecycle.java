package org.androidtransfuse.integrationTest.lifecycle;

import android.os.Bundle;
import org.androidtransfuse.annotations.*;
import org.androidtransfuse.integrationTest.R;

/**
 * @author John Ericksen
 */
@Activity(name = "ActivityLifecycleActivity")
@Layout(R.layout.main)
public class ActivityLifecycle {

    private Bundle onCreateBundle;
    private boolean onDestroy;
    private boolean onStop;
    private boolean onPause;
    private boolean onResume;
    private boolean onStart;
    private boolean onRestart;

    @OnCreate
    public void onCreate(Bundle bundle) {
        onCreateBundle = bundle;
    }

    @OnDestroy
    protected void onDestroy() {
        onDestroy = true;
    }

    @OnStop
    private void onStop() {
        onStop = true;
    }

    @OnPause
    void onPause() {
        onPause = true;
    }

    @OnResume
    public void onResume() {
        onResume = true;
    }

    @OnStart
    public void onStart() {
        onStart = true;
    }

    @OnRestart
    public void onRestart() {
        onRestart = true;
    }

    public Bundle getOnCreateBundle() {
        return onCreateBundle;
    }

    public boolean isOnDestroy() {
        return onDestroy;
    }

    public boolean isOnStop() {
        return onStop;
    }

    public boolean isOnPause() {
        return onPause;
    }

    public boolean isOnResume() {
        return onResume;
    }

    public boolean isOnStart() {
        return onStart;
    }

    public boolean isOnRestart() {
        return onRestart;
    }
}
