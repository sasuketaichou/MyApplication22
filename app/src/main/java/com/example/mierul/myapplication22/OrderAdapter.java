package com.example.mierul.myapplication22;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Hexa-Amierul.Japri on 23/9/2017.
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderNode> list;
    private Context context;

    private boolean isLoadingAdded = false;

    private final int ITEM = 0;
    private final int LOADING = 1;

    public OrderAdapter(Context context,List<OrderNode> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case ITEM:

                View orderView = inflater.inflate(R.layout.view_list_order,parent,false);
                viewHolder = new ViewHolder(orderView);
                break;

            case LOADING:

                View loadingView = inflater.inflate(R.layout.view_loading_bar,parent,false);
                viewHolder = new Loading(loadingView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                OrderAdapter.ViewHolder orderVH  = (OrderAdapter.ViewHolder) holder;

                OrderNode model = list.get(position);

                ImageView imageView = orderVH.imageView;

                Glide.with(context)
                        .load(model.url)
                        .into(imageView);

                TextView address = orderVH.address;
                address.setText(model.address);

                TextView order = orderVH.order;
                order.setText(model.numOrder);

                TextView title = orderVH.title;
                title.setText(model.productName);

                TextView total = orderVH.total;
                total.setText(model.total);

                 break;
            case LOADING:
                 break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return (position == list.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(List<OrderNode> latest){

        list.addAll(latest);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
    }

    public void addLoading(){

        isLoadingAdded = true;

        //add loading to list
        list.add(new OrderNode());

    }

    public void removeLoading() {

        isLoadingAdded = false;

        int last_position = list.size() - 1;
         OrderNode itemLoading = list.get(last_position);

        if (itemLoading != null) {
            list.remove(last_position);
            notifyItemRemoved(last_position);
        }
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView order;
        TextView address;
        TextView title;
        TextView total;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.checkOut_tv_title);
            imageView = (ImageView)itemView.findViewById(R.id.checkOut_iv);
            order = (TextView)itemView.findViewById(R.id.checkOut_tv_numOrder);
            address = (TextView)itemView.findViewById(R.id.checkOut_tv_user_address);
            total = (TextView)itemView.findViewById(R.id.checkOut_tv_total);
        }
    }

    protected class Loading extends RecyclerView.ViewHolder {

        public Loading(View itemView) {
            super(itemView);
        }
    }

}