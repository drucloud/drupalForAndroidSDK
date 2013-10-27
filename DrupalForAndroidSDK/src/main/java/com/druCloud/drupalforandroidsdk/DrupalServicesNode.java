package com.druCloud.drupalforandroidsdk;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * Created by jimmyko on 10/13/13.
 */
public class DrupalServicesNode extends DrupalOAuth implements DrupalServicesResource {


    protected DrupalServicesNode(String baseURI, String endpoint, String tokenKey, String tokenSecret) {
        super(baseURI, endpoint, tokenKey, tokenSecret);
        this.setResource("node");
    }

    @Override
    public void create(ArrayList<NameValuePair> params) {
        this.httpPostRequest(this.getURI(), params);
    }

    @Override
    public void retrieve(int id) {
        this.httpGetRequest(this.getURI() + "/" + id);
    }

    @Override
    public void update(int id, ArrayList<NameValuePair> params) {
        this.httpPutRequest(this.getURI() + "/" + id, params);
    }

    @Override
    public void delete(int id) {
        this.httpDeleteRequest(this.getURI() + "/" + id);
    }

    @Override
    public void index() {
        this.httpGetRequest(this.getURI());
    }
}
