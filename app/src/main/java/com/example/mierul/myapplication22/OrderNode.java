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
    public String url;
    private long timestamp;

    public OrderNode(){
    }


    protected OrderNode(Parcel in) {
        productAddress = in.readString();
        productNote = in.readString();
        productName = in.readString();
        numOrder = in.readString();
        picKey = in.readString();
        total = in.readString();
        timestamp = in.readLong();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productAddress);
        dest.writeString(productNote);
        dest.writeString(productName);
        dest.writeString(numOrder);
        dest.writeString(picKey);
        dest.writeString(total);
        dest.writeLong(timestamp);
        dest.writeString(url);
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

    public String getProductAddress() {
        return productAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public String getProductNote() {
        return productNote;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(String numOrder) {
        this.numOrder = numOrder;
    }

    public String getPicKey() {
        return picKey;
    }

    public void setPicKey(String picKey) {
        this.picKey = picKey;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
