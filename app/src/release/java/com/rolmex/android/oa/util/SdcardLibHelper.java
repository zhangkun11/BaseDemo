package com.rolmex.android.oa.util;

import Sumavision.Library.SmartSDLib;

public final class SdcardLibHelper {

    public String getCsn() {
        /*String csn = null;
        try {
            csn = readCsnFromZLXT();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(csn)) {
            try {
                csn = readCsnFromSuma();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        //正式版本取消SD卡验证
        return "3426BF01F16DADD90100";
//        return csn;
    }

/*
    private String readCsnFromZLXT() throws IOException, UnionPayPaymentException {
        return UPPCardAppInterface.getInstance().getCSN(MainApp.getInstance());
    }

    private String readCsnFromSuma() {
        SmartSDLib smartSDLib = SmartSDLib.getSDAndICLibInstance();
        initSmartCardLib(smartSDLib, MainApp.getInstance());
        String preLoginMsg[] = new String[1];
        smartSDLib.SumaSD_Prelogin(preLoginMsg);

        Pattern pattern = Pattern.compile("([A-Fa-f0-9]{20})");
        Matcher mather = pattern.matcher(preLoginMsg[0]);
        if (mather.find()) {
            return mather.group();
        } else {
            return null;
        }
    }

    @TargetApi(19)
    private void initSmartCardLib(SmartSDLib lib, Context context) {
        String CARD_PATH = "";
        // 4.4.2
        if (Build.VERSION.SDK_INT >= 19) // Android Version 4.4.2 or latter
        {
            File file[] = null;
            file = context.getExternalFilesDirs("");
            if (file.length > 1) {
                if (file[1] != null)
                    CARD_PATH = file[1].getAbsolutePath();
                else
                    CARD_PATH = ""; // Not insert SD Card
            } else {
                CARD_PATH = file[0].getAbsolutePath();
            }
        }
        SmartSDLib.cardPath = CARD_PATH;
        lib.SumaSD_Init();
    }*/

}
