package com.example.manue.bucketlist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "task")
public class Task implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    public String mTaskTitle;

    @ColumnInfo(name = "description")
    public String mTaskDescription;

    @ColumnInfo(name = "checked")
    private boolean mChecked;

    public Task(boolean mChecked, String mTaskTitle, String mTaskDescription) {
        this.mChecked = mChecked;
        this.mTaskTitle = mTaskTitle;
        this.mTaskDescription = mTaskDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getmTaskTitle() {
        return mTaskTitle;
    }

    public void setmTaskTitle(String mTaskTitle) {
        this.mTaskTitle = mTaskTitle;
    }

    public String getmTaskDescription() {
        return mTaskDescription;
    }

    public void setmTaskDescription(String mTaskDescription) {
        this.mTaskDescription = mTaskDescription;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.mTaskTitle);
        parcel.writeString(this.mTaskDescription);
    }

    protected Task(Parcel in){
        this.id = in.readLong();
        this.mTaskTitle = in.readString();
        this.mTaskDescription = in.readString();


    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };


}
