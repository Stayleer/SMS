package utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import common.Property;
import dao.ChannelPoolDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ChannelPool implements ChannelPoolDAO {

    public static final Logger logger = LogManager.getLogger(ChannelPool.class);

    private final static String RABBITMQ_HOST = "RABBITMQ_HOST";

    private final static String RABBITMQ_PORT = "RABBITMQ_PORT";

    private final static String RABBITMQ_USERNAME = "RABBITMQ_USERNAME";

    private final static String RABBITMQ_PASSWORD = "RABBITMQ_PASSWORD";

    private final static String RABBITMQ_HEARTBEAT = "RABBITMQ_HEARTBEAT";

    private final static String VIRTUAL_HOST = "RABBITMQ_VIRTUALHOST";

    private static ConnectionFactory factory;

    private static final int INITIAL_POOL_SIZE = 20;

    private static final int TIME_OUT_GET_POOL = 150;

    private BlockingDeque<Channel> channelPool = new LinkedBlockingDeque<>(INITIAL_POOL_SIZE);

    private static ChannelPool instance;

    private static final Properties properties = Property.getIntansce();

    public ChannelPool() {
    }

    public static ChannelPool getInstance(){
        if(instance == null){
            synchronized (ChannelPool.class){
//                logger.info();
            }
        }
        return null;
    }

    @Override
    public Channel getChannel() throws InterruptedException {
        return null;
    }

    @Override
    public void releaseChannel(Channel channel) throws InterruptedException {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void destroy() {

    }
}
