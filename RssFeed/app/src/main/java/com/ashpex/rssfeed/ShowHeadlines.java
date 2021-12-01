package com.ashpex.rssfeed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ShowHeadlines extends Activity {
    // Main category has already been selected by user: ‘World News’, Business’, ...
    // [“urlCaption”, “urlAddress”] comes in a bundle sent by main thread
    // here we access RSS-feed and show corresponding headlines
    TextView txtMain;
    ArrayList<SingleItem> newsList = new ArrayList<SingleItem>();
    ListView myListView;
    String urlAddress = "", urlCaption = "";
    SingleItem selectedNewsItem;
    int image;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        myListView = (ListView)this.findViewById(R.id.myListView);
        // find out which intent is calling us & grab data bundle holding selected url & caption sent to us
        Intent callingIntent = getIntent();
        Bundle myBundle = callingIntent.getExtras();
        urlAddress = myBundle.getString("urlAddress");
        urlCaption = myBundle.getString("urlCaption");

        String nameApp = myBundle.getString("name app");
        txtMain = findViewById(R.id.txtMain);
        txtMain.setText("Items in channel " + nameApp + " " + urlCaption);

        switch(nameApp){
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


        // update app’s top ‘TitleBar’ (eg. ‘NPR - Business Wed April 09, 2014’)
        this.setTitle(nameApp + urlCaption + " \t" + MainActivity.niceDate());
        myListView = (ListView)this.findViewById(R.id.myListView);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int index, long id) {
                selectedNewsItem = newsList.get(index);
                showNiceDialogBox(selectedNewsItem, getApplicationContext());
            }});
        // get stories for the selected news option
        DownloadRssFeed downloader = new DownloadRssFeed(ShowHeadlines.this);
        downloader.execute(urlAddress, urlCaption);
    }//onCreate

    public void showNiceDialogBox(SingleItem selectedStoryItem, Context context){
        // make a nice-looking dialog box (story summary, btnClose, btnMore)
        // CAUTION: (check)on occasions title and description are the same!
        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.toLowerCase().equals(description.toLowerCase())){ description = ""; }
        try {
        //CAUTION: sometimes TITLE and DESCRIPTION include HTML markers
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            myBuilder.setIcon(image) .setTitle(Html.fromHtml(urlCaption) )
                    .setMessage(title + "\n\n" + Html.fromHtml(description) + "\n") .setPositiveButton("Close", null) .setNegativeButton("More", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichOne) {
                    Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
                    startActivity(browser);
                }}) //setNegativeButton
                    .show();
        }
        catch (Exception e) { Log.e("Error DialogBox", e.getMessage() ); }
    }//showNiceDialogBox


}//ShowHeadlines 3