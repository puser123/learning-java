package com.learning.java.designpattern;


//https://javarevisited.blogspot.com/2012/06/20-design-pattern-and-software-design.html#axzz8eN6bfRJP

//https://javarevisited.blogspot.com/2011/03/10-interview-questions-on-singleton.html#axzz8eN6bfRJP

//  https://javarevisited.blogspot.com/2012/06/20-design-pattern-and-software-design.html#google_vignette


import java.util.HashMap;

public class SingleTonDesignPattern {

    //https://www.baeldung.com/spring-singleton-concurrent-requests#:~:text=It's%20possible%20for%20Spring%20to,inside%20methods%20during%20thread%20execution.
    private static SingleTonDesignPattern instance;
    /**
     * Round - 2 LLD Design(Machine Coding Round) (1.5 hr)
     * Taken by a SDE-2.
     * Suppose you have to create an Application Like Gaana or Spotify.
     * You need to create.
     * Song list (Play List)
     * Users will be able to play the playlist and there should be a shuffle functionality. ( Random Shuffle)
     * From that play list any random song will be played out.
     * One song which has been played will not be repeated again.
     * And the shuffling should be random, it shouldn’t be one by one, 1st -> 2nd -> 3rd (X)
     * 1st —> 8th —> last song
     *
     * Round - 3 HLD Design (1hr)
     * Taken by an EM. Design communication/notification system. Should support following:
     *
     * Different Priority of Notifications.
     * Different type of Notifications.
     * Different Way of Sending Notification (SMS,Email,Push)
     * Should be Flexibile, Extensible to support combinations of above 3.
     * One of the best Interwier i ever meet.
     **/
    private SingleTonDesignPattern() {}
    public static SingleTonDesignPattern getInstance() {

        if(instance == null) {
            instance = new SingleTonDesignPattern();
        }
        return instance;
    }

    public String getDescription() {
        return "This is singleton design pattern example";
    }

}
