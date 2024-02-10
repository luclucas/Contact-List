package com.luluog.contact_list.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "contact_name") var name: String,
    @ColumnInfo(name = "contact_phone") var phoneNumber: String,
    @ColumnInfo(name = "contact_email") var email: String
)