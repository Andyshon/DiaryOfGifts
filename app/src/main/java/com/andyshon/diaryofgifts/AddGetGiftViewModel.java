package com.andyshon.diaryofgifts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


/**
 * Created by andyshon on 18.07.18.
 */


public class AddGetGiftViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddGetGiftViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public LiveData<GiftModel> getGiftAndPersonById(String id) {
        return appDatabase.giftAndPersonModel().getGiftById(id);
    }


    public void addGift(final GiftModel giftModel) {
        new addAsyncTask(appDatabase).execute(giftModel);
    }

    private static class addAsyncTask extends AsyncTask<GiftModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final GiftModel... params) {
            db.giftAndPersonModel().addGift(params[0]);
            return null;
        }

    }
}
