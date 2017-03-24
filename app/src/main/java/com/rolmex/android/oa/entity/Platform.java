
package com.rolmex.android.oa.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Platform implements Parcelable {

    public boolean bSuccess;

    public String strMsg;

    public String RequestIP;

    public String AuthToken;

    public boolean ValidSD;

    public String SDCard;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(bSuccess ? (byte)1 : (byte)0);
        dest.writeString(this.strMsg);
        dest.writeString(this.RequestIP);
        dest.writeString(this.AuthToken);
        dest.writeByte(ValidSD ? (byte)1 : (byte)0);
        dest.writeString(this.SDCard);
    }

    public Platform() {
    }

    private Platform(Parcel in) {
        this.bSuccess = in.readByte() != 0;
        this.strMsg = in.readString();
        this.RequestIP = in.readString();
        this.AuthToken = in.readString();
        this.ValidSD = in.readByte() != 0;
        this.SDCard = in.readString();
    }

    public static final Creator<Platform> CREATOR = new Creator<Platform>() {
        public Platform createFromParcel(Parcel source) {
            return new Platform(source);
        }

        public Platform[] newArray(int size) {
            return new Platform[size];
        }
    };
}
