package com.kodcu;

import org.quartz.CronExpression;

import javax.enterprise.concurrent.LastExecution;
import javax.enterprise.concurrent.Trigger;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: usta
 * Date: 08.09.2013
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
public class CronTrigger implements Trigger {

    private CronExpression cronExpression;

    public CronTrigger(CronExpression cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public Date getNextRunTime(LastExecution lastExecutionInfo, Date taskScheduledTime) {

        Date lastDate=lastExecutionInfo==null?taskScheduledTime:lastExecutionInfo.getRunEnd();
        return cronExpression.getTimeAfter(lastDate);

    }

    @Override
    public boolean skipRun(LastExecution lastExecutionInfo, Date scheduledRunTime) {
        return false;
    }
}
