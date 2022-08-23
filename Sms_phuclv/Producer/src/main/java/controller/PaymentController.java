package controller;

import common.ErrorCommon;
import common.GsonCustom;
import common.KeyCommon;
import model.Payment;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import service.LeakyBucket;
import syncer.RedisSyncer;
import validate.ValidateCommon;
import validate.ValidatePayment;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.UUID;


@Path("/api")
public class PaymentController {

    private static Logger logger = LogManager.getLogger(PaymentController.class);

    @POST
    @Path("/request_data")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)

    public String requestData (Payment payment) throws InterruptedException{
        LeakyBucket.getInstance().checkAllowRequest();  //?

        String tokenKey = UUID.randomUUID().toString().replace("-", "");  //?
        ThreadContext.put(KeyCommon.TOKEN_KEY, tokenKey);

        if (payment.getTokenKey() == null || payment.getTokenKey().isEmpty()){
                payment.setTokenKey(tokenKey);
        }

        if (ValidateCommon.isNullOrEmpty(payment.getTraceTransfer())){
            payment.setTraceTransfer(RandomStringUtils.randomAlphabetic(12)); //?
        }

        logger.info("Begin validate data - da kiem tra data");

        Map<String, String> listErr = ValidatePayment.getInstance().validate(payment);
        if(listErr.size() > 0){
            logger.error("Error validate. No request data");
            logger.info("list error validate: {}", listErr);
            return GsonCustom.getInstance().toJson(listErr);
        }
        String token = payment.getTokenKey();
        logger.info("Begin save tokenKey in redis: {}", token);
        if(RedisSyncer.saveDataRedis(token) == null) return ErrorCommon.errorSystem;
        logger.info("Save tokenKey success: {}", token);

        String tranceTransfer = payment.getTraceTransfer();
        logger.info("BD luu tranceTransfer trong redis: {}", tranceTransfer);
        if(RedisSyncer.saveDataRedis(tranceTransfer) == null) return ErrorCommon.errorSystem;
        logger.info("Ket thuc luu tranceTransfer trong redis", tranceTransfer);

        logger.info("Gui du lieu den RabbitMQ");
        String resultResponse = Pro

    }

}
