package com.example.darthguru.myapplication;

import android.os.Parcel;
import android.os.Parcelable;


public class ScheduleParcelable implements Parcelable {


    public static final Creator<ScheduleParcelable> CREATOR = new Creator<ScheduleParcelable>() {
        @Override
        public ScheduleParcelable createFromParcel(Parcel in) {
            return new ScheduleParcelable(in);
        }

        @Override
        public ScheduleParcelable[] newArray(int size) {
            return new ScheduleParcelable[size];
        }
    };
    public String name = null;
    public String frequency = null;
    public String[] times = new String[0];

    public ScheduleParcelable() {

    }

    protected ScheduleParcelable(Parcel in) {
        this.name = in.readString();
        this.frequency = in.readString();
        in.readStringArray(this.times);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(frequency);
        dest.writeValue(times);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
