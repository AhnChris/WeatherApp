package com.chrisahn.weatherapp.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.chrisahn.weatherapp.R;
import com.chrisahn.weatherapp.adapters.DayAdapter;
import com.chrisahn.weatherapp.weather.Day;

import java.util.Arrays;

public class DailyLocationForecastActivity extends ListActivity {

    private Day[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_location_forecast);

        Intent intent = getIntent();

        //Getting parcelable array from the intent
        Parcelable[] parcelables = intent.getParcelableArrayExtra(CurrentLocationForecastActivity.DAILY_FORECAST_LOC);
        // Getting array of items on the parcelable extra intent
        // copyOf(array we want to copy from, length, type used for new array)
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        DayAdapter adapter = new DayAdapter(this, mDays);
        setListAdapter(adapter); // populates the custom layout
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String dayOfTheWeek = mDays[position].getDayOfTheWeek();
        String conditions = mDays[position].getSummary();
        String highTemp = mDays[position].getTemperatureMax() + "";
        String message = String.format("On %s the high will be %s and it will be %s",
                dayOfTheWeek, highTemp, conditions);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
