package com.example.manue.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    public LiveData<List<Task>> getAllTasks();

    @Insert
    public void insertTask(Task task);

    @Delete
    public void deleteTask(Task task);

    @Update
    public void updateTask(Task task);
}
