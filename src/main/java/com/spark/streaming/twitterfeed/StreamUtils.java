package com.spark.streaming.twitterfeed;

import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationBuilder;


class StreamUtils {

    private static String CONSUMER_KEY = "DJl4B0IX2qYb1ZmT0TQ6jvWo7";
    private static String CONSUMER_SECRET = "6k4iiBJjILMVu1EGeXFEbgBIlisWjIeJkTCd9LTrfGVSzti0kE";
    private static String ACCESS_TOKEN = "166878441-iUDmnNX5GII1YixgDF2zcg9SCQc2vDm3gEGCg8vT";
    private static String ACCESS_TOKEN_SECRET = "R2umVX9UuUAf1oHk1r9UMm1gopLvryqaEK8lAjCYSlKKI";

    static OAuthAuthorization getAuth() {

        return new OAuthAuthorization(
                new ConfigurationBuilder().setOAuthConsumerKey(CONSUMER_KEY)
                        .setOAuthConsumerSecret(CONSUMER_SECRET)
                        .setOAuthAccessToken(ACCESS_TOKEN)
                        .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
                        .build());
    }

}
