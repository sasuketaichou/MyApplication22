package com.example.mierul.myapplication22;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Hexa-Amierul.Japri on 25/9/2017.
 */

public abstract class BaseRealm {
    private Realm realm = null;
    private RealmConfiguration realmConfig;

    protected abstract Context getContext();

    private RealmConfiguration getRealmConfig(){
        if (realmConfig == null)
            Realm.init(getContext());
        realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        return realmConfig;
    }

    public Realm getRealm(){
        RealmConfiguration config = getRealmConfig();
        return realm == null ? Realm.getInstance(config):realm;
    }
}
