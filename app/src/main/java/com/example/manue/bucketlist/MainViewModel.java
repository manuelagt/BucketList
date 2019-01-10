package com.example.manue.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class MainViewModel extends ViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mTasks;

    public MainViewModel(Context context) {
        mRepository = new TaskRepository(context);
        mTasks = mRepository.getAllTasks();
    }


    public LiveData<List<Task>> getTasks() {
        return mTasks;
    }


    public void insert(Task task) {
        mRepository.insert(task);
    }


    public void update(Task task) {
        mRepository.update(task);
    }


    public void delete(Task task) {
        mRepository.delete(task);
    }
}

