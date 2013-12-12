package com.druCloud.drupalforandroidsdk;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by keithyau on 12/12/13.
 */
public class DrupalAuthSession implements DrupalAuth {

    private String baseURI = "";
    private String endpoint = "";
    private String username = "";
    private String password = "";
    private String mSession = null;
    private String mToken = null;

    public DrupalAuthSession(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void initAuth(String baseURI, String endpoint)
    {
        this.baseURI = baseURI;
        this.endpoint = endpoint;
        userLogin();
    }

    private String getSession()
    {
        return mSession;
    }

    private void setSession(String _session)
    {
        mSession = _session;
    }

    private void userLogin()
    {
        HttpClient httpClient   =   new DefaultHttpClient();
        HttpPost httpPost       =   new HttpPost(this.baseURI + "/" + this.endpoint + "/user/login");

        try{
            List<NameValuePair> nameValuePairs  =   new ArrayList<NameValuePair>();
            nameValuePairs.add( new BasicNameValuePair("username", this.username) );
            nameValuePairs.add( new BasicNameValuePair("password", this.password) );
            httpPost.setEntity( new UrlEncodedFormEntity(nameValuePairs));

            //Execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            setSession( response.toString() );

            Log.i("printing session: ", getSession().toString());
            Log.v("CODE", httpPost.getRequestLine().toString() + " - " + getSession());

        }catch(Exception e){
            Log.e("HTTP ERROR", e.toString());
        }
    }

    private String getToken()
    {
        return mToken;
    }

    public void setToken()
    {
        HttpClient httpClient  = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(this.baseURI + "/services/session/token");

        try {
            HttpResponse token = httpClient.execute(httpGet);
            mToken = token.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends HttpRequestBase> T initRequest(T request)
    {
        //Refresh X-XSRF-Token before request
        setToken();

        //Set token and session to header
        if (getToken() != null) {
            request.setHeader("X-CSRF-Token", getToken());
        }
        if (getSession() != null) {
            request.setHeader("Cookie", getSession());
        }

        return request;
    }
}
