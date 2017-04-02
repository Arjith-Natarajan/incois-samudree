
package com.example.vickssarv.homescreenf;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vickssarv.homescreenf.model.ChatBox;
import com.example.vickssarv.homescreenf.util.CustomListViewAdapterForChatBox;

import java.util.ArrayList;

public class ChatBoxActivity extends AppCompatActivity {

    private ListView chatBoxListView;
    private ArrayList<ChatBox> chatBoxArrayList = new ArrayList<>();
    private CustomListViewAdapterForChatBox customListViewAdapterForChatBox;
    private Button btnRequest;
    private TextView reqText;
    private Runnable runnable;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        Tootlbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reqText = (TextView)findViewById(R.id.req_text);

        btnRequest = (Button)findViewById(R.id.btn_req);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

        chatBoxListView = (ListView) findViewById(R.id.chat_box_list_view);
        customListViewAdapterForChatBox = new CustomListViewAdapterForChatBox(this, chatBoxArrayList);
        chatBoxListView.setAdapter(customListViewAdapterForChatBox);

    }

    private void sendRequest(){
//        btnRequest.setEnabled(false);
//        btnRequest.setClickable(false);
        String requestText = reqText.getText().toString();
        ChatBox chatBox = new ChatBox();
        chatBox.setReq(requestText);
        chatBoxArrayList.add(chatBox);
        customListViewAdapterForChatBox.notifyDataSetChanged();

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                updateList();
//            }
//        }, 40000);
    }

    private void updateList(){
        int size = chatBoxArrayList.size();
        ((ChatBox)chatBoxArrayList.get(size)).setRes("Response Text for Request :" + size);
        customListViewAdapterForChatBox.notifyDataSetChanged();
    }

}
