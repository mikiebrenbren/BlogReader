package com.veechie.mikiebrenbren.blogreader;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainListActivity extends ListActivity {

    protected String[] mBlogPostTitles;

    public static final int NUMBER_OF_POSTS = 20;
    public static final String TAG = MainListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

//        Resources resources = getResources();
//        mBlogPostTitles = resources.getStringArray(R.array.array_names);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mBlogPostTitles);
//        setListAdapter(adapter);
          GetBlogPostsTask getBlogPostsTasks = new GetBlogPostsTask();
          getBlogPostsTasks.execute();  //do not call doinbackground method

//        String message = getString(R.string.no_items);
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_list, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetBlogPostsTask extends AsyncTask<Object, Void, String> {

        int responseCode = -1;
        @Override
        protected String doInBackground(Object[] params) {
            try {
                URL blogFeedUrl = new URL(" http://blog.teamtreehouse.com/api/get_recent_summary/?count=" + NUMBER_OF_POSTS);
                HttpURLConnection connection =(HttpURLConnection) blogFeedUrl.openConnection();
                connection.connect();

                responseCode = connection.getResponseCode();
                Log.i(TAG,"Code: " + responseCode);

            }catch(MalformedURLException e){
                Log.e(TAG, "Exception Caught: ", e);
            }catch (IOException e){
                Log.e(TAG, "Exception Caught: ", e);
            }catch(Exception e){
                Log.e(TAG, "Exception Caught: ", e);
            }
                return "Code: " + responseCode;
        }
    }
}




