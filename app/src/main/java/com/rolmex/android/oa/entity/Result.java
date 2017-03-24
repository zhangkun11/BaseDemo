package com.rolmex.android.oa.entity;

import java.util.List;
import java.util.Set;

public class Result {

    public static final Result DEFAULT_RESULT = new Result(false, "服务器无响应或网络断开");
    public static final Result GET_FILE_FAIL = new Result(false, "图片获取失败");

    public boolean bSuccess;

    public String strMsg;

    public Result(boolean bSuccess,String strMsg){
        this.bSuccess=bSuccess;
        this.strMsg=strMsg;
    }

    public boolean isSuccess(){
        return bSuccess;
    }

    public Set<String> navNameList;

    public List<User> LoginData;

    public String strCode;

    public String strStatus;

}
