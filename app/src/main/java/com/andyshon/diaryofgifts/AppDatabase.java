package com.andyshon.diaryofgifts;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by andyshon on 18.07.18.
 */

@Database(entities = {GiftModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "gift_db").build();
        }
        return INSTANCE;
    }

    public abstract GiftModelDao itemAndPersonModel();

}
