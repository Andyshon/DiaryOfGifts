package com.andyshon.diaryofgifts;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by andyshon on 18.07.18.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface GiftModelDao {

    @Query("select * from GiftModel")
    LiveData<List<GiftModel>> getAllGifts();

    @Query("select * from GiftModel where id = :id")
    LiveData<GiftModel> getGiftById(String id);

    @Insert(onConflict = REPLACE)
    void addGift(GiftModel giftModel);

    @Delete
    void deleteGift(GiftModel giftModel);

}
