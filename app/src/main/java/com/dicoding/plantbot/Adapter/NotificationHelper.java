package com.dicoding.plantbot.Adapter;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.dicoding.plantbot.R;

import java.util.ArrayList;

import static com.dicoding.plantbot.Jadwal.namattt;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    public ArrayList<String> tanaman = new ArrayList<>();
    private NotificationManager mManager;
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public NotificationCompat.Builder getChannelNotification(int codea) {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Waktunya Siram!")
                .setContentText("Waktunya penyiraman untuk "+ namattt.get(codea))
                .setSmallIcon(R.drawable.logo);
    }
}