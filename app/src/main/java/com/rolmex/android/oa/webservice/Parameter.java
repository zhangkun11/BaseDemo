package com.rolmex.android.oa.webservice;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Parameter {
    private static final DecimalFormat df = new DecimalFormat("0.####");// 最多保留4位小数，如果小数为零，则不显示小数
    private Context context;
    private Method method;
    private List<KVPair> keyValuePairs;


    String signName;
    boolean encrypted;
    boolean signed;


    static class KVPair {
        String key;
        String value;
        boolean joinSign;
        boolean encrypt;

        public KVPair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public Parameter(Context context, Method method) {
        this.context = context;
        this.method = method;
        keyValuePairs = new ArrayList<KVPair>();
        add("strApkNo", context.apkNo);
        add("strAuthorizationCode", context.strAuthorizationCode);
    }

    public void add(String key, String value, boolean joinSign, boolean encrypt) {
        KVPair p = new KVPair(key, value);
        p.joinSign = joinSign;
        p.encrypt = encrypt;
        keyValuePairs.add(p);
    }

    public void add(String key, int value, boolean joinSign, boolean encrypt) {
        add(key, String.valueOf(value), joinSign, encrypt);
    }

    public void add(String key, double value, boolean joinSign, boolean encrypt) {
        add(key, formatedDouble(value), joinSign, encrypt);
    }


    /**
     * 参与签名的double值要经过此方法格式化，与服务端保持相同
     * 对于小数点后为全零的值(1.00),转为字符串后，java默认会保留小数("1.0"),
     * 而服务端不会保留小数("1"),因此会造成验证签名失败的错误
     */
    private static String formatedDouble(double value) {
        return df.format(value);
    }

    private void add(String name, String value) {
        KVPair p = new KVPair(name, value);
        keyValuePairs.add(p);
    }

    public void signName(String name) {
        signName = name;
    }

    public List<KVPair> toList() {
        if (!signed) {
            signed = true;
            String unsignedStr = getSignParameters();
            if (signName == null) {
                String signedStr = context.sign(unsignedStr);
                add("strMsg", signedStr);
                add("strSignMsg", signedStr);
                add("signMsg", signedStr);
                //

            } else {
                add(signName, context.sign(unsignedStr));
            }
        }

        if (!encrypted) {
            encrypted = true;
            for (KVPair p : keyValuePairs) {
                if (p.encrypt) {
                    p.value = method.encrypt(p.value);
                }
            }
        }


        return keyValuePairs;
    }



    public String getSignParameters() {
        StringBuilder sb = new StringBuilder();
        for (KVPair p : keyValuePairs) {
            if (p.joinSign && p.value != null) {
                sb.append(p.value);
            }
        }
        return sb.toString();
    }
}
