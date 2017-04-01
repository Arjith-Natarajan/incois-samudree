package com.example.vickssarv.homescreenf.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vickssarv.homescreenf.NavigationDrawerActivity;
import com.example.vickssarv.homescreenf.PuzzleActivity;
import com.example.vickssarv.homescreenf.R;
import com.example.vickssarv.homescreenf.SOSActivity;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    View row1;
    View row2;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home, container, false);
        row1 = (View)view.findViewById(R.id.first_row);
        row2 = (View)view.findViewById(R.id.second_row);

        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btn1 = (Button) row1.findViewById(R.id.home_btn_1);
        btn1.setText("PFZ");

        ImageView btn1Image = (ImageView)row1.findViewById(R.id.home_btn_img1);
        btn1Image.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_explore_black_24dp));

        Button btn2 = (Button) row1.findViewById(R.id.home_btn_2);
        btn2.setText("SOS");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sosIntent = new Intent(getActivity(), SOSActivity.class);
                startActivity(sosIntent);
            }
        });

        ImageView btn2Image = (ImageView)row1.findViewById(R.id.home_btn_img2);
        btn2Image.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_warning_black_24dp));

        Button btn3 = (Button) row2.findViewById(R.id.home_btn_1);
        btn3.setText("GAME");

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),PuzzleActivity.class);
                startActivity(intent);    }
        });

        ImageView btn3Image = (ImageView)row2.findViewById(R.id.home_btn_img1);
        btn3Image.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_extension_black_24dp));

        Button btn4 = (Button) row2.findViewById(R.id.home_btn_2);
        btn4.setText("FEEDBACK");

        ImageView btn4Image = (ImageView)row2.findViewById(R.id.home_btn_img2);
        btn4Image.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_border_color_black_24dp));
    }
}
