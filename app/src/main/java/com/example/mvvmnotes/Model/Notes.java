package com.example.mvvmnotes.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int notesID;

    @ColumnInfo(name = "Notes Title")
    public String notesTitle;

    @ColumnInfo(name = "Notes Subtitle")
    public String notesSubtitle;

    @ColumnInfo(name = "Notes Date")
    public String notesDate;

    @ColumnInfo(name="Notes Body")
    public String notesData;

    @ColumnInfo(name="Notes Priority")
    public String notesPriority;
}
