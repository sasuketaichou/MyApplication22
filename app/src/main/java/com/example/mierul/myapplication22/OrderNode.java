package com.example.mierul.myapplication22;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hexa-Amierul.Japri on 24/9/2017.
 */

public class OrderNode implements Parcelable {

    public String productName;
    public String numOrder;
    public String picKey;
    public String ordKey;
    public String url;
    public String address;
    public String note;
    public String total;
    public String usrOrdKey;

    public OrderNode(){

    }

    protected OrderNode(Parcel in) {
        productName = in.readString();
        numOrder = in.readString();
        picKey = in.readString();
        ordKey = in.readString();
        url = in.readString();
        address = in.readString();
        note = in.readString();
        total = in.readString();
        usrOrdKey = in.readString();
    }

    public static final Creator<OrderNode> CREATOR = new Creator<OrderNode>() {
        @Override
        public OrderNode createFromParcel(Parcel in) {
            return new OrderNode(in);
        }

        @Override
        public OrderNode[] newArray(int size) {
            return new OrderNode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(numOrder);
        dest.writeString(picKey);
        dest.writeString(ordKey);
        dest.writeString(url);
        dest.writeString(address);
        dest.writeString(note);
        dest.writeString(total);
        dest.writeString(usrOrdKey);
    }
}
