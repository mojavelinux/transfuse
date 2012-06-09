package org.androidtransfuse.integrationTest.broadcastReceiver;

import android.content.Context;
import android.widget.Toast;
import org.androidtransfuse.annotations.BroadcastReceiver;
import org.androidtransfuse.annotations.Intent;
import org.androidtransfuse.annotations.IntentType;
import org.androidtransfuse.annotations.OnReceive;

/**
 * @author John Ericksen
 */
@BroadcastReceiver
@Intent(type = IntentType.ACTION, name = Toaster.INTENT)
public class Toaster {

    public static final String INTENT = "Toaster";

    private static boolean onReceive = false;

    @OnReceive
    public void onReceive(Context context){
        Toast hello = Toast.makeText(context, "Toast", 1000);
        hello.show();
        onReceive = true;
    }

    public static boolean isOnReceive() {
        return onReceive;
    }
}
