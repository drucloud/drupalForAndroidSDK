package com.druCloud.drupalforandroidsdkdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.druCloud.drupalforandroidsdk.*;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;

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
                    DrupalServicesNode DruNode = new DrupalServicesNode("http://dev-oapi.mobingi.com", "test", "suhWgG9GEMe9tAo2EneBBDVXAnpkXd9j", "ht9mQMR2n2cKrtjknH9gWyMrzq4YPMao");
                    String responseNodeGet = DruNode.retrieve(1346);
                    System.out.println("Nodeget!! response" + responseNodeGet);

                    /*
                    data[0] = new BasicNameValuePair("type", "article");
                    data[1] = new BasicNameValuePair("title", "Test android create body 1");

                    String bodyValue = "testing123";
                    data[2] = new BasicNameValuePair("body[und][0][value]", bodyValue);
                    data[3] = new BasicNameValuePair("body[und][0][format]", "filtered_html");
                    */

                    // or according to https://gist.github.com/kylebrowning/affc9864487bb1b9c918

                    //data[0] = new BasicNameValuePair("node[type]", "article");
                    //data[1] = new BasicNameValuePair("node[title]", "Test android create 2");

                    // Attach file
                    // File upload feature only works on services 3.x-dev 2013-Oct-11
                    DrupalServicesFile DruFile = new DrupalServicesFile("http://dev-oapi.mobingi.com", "test", "<your api key>", "<your api secret>");

                    BasicNameValuePair[] data = new BasicNameValuePair[2];
                    //Bitmap bm = BitmapFactory.decodeFile("../ic_launcher-web.png");
                    Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
                    byte[] bypeImage = baos.toByteArray();
                    String base64image = Base64.encodeToString(bypeImage, Base64.DEFAULT);
                    String filesize = String.valueOf(base64image.length());

                    data[0] = new BasicNameValuePair("filename", "yourFileNameOnDrupal.png");
                    data[1] = new BasicNameValuePair("file", base64image );
                    //data[1] = new BasicNameValuePair("uid", "4");
                    //data[3] = new BasicNameValuePair("filesize", filesize);
                    //data[4] = new BasicNameValuePair("filemime", "image/png"); //or others
                    //data[1] = new BasicNameValuePair("target_uri", "pictures/yourFileNameOnDrupal.png");

                    String responseFileCreate = DruFile.create(data);

                    // It will print out the response
                    System.out.println("FileCreate!! response " + filesize +responseFileCreate);
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
