package com.example.biblicalproverbs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class HomePage extends Activity {
    private ListView listView,catView;
    private ImageButton backButton;
    private Button moreButton;
    private LinearLayout proverbHolder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);
//        backButton = findViewById(R.id.map_item_back_btn);
        listView = findViewById(R.id.proverb);
        ArrayList<Proverb> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new Proverb("text"+i,"test1"+i));
        }
        proverbHolder = findViewById(R.id.proverb_holder);
        proverbHolder.setVisibility(View.GONE );

        proverbHolder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                proverbHolder.setVisibility(View.GONE );

            }
        });
        MyAdapterProverb adapter = new MyAdapterProverb(this, data);
        listView.setAdapter(adapter);
    }
    class Proverb {
        String name;
        String desc;

        public Proverb(String name,String desc ) {
            this.name = name;
            this.desc = desc;
        }
    }
    class MyAdapterProverb extends ArrayAdapter<Proverb> {
    private final Context context;
    private final ArrayList<Proverb> data;
    HashMap<Integer, Boolean> isMenuOpen = new HashMap<>();

//    private final ArrayList<openMenu> isMenuOpen = new ArrayList<>();
        class openMenu {
            int postion;
            boolean status;

            public openMenu(int postion,boolean status ) {
                this.postion = postion;
                this.status = status;
            }
        }
        //    private boolean isMenuOpen = false;
    public MyAdapterProverb(Context context, ArrayList<Proverb> data) {
        super(context, R.layout.proverb_fragment, data);
        this.context = context;
        this.data = data;
        // Initialize isMenuOpen to false initially, assuming all holders are hidden
        for (int i = 0; i < data.size(); i++) {
            isMenuOpen.put(i, false);
        }
    }

        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Inflate the item_card layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.proverb_fragment, parent, false);
            // Get the TextView and ImageView and basic items
            TextView textView = view.findViewById(R.id.proverb_name);
            textView.setText(data.get(position).name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    View viewHome = inflater.inflate(R.layout.home_fragment, parent, false);
                    TextView textView2 = findViewById(R.id.proverb_title);
                    textView2.setText(data.get(position).name);
                    TextView textView3 = findViewById(R.id.proverb_text);
                    textView3.setText(data.get(position).desc);
                    LinearLayout proverbHolder = findViewById(R.id.proverb_holder);
                    proverbHolder.setVisibility(View.VISIBLE );
                }
            });
            return view;
        }
    }

}
