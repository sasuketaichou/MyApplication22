package com.example.mierul.myapplication22;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hexa-Amierul.Japri on 25/9/2017.
 */

public class ProductImageNode implements Parcelable {

    private String key;
    private String image_1;
    private String image_2;
    private String image_3;
    private String image_4;

    public ProductImageNode(){
    }

    protected ProductImageNode(Parcel in) {
        key = in.readString();
        image_1 = in.readString();
        image_2 = in.readString();
        image_3 = in.readString();
        image_4 = in.readString();
    }

    public static final Creator<ProductImageNode> CREATOR = new Creator<ProductImageNode>() {
        @Override
        public ProductImageNode createFromParcel(Parcel in) {
            return new ProductImageNode(in);
        }

        @Override
        public ProductImageNode[] newArray(int size) {
            return new ProductImageNode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(image_1);
        dest.writeString(image_2);
        dest.writeString(image_3);
        dest.writeString(image_4);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }

    public String getImage_3() {
        return image_3;
    }

    public void setImage_3(String image_3) {
        this.image_3 = image_3;
    }

    public String getImage_4() {
        return image_4;
    }

    public void setImage_4(String image_4) {
        this.image_4 = image_4;
    }
}
