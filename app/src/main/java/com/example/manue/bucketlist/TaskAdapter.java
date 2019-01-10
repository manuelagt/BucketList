package com.example.manue.bucketlist;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> mTasks;
    final private TaskClickListener mTaskClickListener;

    public interface TaskClickListener{
        void taskOnClick(int i, boolean checked);
    }

    public TaskAdapter(List<Task> mTasks, TaskClickListener mTaskClickListener) {
        this.mTasks = mTasks;
        this.mTaskClickListener = mTaskClickListener;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_item, parent, false);

        // Return a new holder instance
        TaskAdapter.ViewHolder viewHolder = new TaskAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.mTaskTitle.setText(task.getmTaskTitle());
        holder.mTaskDescription.setText(task.getmTaskDescription());
        holder.mTaskCheckBox.setChecked(task.isChecked());
        crossOutTextIfChecked(holder.mTaskCheckBox, holder.mTaskTitle, holder.mTaskDescription);

    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void crossOutTextIfChecked(CheckBox checkBox, TextView title, TextView description) {
        if (checkBox.isChecked()) {
            title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            description.setPaintFlags(description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            description.setPaintFlags(description.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTaskTitle;
        public TextView mTaskDescription;
        public CheckBox mTaskCheckBox;

        public ViewHolder(View itemView) {

            super(itemView);
            mTaskCheckBox = itemView.findViewById(R.id.checkbox);
            mTaskTitle= itemView.findViewById(R.id.title);
            mTaskDescription = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int i = getAdapterPosition();
            mTaskClickListener.taskOnClick(i, mTaskCheckBox.isChecked());
            crossOutTextIfChecked(mTaskCheckBox, mTaskTitle, mTaskDescription);
        }

    }

    public void swapList (List<Task> newList) {


        mTasks = newList;

        if (newList != null) {

            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();

        }

    }


}
