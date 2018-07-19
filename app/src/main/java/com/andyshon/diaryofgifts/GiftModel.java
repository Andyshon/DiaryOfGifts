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
    private Date borrowDate;

    public GiftModel(String giftName, String personName, Date borrowDate) {
        this.giftName = giftName;
        this.personName = personName;
        this.borrowDate = borrowDate;
    }

    public String getGiftName() {
        return giftName;
    }

    public String getPersonName() {
        return personName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
}
