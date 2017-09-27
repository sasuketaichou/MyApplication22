package com.example.mierul.myapplication22;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hexa-Amierul.Japri on 24/9/2017.
 */

public class OrderNode implements Parcelable {

    public String productAddress;
    public String productNote;
    public String productName;
    public String numOrder;
    public String picKey;
    public String total;

    public OrderNode(){
    }

    protected OrderNode(Parcel in) {
        productAddress = in.readString();
        productNote = in.readString();
        productName = in.readString();
        numOrder = in.readString();
        picKey = in.readString();
        total = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productAddress);
        dest.writeString(productNote);
        dest.writeString(productName);
        dest.writeString(numOrder);
        dest.writeString(picKey);
        dest.writeString(total);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
