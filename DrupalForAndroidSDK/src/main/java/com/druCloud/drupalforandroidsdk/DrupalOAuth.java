package com.druCloud.drupalforandroidsdk;

import android.net.Uri;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

/**
 * Created by jimmyko on 10/13/13.
 */
public class DrupalOAuth {

    private String baseURI = "";
    private String tokenKey = "";
    private String tokenSecret = "";
    private String endpoint = "rest";
    protected String resource = "";

    public DrupalOAuth (String baseURI, String endpoint, String tokenKey, String tokenSecret) {
        this.baseURI = baseURI;
        this.endpoint = endpoint;
        this.tokenKey = tokenKey;
        this.tokenSecret = tokenSecret;
    }

    public void setResource (String resource) {
        this.resource = resource;
    }

    protected String getURI() {
        return this.baseURI + "/" + this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Boolean httpGetRequest (String uri) {
        HttpGet request = new HttpGet(uri);
        return httpSendRequest(request);
    }

    // Only GET request contains query parameters.
    public Boolean httpGetRequest (String uri, ArrayList<NameValuePair> params)
    {
        Uri.Builder uriBuilder = Uri.parse(uri).buildUpon();
        for (NameValuePair param : params) {
            uriBuilder.appendQueryParameter(param.getName(), param.getValue());
        }
        uri = uriBuilder.build().toString();
        HttpGet request = new HttpGet(uri);
        return httpSendRequest(request);
    }

    public Boolean httpPostRequest (String uri, ArrayList<NameValuePair> params)
    {
        HttpPost request = new HttpPost(uri);
        // assign parameters to request
        try {
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpSendRequest(request);
    }

    public Boolean httpDeleteRequest (String uri)
    {
        HttpDelete request = new HttpDelete(uri);
        return httpSendRequest(request);
    }

    public Boolean httpPutRequest (String uri, ArrayList<NameValuePair> params)
    {
        HttpPut request = new HttpPut(uri);
        // assign parameters to request
        try {
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpSendRequest(request);
    }

    private <T extends HttpRequestBase> boolean httpSendRequest (T request)
    {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(this.tokenKey, this.tokenSecret);
        // sign the request (consumer is a Signpost DefaultOAuthConsumer)
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }

        // set header
        request.setHeader("Accept", "application/json");
        request.setHeader("content-type", "application/json");

        // replace header to Drupal specific
        Header[] headers = request.getAllHeaders();
        for (Header header : headers) {
            if (header.getName().equals("Authorization")) {
                String temp = header.getValue().replace("OAuth", "OAuth realm=\"" + this.baseURI + "\"");
                request.setHeader("Authorization", temp);
            }
        }

        // send the request
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(request);
            // if successful, return the response body
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseBody = EntityUtils.toString(resEntity);
                    // debug
                    System.out.println("The response is:" + responseBody);
                }
                //get response headers
                Header[] headers3 = response.getAllHeaders();
                for (Header header : headers3) {
                    // debug
                    System.out.println(header.getName() + ": " + header.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
