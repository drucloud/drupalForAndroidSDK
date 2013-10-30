drupalForAndroidSDK
===================

Android SDK wokring with Drupal Services

Installation
==================
You need to use

Android studio 0.3.+ Gradle 1.8

up for this project.

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
