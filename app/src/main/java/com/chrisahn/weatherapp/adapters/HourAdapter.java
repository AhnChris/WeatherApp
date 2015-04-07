package com.chrisahn.weatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chrisahn.weatherapp.R;
import com.chrisahn.weatherapp.weather.Hour;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Chris on 4/1/2015.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private Hour[] mHours;
    private Context mContext; // added to make use of the Toast when implementing OnClickListener

    // added context in the basic constructor
    public HourAdapter(Context context, Hour[] hours) {
        mContext = context;
        mHours = hours;
    }

    @Override // called whenever a new viewholder is needed
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false);
        HourViewHolder viewHolder = new HourViewHolder(view);

        return viewHolder;
    }

    // bridge between adapter and bind method in viewholder class we made
    @Override
    public void onBindViewHolder(HourViewHolder hourViewHolder, int position) {
        hourViewHolder.bindHour(mHours[position]);

    }

    @Override
    public int getItemCount() { // number of items in hours array
        return mHours.length;
    }

    // added implements View.OnClickListener
    // create the onClickListener here
    public class HourViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        // Regular Way
        /*public TextView mTimeLabel;
        public TextView mSummaryLabel;
        public TextView mTemperatureLabel;
        public ImageView mIconImageView;*/

        // ButterKnife way
        @InjectView(R.id.timeLabel)
        TextView mTimeLabel;
        @InjectView(R.id.summaryLabel) TextView mSummaryLabel;
        @InjectView(R.id.temperatureLabel) TextView mTemperatureLabel;
        @InjectView(R.id.iconImageView)
        ImageView mIconImageView;

        public HourViewHolder(View itemView) { // when viewholder is created this constructor is called
            super(itemView);
            // Regular Way
            /*mTimeLabel = (TextView) itemView.findViewById(R.id.timeLabel);
            mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryLabel);
            mTemperatureLabel = (TextView) itemView.findViewById(R.id.temperatureLabel);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);*/

            // ButterKnife Way
            ButterKnife.inject(this, itemView);
            // need to put the setOnClickListener here because we are setting up the view here
            // in other words the root view in the ViewHolder
            itemView.setOnClickListener(this);
        }

        public void bindHour (Hour hour) {
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() + "");
            mIconImageView.setImageResource(hour.getIconId());
        }

        @Override
        public void onClick(View v) {
            String time = mTimeLabel.getText().toString();
            String temperature = mTemperatureLabel.getText().toString();
            String summary = mSummaryLabel.getText().toString();
            String message = String.format("At %s it will be %s and %s",
                    time, temperature, summary);
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }
    }
}
