package syncer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import utils.ConnectRedis;

import java.util.Calendar;
import java.util.jar.JarFile;

public class RedisSyncer {
    private static final Logger logger = LogManager.getLogger(RedisSyncer.class);

    public static synchronized String saveDataRedis(String key){
            try(Jedis redis = ConnectRedis.getInstance().getJedisSentinelPool().getResource()){
                redis.set(key, "");
                redis.pexpireAt(key, timeLimitTokenKey());
                logger.info("Insert success to redis");
                logger.info("End insert data to redis: {}", key);
                return key;
            }catch (Exception e) {
                logger.error("Error insert data to redis: ", e);
            }
            return null;
    }

    public static long timeLimitTokenKey(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) + 1,
                0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static boolean checkKey(String key){
        try (Jedis jedis = ConnectRedis.getInstance().getJedisSentinelPool().getResource()){
            String value = jedis.get(key);
                if (value != null){
                    logger.info("value {}", value);
                    return true;
                }
        }catch (Exception e){
            logger.error("error init redis", e);
        }
        return false;
    }
}
