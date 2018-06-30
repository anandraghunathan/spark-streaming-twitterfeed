package com.spark.streaming.twitterfeed;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;


public class StreamTwitterFeed implements Serializable {

    private static String PATH = "/Volumes/Coding/twitter-feed-project/streams/";

    JavaStreamingContext jssc;

    /*public JavaDStream<Status> loadData() {
        SparkConf conf = new SparkConf()
                .setAppName("Spark Streaming")
                .set("spark.driver.allowMultipleContexts", "true")
                .setMaster("local[*]");

        jssc = new JavaStreamingContext(conf, Durations.seconds(30));

        System.out.println("Initializing Twitter stream...");

        JavaDStream<Status> tweetsStream = TwitterUtils.createStream(jssc, StreamUtils.getAuth());

        return tweetsStream;
    }*/

    /**
     *  Print the status text of the some of the tweets
     */
    public static void main(String[] args) {

        JavaStreamingContext jssc;

        SparkConf conf = new SparkConf()
                .setAppName("Spark Streaming Twitter Feed Project")
                .setMaster("local[*]");

        jssc = new JavaStreamingContext(conf, Durations.seconds(5));

        System.out.println("Initializing Twitter stream...");

        ObjectMapper mapper = new ObjectMapper();

        // filter
        String[] filters = {"Lebron", "LBJ", "Bron", "free agency"};

        // Dstream
        // Hint: use the print method
        JavaDStream<String> statusTweet = TwitterUtils.createStream(jssc, StreamUtils.getAuth(), filters)
                                                    .map(tweetStatus -> mapper.writeValueAsString(tweetStatus));

        // Print tweet
        statusTweet.print(10);

        // Save those tweets in a file
        statusTweet.repartition(1).dstream().saveAsTextFiles(PATH, "stream.txt");


        // Start the context
        jssc.start();
        jssc.awaitTermination();
    }
}
