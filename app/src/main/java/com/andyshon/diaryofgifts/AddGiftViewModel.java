package com.andyshon.diaryofgifts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

/**
 * Created by andyshon on 18.07.18.
 */


public class AddGiftViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddGiftViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

    }

    public void addBorrow(final GiftModel giftModel) {
        new addAsyncTask(appDatabase).execute(giftModel);
    }

    private static class addAsyncTask extends AsyncTask<GiftModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final GiftModel... params) {
            db.itemAndPersonModel().addBorrow(params[0]);
            return null;
        }

    }
}
