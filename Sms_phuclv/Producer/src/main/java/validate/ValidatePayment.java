package validate;

import common.ErrorCommon;
import common.KeyCommon;
import model.Payment;
import syncer.RedisSyncer;

import java.util.HashMap;
import java.util.Map;

public class ValidatePayment extends CheckValidate{
    private static ValidatePayment instance;

    public ValidatePayment() {
    }

    public static ValidatePayment getInstance(){
        if (instance == null){
            synchronized (ValidatePayment.class){
                if (instance == null){
                    instance = new ValidatePayment();
                }
            }
        }
        return instance;
    }

    public Map<String, String> validate(Payment payment){
            Map<String, String> listErr = new HashMap<>();

            if (RedisSyncer.checkKey(payment.getTokenKey())){
                listErr.put(KeyCommon.TOKEN_KEY, ErrorCommon.errorTokenKey);
            }

            if(RedisSyncer.checkKey(payment.getTraceTransfer())){
                listErr.put(KeyCommon.TRANCE_TRANFER, ErrorCommon.errorTraceTransfer);
            }

            if(payment.getApiID() == null || payment.getApiID().isEmpty()){
                listErr.put(KeyCommon.API_ID, ErrorCommon.notEmpty);
            }

            if(!checkMobile(payment.getMobile())){
                listErr.put(KeyCommon.MOBILE, ErrorCommon.invalid);
            }

            if(ValidateCommon.isNullOrEmpty(payment.getBankCode())){
                payment.setBankCode(KeyCommon.BANK_CODE_CONSTANT);
            }else if(!payment.getBankCode().matches("^[0-9]{6}$")){
                listErr.put(KeyCommon.BANK_CODE, ErrorCommon.invalid);
            }

            if(!checkPayDate(String.valueOf(payment.getPayDate()))){
                listErr.put(KeyCommon.PAY_DATE, ErrorCommon.invalid);
            }

            if (!checkAmount(payment.getRealAmount())){
                listErr.put(KeyCommon.REAL_AMOUNT, ErrorCommon.invalid);
            }

            if(payment.getMessageType() == null || payment.getMessageType().isEmpty()){
                payment.setMessageType(KeyCommon.MESSAGE_TYPE_CONSTANT);
            }

            if (payment.getRespCode() == null || payment.getRespCode().isEmpty()){
                    listErr.put(KeyCommon.RESP_CODE, ErrorCommon.invalid);
            }

            if (checkAmount(payment.getRealAmount()) &&
            Integer.parseInt(payment.getRealAmount()) > Integer.parseInt(payment.getDebitAmount())){
                listErr.put(KeyCommon.REAL_AMOUNT, ErrorCommon.errorRealAmount);
            }

            if (!payment.getDebitAmount().equals(payment.getRealAmount()) && checkPromotionCode(payment.getPromotionCode())){
                listErr.put(KeyCommon.PROMOTION_CODE, ErrorCommon.invalid);
            }
            return listErr;
    }
}
