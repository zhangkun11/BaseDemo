package com.rolmex.android.oa.webservice;

import android.text.TextUtils;
import android.util.Log;

import com.androidquery.util.AQUtility;
import com.google.gson.Gson;
import com.rolmex.android.oa.entity.Result;
import com.rolmex.android.oa.utils.TrippleDes;
import com.rolmex.android.oa.ws.IWsdl2CodeEvents;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

public class Method {
    private static final String TAG = "vidic";
    Context context;

    String method;

    String key;

    public String getParameter() {
        return parameter.getSignParameters();
    }

    Parameter parameter;

    Callback callback;

    boolean plainResponse;

    public interface Callback {
        String beforeParse(String response);
    }

    Method(Context context, String method, String key) {
        this.context = context;
        this.method = method;
        parameter = new Parameter(context, this);
        this.key = key;
    }

    public Method setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public Method plainResponse() {
        plainResponse = true;
        return this;
    }

    public void add(String key, String value) {
        Log.i("vidic","key=="+key+"~~~~~value=="+value);
        parameter.add(key, value, true, true);
    }

    public void add(String key, int value) {
        parameter.add(key, value, true, true);
    }

    public void add(String key, double value) {
        parameter.add(key, value, true, true);
    }

    public void add(String key, String value, boolean joinSign, boolean encrypt) {
        parameter.add(key, value, joinSign, encrypt);
    }

    public void add(String key, int value, boolean joinSign, boolean encrypt) {
        parameter.add(key, value, joinSign, encrypt);
    }

    public void add(String key, double value, boolean joinSign, boolean encrypt) {
        parameter.add(key, value, joinSign, encrypt);
    }

    public Method signName(String name) {
        parameter.signName(name);
        return this;
    }

    public <T> T invoke(Class<T> tClass) {
        return invoke(tClass, null);
    }

    public String invoke() {
        String response = invoke(context.getUrl(), method, parameter, null);
        if (!plainResponse) {
            response = decrypt(response);
        }
        return response;
    }

    public <T> T invoke(Class<T> tClass, T defaultObj) {
        String response = invoke();
        if (callback != null) {
            response = callback.beforeParse(response);
        }
        Log.i(TAG, method);
        Log.i(TAG, response);
        if (response.contains("SessionTimeout")) {
            AQUtility.post(new Runnable() {
                @Override
                public void run() {
                    //TODO
                }
            });
        }

        T obj = getGson().fromJson(response, tClass);
        if (obj == null) {
            obj = defaultObj;
        }
        return obj;
    }

    String encrypt(String str) {
        if (str == null) {
            str = "";
        }
        return TrippleDes.encrypt(key, str);
    }


    String decrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        String decryptedStr = TrippleDes.decrypt(key, str);

        if (TextUtils.isEmpty(decryptedStr)) {
            decryptedStr = "{'bSuccess':False,'strMsg':'SessionTimeout'}";
        }
        return decryptedStr;
    }

    public IWsdl2CodeEvents eventHandler;
    public int timeOut = 60000;

    public static Result upLoadImage(String bytestr,String url, String method) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://tempuri.org/", method);
        soapReq.addProperty("bytestr", bytestr);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, 30000);
        try {
            httpTransport.call("http://tempuri.org/" + method, soapEnvelope);
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault) {
                SoapFault fault = (SoapFault) retObj;
                Exception ex = new Exception(fault.faultstring);
                ex.printStackTrace();
            } else {
                SoapObject result = (SoapObject) retObj;
                if (result.getPropertyCount() > 0) {
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                        SoapPrimitive j = (SoapPrimitive) obj;
                        String resultVariable = j.toString();
                        return getGson().fromJson(resultVariable, Result.class);
                    } else if (obj != null && obj instanceof String) {
                        String resultVariable = (String) obj;
                        return getGson().fromJson(resultVariable, Result.class);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.DEFAULT_RESULT;
    }

    private String invoke(String url, String method, Parameter parameter,
                          List<HeaderProperty> headers) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://tempuri.org/", method);
        for (Parameter.KVPair p : parameter.toList()) {
            soapReq.addProperty(p.key, p.value);
            Log.i("vidic","key="+p.key+"~~~~value="+p.value);
        }
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            if (headers != null) {
                httpTransport.call("http://tempuri.org/" + method, soapEnvelope, headers);
            } else {
                httpTransport.call("http://tempuri.org/" + method, soapEnvelope);
            }
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault) {
                SoapFault fault = (SoapFault) retObj;
                Exception ex = new Exception(fault.faultstring);
                if (eventHandler != null)
                    eventHandler.Wsdl2CodeFinishedWithException(ex);
            } else {
                SoapObject result = (SoapObject) retObj;
                if (result.getPropertyCount() > 0) {
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                        SoapPrimitive j = (SoapPrimitive) obj;
                        String resultVariable = j.toString();
                        return resultVariable;
                    } else if (obj != null && obj instanceof String) {
                        String resultVariable = (String) obj;
                        return resultVariable;
                    }
                }
            }
        } catch (Exception e) {
            if (eventHandler != null)
                eventHandler.Wsdl2CodeFinishedWithException(e);
            e.printStackTrace();
        }
        return "";
    }

    private static Gson getGson(){
        return  new Gson();
    }

}
