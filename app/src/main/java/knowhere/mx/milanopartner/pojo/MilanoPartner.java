package knowhere.mx.milanopartner.pojo;

import java.io.Serializable;

/**
 * Created by cacorona on 13/07/2016.
 */
public class MilanoPartner implements Serializable {

    private int userId;
    private MilanoUser user;
    private MilanoRate rate;
    private MilanoFinance finance;
    private MilanoSettings account;

    public MilanoUser getUser() {
        return user;
    }

    public void setUser(MilanoUser user) {
        this.user = user;
    }

    public MilanoRate getRate() {
        return rate;
    }

    public void setRate(MilanoRate rate) {
        this.rate = rate;
    }

    public MilanoFinance getFinance() {
        return finance;
    }

    public void setFinance(MilanoFinance finance) {
        this.finance = finance;
    }

    public MilanoSettings getAccount() {
        return account;
    }

    public void setAccount(MilanoSettings account) {
        this.account = account;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
