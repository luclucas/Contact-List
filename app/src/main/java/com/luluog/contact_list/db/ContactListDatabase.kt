package com.luluog.contact_list.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.luluog.contact_list.db.daos.ContactDao
import com.luluog.contact_list.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactListDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactListDatabase? = null

        fun getInstance(context: Context): ContactListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ContactListDatabase::class.java,
                    "contact_list_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}