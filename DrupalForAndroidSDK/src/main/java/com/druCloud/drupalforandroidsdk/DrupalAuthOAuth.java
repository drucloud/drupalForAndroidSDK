package com.druCloud.drupalforandroidsdk;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

/**
 * Created by keithyau on 12/12/13.
 */
public class DrupalAuthOAuth implements DrupalAuth {

    private String baseURI = "";
    private String endpoint = "";
    private String tokenKey = "";
    private String tokenSecret = "";

    public DrupalAuthOAuth(String tokenKey, String tokenSecret)
    {
        this.tokenKey = tokenKey;
        this.tokenSecret = tokenSecret;
    }

    @Override
    public void initAuth(String baseURI, String endpoint)
    {
        this.baseURI = baseURI;
        this.endpoint = endpoint;
    }

    @Override
    public <T extends HttpRequestBase> T initRequest(T request)
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

        // replace header to Drupal specific
        Header[] headers = request.getAllHeaders();
        for (Header header : headers) {
            if (header.getName().equals("Authorization")) {
                String temp = header.getValue().replace("OAuth", "OAuth realm=\"" + this.baseURI + "\"");
                request.setHeader("Authorization", temp);
            }
        }

        return request;
    }
}

/* debug code
            //get request headers
            Header[] headers2 = request.getAllHeaders();
            for (Header header : headers2) {
                System.out.println(header.getName()
                        + ": " + header.getValue());
            }

            System.out.println("fuck!! not 200 " + response.getStatusLine().getReasonPhrase() + " " + response.getStatusLine().getStatusCode());
            System.out.println("fuck!! not 200 Called URL" + request.getURI());
 */