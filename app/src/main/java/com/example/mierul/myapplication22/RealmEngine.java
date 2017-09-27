package com.example.mierul.myapplication22;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Hexa-Amierul.Japri on 25/9/2017.
 */

public class RealmEngine extends BaseRealm {

    private Context context;

    public RealmEngine(Context context) {
        this.context = context;
    }

    public void loadPictureUrl(List<ProductImageNode> productImageNodeList ){

        RealmList<PictureUrlRealm> list = new RealmList<>();

        for (ProductImageNode node : productImageNodeList){

            PictureUrlRealm pictureUrlRealm = new PictureUrlRealm();
            pictureUrlRealm.setId(node.getKey());
            pictureUrlRealm.setUrl_1(node.getImage_1());
            pictureUrlRealm.setUrl_2(node.getImage_2());
            pictureUrlRealm.setUrl_3(node.getImage_3());
            pictureUrlRealm.setUrl_4(node.getImage_4());

            list.add(pictureUrlRealm);
        }

        Realm realm = super.getRealm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
    }

    public List<ProductImageNode> getPictureUrlList(){

        Realm realm = super.getRealm();
        RealmResults<PictureUrlRealm> results = realm.where(PictureUrlRealm.class).findAll();
        List<ProductImageNode> list = new ArrayList<>();

        for (PictureUrlRealm pictureUrlRealm : results){

            ProductImageNode node = new ProductImageNode();
            node.setKey(pictureUrlRealm.getId());
            node.setImage_1(pictureUrlRealm.getUrl_1());
            node.setImage_2(pictureUrlRealm.getUrl_2());
            node.setImage_3(pictureUrlRealm.getUrl_3());
            node.setImage_4(pictureUrlRealm.getUrl_4());

            list.add(node);
        }

        return list;
    }


    @Override
    protected Context getContext() {
        return context;
    }
}
