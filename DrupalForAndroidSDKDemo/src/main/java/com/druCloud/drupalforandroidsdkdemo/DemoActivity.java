package com.druCloud.drupalforandroidsdkdemo;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.druCloud.drupalforandroidsdk.*;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class DemoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        new Thread(){
            public void run(){
                try {
                    // Here give you a test link
                    DrupalServicesNode oauth = new DrupalServicesNode("http://dev-oapi.mobingi.com", "test", "NPTyBa4hyhbamsoJF6LEeKVZaGT4GDwc", "5PKaL3a26PJVjvDUye9TbiV5ra7ZiwiQ");
                    //String response = oauth.retrieve(1346);

                    BasicNameValuePair[] data = new BasicNameValuePair[4];

                    data[0] = new BasicNameValuePair("type", "article");
                    data[1] = new BasicNameValuePair("title", "Test android create body 1");

                    String bodyValue = "testing123";
                    data[2] = new BasicNameValuePair("body[und][0][value]", bodyValue);
                    data[3] = new BasicNameValuePair("body[und][0][format]", "filtered_html");

                    // or according to https://gist.github.com/kylebrowning/affc9864487bb1b9c918

                    //data[0] = new BasicNameValuePair("node[type]", "article");
                    //data[1] = new BasicNameValuePair("node[title]", "Test android create 2");

                    String response = oauth.create(data);

                    // It will print out the response
                    System.out.println("fuck!! response" + response);
                } catch (Exception e) {
                    Log.e("HTTP GET:", e.toString());
                }
            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_demo, container, false);
            return rootView;
        }
    }

}
