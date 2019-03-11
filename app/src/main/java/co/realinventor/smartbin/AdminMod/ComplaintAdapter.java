package co.realinventor.smartbin.AdminMod;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import co.realinventor.smartbin.Common.Complaint;
import co.realinventor.smartbin.R;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.MyViewHolder> {

    private List<Complaint> complaintList;
    public Context context;

    public ComplaintAdapter(List<Complaint> complaintList){
        this.complaintList = complaintList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView msg,name,date;

        public MyViewHolder(View view) {
            super(view);
            msg = (TextView) view.findViewById(R.id.msgTextView);
            name = (TextView) view.findViewById(R.id.nameTextView);
            date = (TextView) view.findViewById(R.id.dateTextView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Complaint complaint = complaintList.get(position);
        holder.msg.setText(complaint.getMsg());
        holder.date.setText(complaint.getDate()+" | "+complaint.getTime());
        holder.name.setText(complaint.getSenderName());
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
