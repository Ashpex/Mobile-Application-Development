package com.ashpex.rssfeed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    // Main GUI - A NEWS application based on National Public Radio RSS material
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;
    SingleItem selectedNewsItem;
    String name;
    TextView txtMain;
    ImageView imageChannel;
    // hard-coding main NEWS categories (TODO: use a resource file)
    String [][] myUrlCaptionMenu;
    String[] myUrlCaption;
    String[] myUrlAddress;
    int image;



    public static String niceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM d, yyyy",
                Locale.US);
        return sdf.format(new Date()); //Monday Apr 7, 2014
    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);

            Intent intent = getIntent();
            String name = intent.getStringExtra("name");
            imageChannel = findViewById(R.id.imageChannel);
            switch(name){
                case "Thanh niên":
                    image = R.drawable.ic_thanhnien;
                    break;
                case "VietnamNet":
                    image = R.drawable.vietnamnet;
                    break;
                case "Tuổi trẻ":
                    image = R.drawable.ic_tuoitre;
                    break;
                case "VNExpress":
                    image = R.drawable.vnexpress;
                    break;

            }
            txtMain = findViewById(R.id.txtMain);
            txtMain.setText("Channel in " + name);
            imageChannel.setImageResource(image);
            getUrl(name);

            for (int i=0; i<myUrlAddress.length; i++) {
            myUrlAddress[i] = myUrlCaptionMenu[i][0]; myUrlCaption[i] = myUrlCaptionMenu[i][1];
        }
        context = getApplicationContext();
        this.setTitle(name + niceDate() );

        // user will tap on a ListView’s row to request category’s headlines

        myMainListView = (ListView)this.findViewById(R.id.myListView);
        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {

                String urlAddress = myUrlAddress[_index], urlCaption = myUrlCaption[_index];

                //create an Intent to talk to activity: ShowHeadlines
                Intent callShowHeadlines = new Intent(MainActivity.this, ShowHeadlines.class);
                //prepare a Bundle and add the input arguments: url & caption
                Bundle myData = new Bundle();
                myData.putString("urlAddress", urlAddress);
                myData.putString("urlCaption", urlCaption);
                myData.putString("name app", name);///////
                callShowHeadlines.putExtras(myData); startActivity(callShowHeadlines);
            }
        });
        // fill up the Main-GUI’s ListView with main news categories
        adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myUrlCaption);
        myMainListView.setAdapter(adapterMainSubjects);
    }//onCreate

    public void getUrl(String name){
            switch(name){
                case "Thanh niên":
                    myUrlCaptionMenu = new String[][]{
                            {"https://thanhnien.vn/rss/thoi-su/chinh-tri-227.rss", "Chính trị"},
                            {"https://thanhnien.vn/rss/thoi-su/phap-luat-5.rss", "Thời sự-pháp luật"},
                            {"https://thanhnien.vn/rss/the-gioi/quan-su-144.rss", "Quân sự"},
                            {"https://thanhnien.vn/rss/the-thao/bong-da-viet-nam-375.rss", "Bóng đá"},
                    };
                    break;
                case "VietnamNet":

                    myUrlCaptionMenu = new String[][]{
                            {"https://vietnamnet.vn/rss/suc-khoe.rss", "Sức khỏe"},
                            {"https://vietnamnet.vn/rss/thoi-su-chinh-tri.rss", "Thời sự chính "},
                            {"https://vietnamnet.vn/rss/giai-tri.rss", "Giải trí"},
                            {"https://dantri.com.vn/the-thao.rss", "Thể thao"},
                            {"https://vietnamnet.vn/rss/kinh-doanh.rss", "Kinh doanh"},
                            {"https://vietnamnet.vn/rss/giao-duc.rss", "Giáo dục"},
                            {"https://vietnamnet.vn/rss/the-gioi.rss", "Thế giới"},

                    };
                    break;

                case "Tuổi trẻ":
                    myUrlCaptionMenu = new String[][]{
                            {"https://tuoitre.vn/rss/the-gioi.rss", "Thế giới"},
                            {"https://tuoitre.vn/rss/kinh-doanh.rss", "Kinh doanh"},
                            {"https://tuoitre.vn/rss/nhip-song-so.rss", "Công nghệ"},
                            {"https://tuoitre.vn/rss/the-thao.rss", "Thể thao"},
                            {"https://tuoitre.vn/rss/giai-tri.rss", "Giải trí"},
                            {"https://tuoitre.vn/rss/giao-duc.rss", "Giáo dục"},
                            {"https://tuoitre.vn/rss/suc-khoe.rss", "Sức khỏe"},
                    };
                    break;
                case "VNExpress":
                    myUrlCaptionMenu = new String[][]{
                            {"https://vnexpress.net/rss/the-gioi.rss", "Thế giới"},
                            {"https://vnexpress.net/rss/kinh-doanh.rss", "Kinh doanh"},
                            {"https://vnexpress.net/rss/suc-khoe.rss", "Sức khỏe"},
                            {"https://vnexpress.net/rss/khoa-hoc.rss", "Khoa học"},
                            {"https://vnexpress.net/rss/the-thao.rss", "Thể thao"},
                            {"https://vnexpress.net/rss/giai-tri.rss", "Giải trí"},
                            {"https://vnexpress.net/rss/giao-duc.rss", "Giáo dục"},
                    };
                    break;
            }
            //define convenient URL and CAPTIONs arrays
            myUrlCaption = new String[myUrlCaptionMenu.length];
            myUrlAddress = new String[myUrlCaptionMenu.length];
    }
}