package com.dicoding.plantbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.plantbot.Fragment.TimePickerFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;



import static com.dicoding.plantbot.DetailActivity.EXTRA_COMMON2;
import static com.dicoding.plantbot.DetailActivity.EXTRA_URL2;

public class Jadwal extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView mTextView;
    public static final String EXTRA_COMMON22 = "commonName";
    public static final String EXTRA_URL22 = "imageUrll";
    public String commonName2 = "Tes";
    public String imageUrl2;
    public static ArrayList<String> namattt = new ArrayList<>();
    public static int codex = 0;
    public static int angka;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        mTextView = findViewById(R.id.jadwall);

        Intent intent = getIntent();
        String commonName = intent.getStringExtra(EXTRA_COMMON2);
        String imageUrl = intent.getStringExtra(EXTRA_URL2);
        commonName2 = commonName;
        imageUrl2 = imageUrl;
        TextView textCommon = findViewById(R.id.textView4);
        ImageView imageViewm = findViewById(R.id.image_species);
        textCommon.setText(commonName);
        System.out.println(imageUrl);

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions())
                .into(imageViewm);

        Button buttonTimePicker = findViewById(R.id.btnTambah);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        Button buttonCancelAlarm = findViewById(R.id.btnHapus);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
        Button buttonUpload = findViewById(R.id.btnUpload);
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Jadwal.this, MyPlantsActivity.class);
                startActivity(intent3);
            }
        });
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);
        startAlarm(c);
    }
    private void updateTimeText(Calendar c) {
        String timeText = "";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mTextView.setText(timeText);
    }
    private void startAlarm2(Calendar c) {
        AlarmManager mgrAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

        for( int i = 0; i < 10; ++i)
        {
            Intent intent = new Intent(this, AlertReceiver.class);
            // Loop counter `i` is used as a `requestCode`
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, intent, 0);
            // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
            if (c.before(Calendar.getInstance())) {
                c.add(Calendar.DATE, 1);
            }
            mgrAlarm.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        namattt.add(commonName2);
        System.out.println(namattt);
        System.out.println(commonName2);
        System.out.println(codex);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, codex, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        codex++;
    }
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, codex, intent, 0);
        alarmManager.cancel(pendingIntent);
        mTextView.setText("......");
    }
}