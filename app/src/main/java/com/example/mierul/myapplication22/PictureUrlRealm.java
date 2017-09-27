package com.example.mierul.myapplication22;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Hexa-Amierul.Japri on 25/9/2017.
 */

public class PictureUrlRealm extends RealmObject {

    @PrimaryKey
    private String id;
    private String url_1;
    private String url_2;
    private String url_3;
    private String url_4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl_1() {
        return url_1;
    }

    public void setUrl_1(String url_1) {
        this.url_1 = url_1;
    }

    public String getUrl_2() {
        return url_2;
    }

    public void setUrl_2(String url_2) {
        this.url_2 = url_2;
    }

    public String getUrl_3() {
        return url_3;
    }

    public void setUrl_3(String url_3) {
        this.url_3 = url_3;
    }

    public String getUrl_4() {
        return url_4;
    }

    public void setUrl_4(String url_4) {
        this.url_4 = url_4;
    }
}
