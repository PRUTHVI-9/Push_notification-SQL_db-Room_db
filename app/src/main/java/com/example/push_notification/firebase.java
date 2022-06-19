package com.example.push_notification;

import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class firebase extends FirebaseMessagingService {

    private String TAG=getClass().getSimpleName();

    @Override
    public void onNewToken(@NonNull String token) {
        Log.e(TAG, "onNewToken: "+token );
        SharedPreferences preferences = getSharedPreferences("MyApp",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firebase_token",token);
        editor.commit();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

            // TODO(developer): Handle FCM messages here.
            // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
            Log.d(TAG, "From: " + remoteMessage.getFrom());

            // Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0) {
                Log.e(TAG, "Message data payload: " + remoteMessage.getData());

              /*  if (*//* Check if data needs to be processed by long running job *//* true) {
                    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                    scheduleJob();
                } else {
                    // Handle message within 10 seconds
                    handleNow();
                }*/
            }

            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            }
    }
}
