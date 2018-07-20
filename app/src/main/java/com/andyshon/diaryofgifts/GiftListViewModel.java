package com.andyshon.diaryofgifts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by andyshon on 18.07.18.
 */

public class GiftListViewModel extends AndroidViewModel {

    private final LiveData<List<GiftModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public GiftListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.giftAndPersonModel().getAllGifts();
    }


    public LiveData<List<GiftModel>> getGiftAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(GiftModel giftModel) {
        new deleteAsyncTask(appDatabase).execute(giftModel);
    }

    private static class deleteAsyncTask extends AsyncTask<GiftModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final GiftModel... params) {
            db.giftAndPersonModel().deleteGift(params[0]);
            return null;
        }

    }

}