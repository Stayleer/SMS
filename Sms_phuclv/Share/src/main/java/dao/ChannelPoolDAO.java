package dao;

import com.rabbitmq.client.Channel;

public interface ChannelPoolDAO {
    Channel getChannel() throws InterruptedException;

    void releaseChannel(Channel channel) throws InterruptedException;

    int getSize();

    void destroy();
}
