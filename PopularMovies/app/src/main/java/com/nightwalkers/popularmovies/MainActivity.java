package com.nightwalkers.popularmovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    final CharSequence[] items = {" Most Popular ", " Highest Rated "};
    private final String MOST_POPULAR = "popularity.desc";
    private final String HIGHLY_RATED = "vote_count.desc";
    private final String TITLE = "title";
    private final String DATE = "release_date";
    private final String POSTER = "poster_path";
    private final String RATING = "vote_average";
    private final String OVERVIEW = "overview";
    private GridView gridView;
    String[] imgUrl ;
    private AlertDialog choice;
    JSONArray array;
    String choices = HIGHLY_RATED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uiUpdate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showChoices();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean getJSONFile(String desc) throws JSONException {
        NetworkConnection connect = new NetworkConnection();
        String JSON = null;
        try {
            JSON = connect.execute(desc).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("String",JSON);
        JSONObject object = new JSONObject(JSON);
        array  = object.getJSONArray("results");
        Log.d("String",JSON);
        Log.d("Array",array.toString());
        imgUrl = new String[array.length()];
        for(int i=0;i<array.length();i++){
            JSONObject temp = array.getJSONObject(i);
            imgUrl[i]="http://image.tmdb.org/t/p/w185/"+temp.getString(POSTER);
            Log.d("Image",imgUrl[i]);
        }
        return true;
    }

    private void showChoices() {

        choice = new AlertDialog.Builder(MainActivity.this)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                choices=MOST_POPULAR;
                                uiUpdate();
                                break;
                            case 1:
                                choices=HIGHLY_RATED;
                                uiUpdate();
                                break;
                        }
                        choice.dismiss();
                    }
                }).setTitle("Choose")
                .show();
    }

   void uiUpdate(){
       try {
           if(getJSONFile(choices)){
               gridView = (GridView) findViewById(R.id.movie_grid);
               gridView.setAdapter(new ImageAdapter(this, imgUrl));
               gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       try {
                           JSONObject temp = array.getJSONObject(position);
                           String title = temp.getString(TITLE);
                           String poster = temp.getString(POSTER);
                           String rating = temp.getString(RATING);
                           String date = temp.getString(DATE);
                           String overview = temp.getString(OVERVIEW);
                           Intent intent = new Intent(getApplicationContext(),MovieDetailActivity.class);
                           intent.putExtra(TITLE, title);
                           intent.putExtra(POSTER, poster);
                           intent.putExtra(DATE, date);
                           intent.putExtra(RATING, rating);
                           intent.putExtra(OVERVIEW, overview);



                           startActivity(intent);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               });
           }
       } catch (JSONException e) {
           e.printStackTrace();
       }

   }
}
