package syncer;

import com.rabbitmq.client.Channel;
import model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Producer {
    private static final Logger logger = LogManager.getLogger(Producer.class);

    private static final long TIME_OUT_MESSAGE = 150;

    private static final int NUMBER_MESSAGE = 1;

    // PUBLISH RABBITMQ

    public static String submitData(Payment payment){
        //fixme return null ghi log ghi rõ
        // ? fixme là gì
        if(payment == null){
            logger.info("data payment null");
            return null;
        }

        Channel channel = null;
        try {
            logger.debug("Begin init channel - Bat dau bat dau kenh");
            channel = ChP
        }


    }
}
