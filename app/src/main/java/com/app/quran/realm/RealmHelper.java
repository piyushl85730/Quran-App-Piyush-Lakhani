package com.app.quran.realm;

import android.content.Context;
import android.util.Log;

import com.app.quran.realm.model.Quran;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class RealmHelper {

    private static final String TAG = RealmHelper.class.getSimpleName();
    public Realm realm;
    public Context context;

    public RealmHelper(Context context) {
        realm = RealmAdaptor.getInstance(context);
        this.context = context;
    }

    public Realm getRealmObject() {
        return realm;
    }

    /**
     * addQuranList
     *
     * @return
     */
    public void addQuranList(final ArrayList<Quran> arrayList) {
        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(arrayList);
        });
    }


    /**
     * getAllQuranList
     *
     * @return
     */
    public RealmList<Quran> getAllQuranList() {
        RealmList<Quran> quranRealmList = new RealmList<>();
        try {
            RealmResults<Quran> realmResults = realm.where(Quran.class)
                    .findAll();

            quranRealmList.addAll(realmResults);
        } catch (Exception e) {
            Log.e(TAG, "getAllQuranList: " + e);
        }

        return quranRealmList;
    }


}
