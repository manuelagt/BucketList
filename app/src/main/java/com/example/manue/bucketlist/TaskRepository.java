package com.example.manue.bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskRepository {

    private AppDatabase mAppDatabase;
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mTasks;
    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public TaskRepository (Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mTaskDao = mAppDatabase.taskDao();
        mTasks = mTaskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mTasks;
    }


    public void insert(final Task task) {
        mExecutor.execute(new Runnable() {

            @Override
            public void run() {
                mTaskDao.insertTask(task);

            }

        });
    }


    public void update(final Task task) {
        mExecutor.execute(new Runnable() {

            @Override
            public void run() {
                mTaskDao.updateTask(task);
            }

        });
    }


    public void delete(final Task task) {

        mExecutor.execute(new Runnable() {

            @Override
            public void run() {
                mTaskDao.deleteTask(task);
            }
        });
    }
}
