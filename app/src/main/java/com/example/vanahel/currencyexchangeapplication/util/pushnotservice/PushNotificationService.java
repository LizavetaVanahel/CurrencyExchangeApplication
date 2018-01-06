package com.example.vanahel.currencyexchangeapplication.util.pushnotservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.vanahel.currencyexchangeapplication.HomeActivity;
import com.example.vanahel.currencyexchangeapplication.R;

/**
 * Created by Liza Kaliada on 03.12.17.
 */

public class PushNotificationService  {

    private static final String BROADCAST_RECEIVER_INTENT = "brodcastreceiver";
    public static final String INTENT_FILTER = "INTENT_FILTER";

    public void sendNotification( Context context, String currency, Double priceFallRate ) {

        String title = currency + " has fallen in price";
        String body = currency + " was down to " + priceFallRate;

        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(BROADCAST_RECEIVER_INTENT, "brodcastreceiver");
        intent.putExtra("title", title);
        intent.putExtra("description", body);
        intent.putExtra("currency", currency);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int num = (int) System.currentTimeMillis();
        PendingIntent pendingIntent =
                PendingIntent.getActivity( context, num, intent, PendingIntent.FLAG_ONE_SHOT );

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(num, notificationBuilder.build());
    }

}
