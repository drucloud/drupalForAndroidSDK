package com.druCloud.drupalforandroidsdk;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by jimmyko on 10/13/13.
 */
public interface DrupalServicesResource {

    public String create(BasicNameValuePair[] params);

    public String retrieve(int id);

    public String update(int id, BasicNameValuePair[] params);

    public String delete(int id);

    public String index();
}
