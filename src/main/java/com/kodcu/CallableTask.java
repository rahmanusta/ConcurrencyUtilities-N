package com.kodcu;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: usta
 * Date: 08.09.2013
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class CallableTask implements Callable<Void> {

    private int taskID;


    public CallableTask(int taskID) {
        this.taskID = taskID;
    }


    @Override
    public Void call() throws Exception {
        Thread.sleep(3000);
        System.out.println("CallableTask is running: Task ID="+taskID);
        return null;
    }
}
