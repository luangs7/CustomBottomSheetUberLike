package br.com.luan.custombottomsheetuberlike.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.luan.custombottomsheetuberlike.R;


public class mBottomAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public mBottomAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public mBottomAdapter(Context context,List<String> mObjects) {
        this.context = context;
        this.objects = mObjects;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_bottom, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(String object, ViewHolder holder) {

    }

    protected class ViewHolder {
        private TextView title;
    private TextView desc;
    private ImageView imageView10;

        public ViewHolder(View view) {

        }
    }


    public void addData(List<String> objects){
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }


    public void refresh(){
        notifyDataSetChanged();
    }
    public void refresh(List<String> objects){
        this.objects.clear();
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }
}
