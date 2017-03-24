package com.rolmex.android.oa.entity;

import java.io.Serializable;

public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1220820501735439923L;

    public boolean bSuccess;

    public String strMsg;

    String strAuthToken;

    String varBranch;

    String varUID;

    String varUserID;

    String varUserName;

    String varOperatPWD;

    String varNicName;

    String varRealName;

    boolean chrIsServiceFreez;

    public boolean chrIsSellFreez;

    public boolean isNotShowResults;

    boolean chrIsAutomatic;

    boolean chrIsShop;// 是否为店

    String varStoCode;// 仓库编号

    String chrShopType;

    String varShopReID;

    String varShopReName;

    String nvrStoName;// 仓库名字

    int intRank;

    String Intprovince;

    String Intcity;

    String Intcounty;

    String Varuseraddress;

    String varContact_Name;

    String varMobile;

    String ShopIsLock;

    String intBankMobi;

    String intBankCode;

    String varRequestIP;

    String varZipCode;

    String varTeamNo;

    public String ValidCode;

    public String getValidCode() {
        return ValidCode;
    }

    public String getVarTeamNo() {
        return varTeamNo;
    }

    public String getVarBranch() {
        return varBranch;
    }

    public String getVarUID() {
        return varUID;
    }

    public String getVarUserID() {
        return varUserID;
    }

    public String getVarOperatPWD() {
        return varOperatPWD;
    }

    public void setVarOperatPWD(String strNewPWD) {
        this.varOperatPWD=strNewPWD;
    }

    public String getVarRealName() {
        return varRealName;
    }

    public boolean isChrIsShop() {
        return chrIsShop;
    }

    public String getVarStoCode() {
        return varStoCode;
    }

    public String getChrShopType() {
        return chrShopType;
    }

    public int getIntRank() {
        return intRank;
    }

    public String getVarContact_Name() {
        return varContact_Name;
    }

    public String getVarMobile() {
        return varMobile;
    }

    public String getVarZipCode() {
        return varZipCode;
    }

    public boolean isSenior() {
        return intRank >= 30;
    }
}
