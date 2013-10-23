package com.kodcu;

import org.quartz.CronExpression;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: usta
 * Date: 26.07.2013
 * Time: 01:56
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(urlPatterns = "/kodcu", name = "KodcuServlet")
public class KodcuServlet extends HttpServlet {

    @Resource
    private ManagedExecutorService defaultmanagedExecutorService;

    @Resource
    private ManagedScheduledExecutorService defaultScheduledExecutorService;

    @Resource(lookup = "concurrent/KodcuExecutor")
    private ManagedExecutorService managedExecutorService;

    @Resource(lookup = "concurrent/KodcuScheduledExecutor")
    private ManagedScheduledExecutorService scheduledExecutorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /*
        try{
            InitialContext context=new InitialContext(); // (5)

            ManagedExecutorService managedExecutorServiceWithContext =
                    (ManagedExecutorService) context.lookup("concurrent/KodcuExecutor");
        } catch (NamingException nexp){
            nexp.printStackTrace();
        } */

        try {


            scheduledExecutorService
                    .schedule(new RunnableTask(), new CronTrigger(new CronExpression("0/5 * * * * ?")));

            List<Callable<Void>> gorevler = new ArrayList<>();

            for (int i = 0; i < 100; i++)
                gorevler.add(new CallableTask(i));

            defaultmanagedExecutorService.invokeAll(gorevler);

            Thread.currentThread().join();

        } catch (InterruptedException | ParseException e) {
            e.printStackTrace();
        }

    }
}
