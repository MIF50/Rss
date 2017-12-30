package adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import inter.ItemClickListener;
import mif50.com.rssreader.R;
import model.RSSObject;

/**
 * Adapter that add data to Item_row and add item in RecyclerView
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    Context context;
    RSSObject rssObject;
    LayoutInflater inflater;
    //Context of Activity that Show data and obj of Model class that get data from it
    public FeedAdapter(Context context, RSSObject rssObject) {
        this.context = context;
        this.rssObject = rssObject;
        // get LayoutInflater Obj
        inflater=LayoutInflater.from(context);
    }
    /*inflate View of item_row and return obj of View holder with view that inflate it */
    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=inflater.inflate(R.layout.item_row,parent,false);
        return new FeedViewHolder(item);
    }
    /* get Data from obj of Model and set this data to View holder and action item of View Holder*/
    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.title_item.setText(rssObject.getItems().get(position).getTitle());
        holder.data_item.setText(rssObject.getItems().get(position).getPubDate());
        holder.content_item.setText(rssObject.getItems().get(position).getContent());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position, boolean isClickLong) {
                // when click in item get link and open browser
                if (!isClickLong){ // isClickLong == false
                    // open browser to show page
                    Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    context.startActivity(browserIntent);
                }
            }
        });

    }
    // get Count of object Model
    @Override
    public int getItemCount() {
        return rssObject.getItems().size();
    }
}

/*
* View Holder of Feed that find view of item_row
* set Action of item of this row Item
* */
class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
    // find view in item_row.xml
    public TextView title_item,data_item,content_item;
    // itemClickListener interface used to implement action when put data in view holder
    private ItemClickListener itemClickListener;
    public FeedViewHolder(View itemView) {
        super(itemView);
        // find View in item_row.xml
        title_item=itemView.findViewById(R.id.title_item);
        data_item=itemView.findViewById(R.id.data_item);
        content_item=itemView.findViewById(R.id.content_item);
        //action itemView
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        // implement action on Onclick
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {
        // implement action on Onclick
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
