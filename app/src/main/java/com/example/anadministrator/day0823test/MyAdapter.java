package com.example.anadministrator.day0823test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 张祺钒
 * on2017/8/23.
 */

public class MyAdapter extends BaseAdapter {
    private Bean.ResultBean dataBean;
    private Context context;

    public MyAdapter(Bean.ResultBean dataBean, Context context) {
        this.dataBean = dataBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBean.data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = convertView.inflate(context, R.layout.item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.tv);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean.ResultBean.DataBean dataBean1 = this.dataBean.data.get(position);
        holder.textView.setText(dataBean1.date);
        ImageLoaderUtils.setImageView(dataBean1.thumbnail_pic_s, context, holder.imageView);
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
