package com.kodcu;

/**
 * Created with IntelliJ IDEA.
 * User: usta
 * Date: 08.09.2013
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class RunnableTask implements Runnable {


    @Override
    public void run() {
        System.out.println("Runnable task is running in every 5 seconds");
    }
}
