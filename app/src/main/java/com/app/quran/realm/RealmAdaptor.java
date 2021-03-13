package com.app.quran.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by c85 on 19/11/15.
 * This class is use to create Realm instance with database and migration functionality.
 * it is working as a base class for Realm.
 */
public class RealmAdaptor {
    /**
     * get realm instance.Give Name to your database.
     *
     * @param context
     * @return
     */
    public static Realm getInstance(Context context) {
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Quran.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(configuration);
    }
}
