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
                    DrupalServicesNode oauth = new DrupalServicesNode("http://dev-oapi.mobingi.com", "test", "suhWgG9GEMe9tAo2EneBBDVXAnpkXd9j", "ht9mQMR2n2cKrtjknH9gWyMrzq4YPMao");
                    System.out.println("fuck!! oauth" + oauth.toString());
                    String response = oauth.retrieve(1346);
                    // It will print out a response of node get
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
