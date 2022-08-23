package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

    private static final Logger logger = LogManager.getLogger(Property.class);

    private static Properties intansce;

    public Property() {
    }

    public static Properties getIntansce(){
        if (intansce == null){
            synchronized (Properties.class){
                if  (intansce == null)  {
                    intansce = new Properties();

                    try {
                        intansce.load(new FileInputStream("ten file gì đấy"));
                    } catch (IOException e) {
                        logger.error("Loi khi doc file: ", e);
                    }
                }
            }
        }
        return intansce;
    }
}
