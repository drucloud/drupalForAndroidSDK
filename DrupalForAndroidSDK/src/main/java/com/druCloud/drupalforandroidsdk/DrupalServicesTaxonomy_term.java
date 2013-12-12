package com.druCloud.drupalforandroidsdk;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by keithyau on 11/7/13.
 */
public class DrupalServicesTaxonomy_term extends DrupalServicesBase implements DrupalServicesResource {

    public DrupalServicesTaxonomy_term(String baseURI, String endpoint) {
        super(baseURI, endpoint);
        this.setResource("taxonomy_term");
    }

    @Override
    public String create(BasicNameValuePair[] params) {
        return this.httpPostRequest(this.getURI(), params);
    }

    @Override
    public String retrieve(int id) {
        return this.httpGetRequest(this.getURI() + "/" + id);
    }

    @Override
    public String update(int id, BasicNameValuePair[] params) {
        return this.httpPutRequest(this.getURI() + "/" + id, params);
    }

    @Override
    public String delete(int id) {
        return this.httpDeleteRequest(this.getURI() + "/" + id);
    }

    @Override
    public String index() {
        return this.httpGetRequest(this.getURI());
    }

}
