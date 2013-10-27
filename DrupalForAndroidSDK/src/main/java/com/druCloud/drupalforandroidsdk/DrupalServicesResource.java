package com.druCloud.drupalforandroidsdk;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * Created by jimmyko on 10/13/13.
 */
public interface DrupalServicesResource {

    public String create(ArrayList<NameValuePair> params);

    public String retrieve(int id);

    public String update(int id, ArrayList<NameValuePair> params);

    public String delete(int id);

    public String index();
}
