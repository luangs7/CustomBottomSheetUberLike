package br.com.luan.custombottomsheetuberlike.sample;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.luan.custombottomsheetuberlike.R;

public class ItemPagerAdapter extends PagerAdapter {

    protected View rootView;
    protected Toolbar toolbar;
    protected CollapsingToolbarLayout toolbarLayout;
    protected AppBarLayout appBar;
    Context mContext;
    LayoutInflater mLayoutInflater;
    final int[] mItems;

    public ItemPagerAdapter(Context context, int[] items) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        toolbar = (Toolbar) itemView.findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click!", Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private void initView(View rootView) {

    }
}
