package com.druCloud.drupalforandroidsdk;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * Created by jimmyko on 10/13/13.
 */
public interface DrupalServicesResource {

    public void create(ArrayList<NameValuePair> params);

    public void retrieve(int id);

    public void update(int id, ArrayList<NameValuePair> params);

    public void delete(int id);

    public void index();
}
