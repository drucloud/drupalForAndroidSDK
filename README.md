drupalForAndroidSDK
===================

Android SDK wokring with Drupal Services

Installation
==================
You need below for this project:

Android studio 0.3.+ Gradle 1.8

The project implement with gradle, so please gradle sync before compile the project. Since Android studio is actively changed, the build.gradle may got something not functioning.

The project is not yet ready for production !!

Authentication
==================

The project right now supports 2 legged oauth while real 3 legged case please reference:
https://drupal.org/project/oauth3legged_client

We currently dont have plan to support single server 3 legged oauth as it is not that meaningful. 
Please let us know if you have this requirement.

The 2 legged oauth is very simple, please reference our drupalForAndroidSDKDemo in the project.

Example:

        new Thread(){
            public void run(){
                try {
                    // Here give you a test link
                    DrupalServicesNode oauth = new DrupalServicesNode("your web server", "endpoint", "appkey", "appsecret");
		    //The node number you want to retrieve
                    String response = oauth.retrieve(1346);
                    // It will print out a response of node get
                    System.out.println("response json" + response);
                } catch (Exception e) {
                    Log.e("HTTP GET:", e.toString());
                }
            }
        }.start();

To-do
===============
1. Add more web services support based on default Drupal services 3.x
2. Support services views
3. Support some AWS SDK features
4. API documentation
5. Support single server oauth3legged
6. Support other project like Eclipse

Call for help ~ :)


Dependencies
===============
1. Signpost library
2. Apache http library


Troubleshooting
================
If you are getting Access denied, or API Key not valid, double check that your key settings are setup correctly at admin/build/services/keys and double check that permissions are correct for your user and anonymous.

Questions
===============
Checkout the Issue queue, or create new issues 

Examples
===============
Creating a node

Example:

                    BasicNameValuePair[] data = new BasicNameValuePair[4];

                    data[0] = new BasicNameValuePair("type", "article");
                    data[1] = new BasicNameValuePair("title", "Test android create body 1");

                    String bodyValue = "testing123";
                    data[2] = new BasicNameValuePair("body[und][0][value]", bodyValue);
                    data[3] = new BasicNameValuePair("body[und][0][format]", "filtered_html");

                    // or according to https://gist.github.com/kylebrowning/affc9864487bb1b9c918

                    //data[0] = new BasicNameValuePair("node[type]", "article");
                    //data[1] = new BasicNameValuePair("node[title]", "Test android create 2");

                    String response = oauth.create(data);

