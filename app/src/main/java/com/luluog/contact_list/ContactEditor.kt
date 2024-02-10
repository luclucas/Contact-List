package com.luluog.contact_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.luluog.contact_list.databinding.ActivityContactEditorBinding
import com.luluog.contact_list.db.ContactListDatabase
import com.luluog.contact_list.db.repository.ContactListRepository
import com.luluog.contact_list.model.Contact

class ContactEditor : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityContactEditorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_editor)

        val db = ContactListDatabase.getInstance(this)
        val repository = ContactListRepository(db.contactDao())

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            val name = viewModel.newName.value
            val phone = viewModel.newPhone.value
            val email = viewModel.newEmail.value


            Log.d("teste", "button save clicked")

            if (!name.isNullOrEmpty() && !phone.isNullOrEmpty() && !email.isNullOrEmpty()) {
                Log.d("teste", "upsert completed")

                viewModel.upsertContact(Contact(0, name, phone, email))
                finish()
            }
        }

    }
}