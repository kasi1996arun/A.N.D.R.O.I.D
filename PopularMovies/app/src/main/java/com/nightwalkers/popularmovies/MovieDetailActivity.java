package com.nightwalkers.popularmovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDetailActivity extends AppCompatActivity {
    private final String TITLE = "title";
    private final String DATE = "release_date";
    private final String POSTER = "poster_path";
    private final String RATING = "vote_average";
    private final String OVERVIEW = "overview";
    private TextView text,text2,text3;
    private ImageView image;
    private RatingBar rating;
    String textView,textView2,textView3,ratingBar,imageView,imageView2;
    private ShareActionProvider myShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        rating=(RatingBar)findViewById(R.id.ratingBar);
        text=(TextView)findViewById(R.id.textView);
        text2=(TextView)findViewById(R.id.textView4);
        image=(ImageView)findViewById(R.id.imageView);
        text3=(TextView)findViewById(R.id.textView3);
        if (uiUpdate()) {
            Log.d("success", "success in updating UI");
        } else
            showErrorDialog();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.share, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
         myShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        myShareActionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);

        myShareActionProvider.setShareIntent(createShareIntent());
        return true;
    }

    private Intent createShareIntent(){
        String text = textView + " " + textView2 + " " + "-PopularMovies";
        Uri pictureUri = Uri.parse(imageView);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, pictureUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
       return shareIntent;

    }

    private void shareIntent(Intent shareIntent){
        if (myShareActionProvider != null) {
            myShareActionProvider.setShareIntent(shareIntent);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    private void showErrorDialog() {
        new AlertDialog.Builder(MovieDetailActivity.this)
                .setCancelable(true)
                .setMessage("Sorry Something Went Wrong.Try again Later!")
                .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    private boolean uiUpdate() {

        Intent intent = getIntent();
        textView = intent.getStringExtra(TITLE);
        textView3 = intent.getStringExtra(DATE);
        imageView = "http://image.tmdb.org/t/p/w500/" +intent.getStringExtra(POSTER);
        ratingBar = intent.getStringExtra(RATING);
        textView2 = intent.getStringExtra(OVERVIEW);

        Log.d("text", textView);
        Log.d("text3",textView3);Log.d("text2",textView2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(textView3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        text3.setText(df.format(date).toString());
        text.setText(textView);
        rating.setRating(Float.parseFloat(ratingBar)%5);
        text2.setText(textView2);
        Picasso.with(this)
                .load(imageView)
                .into(image);
        return true;
    }




}
