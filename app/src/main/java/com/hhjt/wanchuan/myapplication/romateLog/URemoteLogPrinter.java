package com.hhjt.wanchuan.myapplication.romateLog;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator
 * on 2019/11/1 10:55.
 * desc:
 */
public class URemoteLogPrinter {

    private List<ULog> logs;
    private String url;
    private int interval = 1000; //单位 毫秒

    private Timer timer;
    private boolean running;

    public URemoteLogPrinter(){

    }

    public URemoteLogPrinter(String remoteUrl, int interval){
        this.logs = Collections.synchronizedList(new ArrayList<ULog>());
        this.url = remoteUrl;
        this.interval = interval;
    }

    public void print(ULog log){

        start();
        synchronized (logs) {
            logs.add(log);
        }
    }

    public void printImmediate(String url, ULog log){

//        Map<String, String> params = new HashMap<String,String>();
//        params.put("log", log.toJSON());
        UHttpUtils.httpPost(url, log.toJSON());
    }

    public List<ULog> getAndClear(){
        synchronized (logs) {
            List<ULog> all = new ArrayList<ULog>(logs);
            logs.clear();
            return all;
        }
    }

    public void start(){
        if(running){
            return;
        }

        running = true;
        TimerTask task = new LogPrintTask();
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 100, interval);
    }

    public void stop(){
        if(timer != null){
            timer.cancel();
        }
        running = false;
    }

    class LogPrintTask extends TimerTask {

        @Override
        public void run() {
            try{

                List<ULog> logs = getAndClear();

                if(logs.size() > 0){
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for(ULog log : logs){
                        sb.append(log.toJSON()).append(",");
                    }
                    sb.deleteCharAt(sb.length()-1).append("]");

//                    Map<String, String> params = new HashMap<String,String>();
//                    params.put("log", sb.toString());

                    UHttpUtils.httpPost(url, sb.toString());

                }

            }catch(Exception e){
                e.printStackTrace();
                stop();
            }
        }

    }
}