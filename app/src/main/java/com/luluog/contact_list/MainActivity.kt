package com.luluog.contact_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.luluog.contact_list.db.ContactListDatabase
import com.luluog.contact_list.db.repository.ContactListRepository
import com.luluog.contact_list.model.Contact
import com.luluog.contact_list.R
import com.luluog.contact_list.adapter.ContactAdapter
import com.luluog.contact_list.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var db: ContactListDatabase
    private lateinit var adapter: ContactAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        db = ContactListDatabase.getInstance(this)

        val repository = ContactListRepository(db.contactDao())

        viewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        binding.lifecycleOwner = this

        viewModel.allContacts.observe(this){
            Log.d("Teste", "$it")
            initRecyclerView(it)
        }

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this,  ContactEditor::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.getAllContacts()
    }

    private fun initRecyclerView(list: List<Contact>){
        adapter = ContactAdapter(list)
        binding.rvContacts.adapter = adapter
        binding.rvContacts.layoutManager = LinearLayoutManager(this)

    }
}