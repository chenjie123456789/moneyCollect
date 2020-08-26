package com.shizheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shizheng.bean.Smsbean;
import com.shizheng.moneycollect.R;

import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.ViewSmsHolder> {

    private List<Smsbean> smsbeans;
    private Context context;

    public SmsAdapter(List<Smsbean> list,Context context){
        this.context = context;
        this.smsbeans = list;
    }



    @NonNull
    @Override
    public SmsAdapter.ViewSmsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sms_layout,parent,false);

        return new ViewSmsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmsAdapter.ViewSmsHolder holder, int position) {

        holder.timeTextView.setText(smsbeans.get(position).getTime());
        holder.titleTextView.setText(smsbeans.get(position).getAddress());
        holder.contentTextView.setText(smsbeans.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return smsbeans.size();
    }

    public static class ViewSmsHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView contentTextView;
        TextView timeTextView;

        public ViewSmsHolder(@NonNull View itemView) {
            super(itemView);
            contentTextView = itemView.findViewById(R.id.content_text);
            titleTextView = itemView.findViewById(R.id.title_text);
            timeTextView= itemView.findViewById(R.id.text_time);
        }
    }
}
