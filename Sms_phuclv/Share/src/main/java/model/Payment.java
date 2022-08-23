package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Payment {

    private long ID;

    private String tokenKey;

    private String apiID;

    private String mobile;

    private String bankCode;

    private String accountNo;

    private String payDate;

    private String addtionalData;

    private String debitAmount;

    private String respCode;

    private String respDesc;

    private String traceTransfer;

    private String messageType;

    private String checkSum;

    private String orderCode;

    private String userName;

    private String realAmount;

    private String promotionCode;

    private String queueNameResponse;

    private String addValue;

    public Payment(long ID, String tokenKey, String apiID, String mobile, String bankCode, String accountNo, String payDate, String addtionalData, String debitAmount, String respCode, String respDesc, String traceTransfer, String messageType, String checkSum, String orderCode, String userName, String realAmount, String promotionCode, String queueNameResponse, String addValue) {

        this.ID = ID;
        this.tokenKey = tokenKey;
        this.apiID = apiID;
        this.mobile = mobile;
        this.bankCode = bankCode;
        this.accountNo = accountNo;
        this.payDate = payDate;
        this.addtionalData = addtionalData;
        this.debitAmount = debitAmount;
        this.respCode = respCode;
        this.respDesc = respDesc;
        this.traceTransfer = traceTransfer;
        this.messageType = messageType;
        this.checkSum = checkSum;
        this.orderCode = orderCode;
        this.userName = userName;
        this.realAmount = realAmount;
        this.promotionCode = promotionCode;
        this.queueNameResponse = queueNameResponse;
        this.addValue = addValue;
    }

    public Payment() {
    }
}
