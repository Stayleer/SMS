package utils;

import common.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Properties;

public class ConnectRedis {
    private static final Logger logger = LogManager.getLogger(ConnectRedis.class);

    private static final String REDIS_MARTER_NAME = "REDIS_MARTER_NAME";

    private static final String REDIS_PASSWORD = "REDIS_PASSWORD";

    private static final String REDIS_HOST_MASTER = "REDIS_HOST_MARTER";

    private static final String REDIS_PROT_MASTER = "REDIS_HOST_MASTER";

    private static final String REDIS_HOST_SLAVE = "REDIS_HOST_SLAVE";

    private static final String REDIS_PORT_SLAVE = "REDIS_PORT_SLAVE";

    private static final String MAX_DATABASE = "MAX_DATABASE";

    private static final Properties properties = Property.getIntansce();

    private static JedisSentinelPool sentinelPool = null;
    private static ConnectRedis instance;

    public ConnectRedis() {
    }

    public static ConnectRedis getInstance(){
        if (instance == null){
            synchronized (ConnectRedis.class){
                if (instance == null){
                        instance = new ConnectRedis();
                }
            }
        }
        return instance;
    }

    public JedisSentinelPool getJedisSentinelPool(){
        return sentinelPool;
    }
}
