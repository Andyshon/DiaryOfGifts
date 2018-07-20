package com.andyshon.diaryofgifts;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by andyshon on 18.07.18.
 */

@Entity
public class GiftModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String giftName;
    private String personName;
    @TypeConverters(DateConverter.class)
    private Date giftDate;

    public GiftModel(int id, String giftName, String personName, Date giftDate) {
        this.id = id;
        this.giftName = giftName;
        this.personName = personName;
        this.giftDate = giftDate;
    }

    public String getGiftName() {
        return giftName;
    }

    public String getPersonName() {
        return personName;
    }

    public Date getGiftDate() {
        return giftDate;
    }
}
