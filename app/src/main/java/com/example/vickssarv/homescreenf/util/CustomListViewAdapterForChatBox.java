package com.example.vickssarv.homescreenf.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vickssarv.homescreenf.R;
import com.example.vickssarv.homescreenf.model.ChatBox;

import java.util.ArrayList;

/**
 * Created by bharathkumar.sainath on 30-06-2016.
 */
public class CustomListViewAdapterForChatBox extends BaseAdapter {
    private static LayoutInflater inflater=null;

    private Context mContext;
    private ArrayList<ChatBox> mData;
    private ChatBox chatBox;

    public CustomListViewAdapterForChatBox(Context context, ArrayList<ChatBox> data){
        mContext = context;
        mData = data;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public ChatBox getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.chat_box_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.req = (TextView)convertView.findViewById(R.id.req_text);
            viewHolder.res = (TextView)convertView.findViewById(R.id.res_text);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if(mData!= null && !mData.isEmpty()){
            chatBox = mData.get(position);
            viewHolder.req.setText(chatBox.getReq());
            if(chatBox.getRes() == null || chatBox.getRes().isEmpty()){
                chatBox.setRes("response  text to be got from BOT");
            }
            viewHolder.res.setText(chatBox.getRes());
        }else{
            viewHolder.req.setText("No Products Available Now");
        }

        return convertView;
    }

    private static class ViewHolder{
        ImageView imageView;
        TextView req;
        TextView res;

    }



}
