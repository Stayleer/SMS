package validate;

public class CheckValidate {

    public static boolean checkMobile(String mobile){
        if (mobile == null) return false;
        return mobile.matches("^[0-9]{10}$");
        // String.matches()?
    }

    public static boolean checkPayDate(final String payDate){
        if(payDate == null) return false;
        String payDateStr = payDate.replaceAll("\\D", "");
        return payDateStr.matches("^[0-9]{14}$");
    }

    public static boolean checkAmount(String amount){
        if(amount == null || amount.isEmpty()) return false;
            Double.parseDouble(amount);
            return true;
            // ? sao khong co try/catch
    }

    public static boolean checkPromotionCode(String promotionCode){
        return ValidateCommon.isNullOrEmpty(promotionCode) || promotionCode.trim().length() == 0;// th string = " "
    }
}
