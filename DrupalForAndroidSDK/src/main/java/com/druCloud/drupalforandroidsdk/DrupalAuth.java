package com.druCloud.drupalforandroidsdk;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by keithyau on 12/12/13.
 */
public interface DrupalAuth {

    public void initAuth(String baseURI, String endpoint);

    public <T extends HttpRequestBase> T initRequest (T request);
}