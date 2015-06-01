package com.notifyme.ws.beans;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author Meir Winston
 */
@ApiModel("Signup Response")
public class SignupResponse {
    private long accountId;

    public SignupResponse(long accountId){
        this.accountId = accountId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
