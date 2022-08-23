package service;

import common.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LeakyBucket extends  RateLimiter{
    private static final Logger logger = LogManager.getLogger(LeakyBucket.class);

    private static final String maxRequestPerSec = "maxRequestPerSec";

    private static Properties properties = Property.getIntansce();

    private long nextAllowedTime;

    private final long REQUEST_INTERVAL_MILLS;

    private final long TIME_OUT_REQUEST = 5;

    private static LeakyBucket intansce;

    public static LeakyBucket getInstance(){
        if  (intansce == null){
            synchronized (LeakyBucket.class){
                if (intansce == null){
                        intansce = new LeakyBucket(Integer.parseInt(properties.getProperty(maxRequestPerSec)));

                }
            }
        }
        return intansce;
    }

    public LeakyBucket(int maxRequestPerSec){
        super(maxRequestPerSec);
        REQUEST_INTERVAL_MILLS = 1000/maxRequestPerSec;
        nextAllowedTime = System.currentTimeMillis();
    }

    private boolean allow(){
        long curTime = System.currentTimeMillis();
        synchronized (this){
        if (curTime >=  nextAllowedTime){
            nextAllowedTime = curTime + REQUEST_INTERVAL_MILLS;
            return true;
            }
        }
        return false;
    }

    @Override
    public void checkAllowRequest(){
        while (!allow()){
            //??
            try {
                TimeUnit.MICROSECONDS.sleep(TIME_OUT_REQUEST);
            } catch (InterruptedException e) {
                logger.error("loi gi day?: ", e);
            }
        }
    }


}
