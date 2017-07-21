package br.com.luan.custombottomsheetuberlike.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.luan.custombottomsheetuberlike.R;


/**
 * Created by Luan on 23/06/17.
 */

public class RecycleBottomAdapter extends RecyclerView.Adapter<RecycleBottomAdapter.ViewHolder>  {

    private Context context;
    private LayoutInflater inflater;
    private List<String> itens = new ArrayList<>();

    boolean view;

    public RecycleBottomAdapter(Context context, List<String> itens, boolean view){
        this.context = context;
        this.itens = itens;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = view;
    }
    public RecycleBottomAdapter(Context context, boolean view){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = view;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.adapter_bottom, viewGroup, false);
        RecycleBottomAdapter.ViewHolder mvh = new RecycleBottomAdapter.ViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String object = itens.get(position);
        prepare(viewHolder, object, position);

    }

    private  void  prepare(final ViewHolder viewHolder, final String object, int position){

    }

    @Override
    public int getItemCount() {
        return itens.size();
    }




    public void addData(List<String> mList){
        this.itens.addAll(mList);
        notifyDataSetChanged();
    }


    public void refresh(){
        notifyDataSetChanged();
    }
    public void refresh(List<String> mList){
        this.itens.clear();
        this.itens.addAll(mList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView title;
        RelativeLayout layout;


        public ViewHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {

            return true;
        }
    }
}
