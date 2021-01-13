package com.dicoding.plantbot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.dicoding.plantbot.Adapter.NotificationHelper;

import java.util.ArrayList;

import static com.dicoding.plantbot.Jadwal.angka;
import static com.dicoding.plantbot.Jadwal.codex;
import static com.dicoding.plantbot.Jadwal.namattt;

public class AlertReceiver extends BroadcastReceiver {
    public ArrayList<String> tan = new ArrayList<>();



    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);

        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(angka);
        notificationHelper.getManager().notify(1, nb.build());
        angka++;
    }

}