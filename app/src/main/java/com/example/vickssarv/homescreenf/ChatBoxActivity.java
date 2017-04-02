
package com.example.vickssarv.homescreenf;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vickssarv.homescreenf.model.ChatBox;
import com.example.vickssarv.homescreenf.util.CustomListViewAdapterForChatBox;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatBoxActivity extends AppCompatActivity {

    private static final String URL = "http://139.59.72.134:8000/Samudree/bot";

    private ListView chatBoxListView;
    private ArrayList<ChatBox> chatBoxArrayList = new ArrayList<>();
    private CustomListViewAdapterForChatBox customListViewAdapterForChatBox;
    private Button btnRequest;
    private TextView reqText;
    private Runnable runnable;
    private Handler mHandler;
    private RequestQueue queue;
    private String replyFromBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);

        reqText = (TextView) findViewById(R.id.req_text);

        btnRequest = (Button) findViewById(R.id.btn_req);
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

    private void sendRequest() {
//        btnRequest.setEnabled(false);
//        btnRequest.setClickable(false);
        String requestText = reqText.getText().toString();
        ChatBox chatBox = new ChatBox();
        chatBox.setReq(requestText);
        chatBoxArrayList.add(chatBox);
        customListViewAdapterForChatBox.notifyDataSetChanged();

        callChatBotAPi(requestText);

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                updateList();
//            }
//        }, 40000);
    }

    private void updateList(String answerFromBot) {
        reqText.setText("");
        int size = chatBoxArrayList.size();
        ((ChatBox) chatBoxArrayList.get(size -1)).setRes(answerFromBot);
        customListViewAdapterForChatBox.notifyDataSetChanged();
    }

    private void callChatBotAPi(String request) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("request_message", request);


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // VolleyLog.v("Response:%n %s", response.toString(4));

                    Gson gson = new Gson();
                    Type type = new TypeToken<Map<String, String>>() {
                    }.getType();
                    Map<String, String> resObj = gson.fromJson(response.toString(), type);
                    replyFromBot = (String)response.get("reply");
                    updateList(replyFromBot);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        queue.add(req);
    }

}
