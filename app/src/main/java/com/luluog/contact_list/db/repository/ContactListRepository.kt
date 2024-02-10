package com.luluog.contact_list.db.repository

import com.luluog.contact_list.db.daos.ContactDao
import com.luluog.contact_list.model.Contact

class ContactListRepository(private val contactDao: ContactDao) {

    suspend fun getAllContacts() = contactDao.getAllContacts()

    suspend fun upsertContact(contact: Contact) = contactDao.upsertContact(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)
}