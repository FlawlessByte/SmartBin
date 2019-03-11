package co.realinventor.smartbin.UserMod;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import co.realinventor.smartbin.Common.CollectionMessage;
import co.realinventor.smartbin.R;

public class CollectionMessageAdapter extends RecyclerView.Adapter<CollectionMessageAdapter.MyViewHolder> {

private List<CollectionMessage> collectionMessageList;

public CollectionMessageAdapter(List<CollectionMessage> collectionMessageList){
        this.collectionMessageList = collectionMessageList;
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView msg,date;

    public MyViewHolder(View view) {
        super(view);
        msg = (TextView) view.findViewById(R.id.msgTextViews);
        date = (TextView) view.findViewById(R.id.dateTextViews);
    }
}

    @Override
    public CollectionMessageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msg_list_item, parent, false);

        return new CollectionMessageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CollectionMessageAdapter.MyViewHolder holder, int position) {
        final CollectionMessage complaint = collectionMessageList.get(position);
        holder.msg.setText(complaint.getMsg());
        holder.date.setText(complaint.getDate()+" | "+complaint.getTime());
    }

    @Override
    public int getItemCount() {
        return collectionMessageList.size();
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