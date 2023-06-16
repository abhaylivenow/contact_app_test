package com.example.dataentryapptest

import android.app.Dialog
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var imagePreviewProfile : ImageView
    private lateinit var phoneNumber : EditText
    private lateinit var name : EditText
    private lateinit var btnAddEntry : Button
    private lateinit var fabAddEntry: FloatingActionButton
    val listOfEntries = mutableListOf<Entry>()
    private lateinit var dialog: Dialog
    private lateinit var entryAdapter: EntryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)
        fabAddEntry = findViewById(R.id.fab_add_entry)

        entryAdapter = EntryAdapter(listOfEntries)
        rv.adapter = entryAdapter
        rv.layoutManager = LinearLayoutManager(this)

        fabAddEntry.setOnClickListener {
            showDialog()
        }
    }
    private fun showDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialog)
        name = dialog.findViewById(R.id.edt_name)
        phoneNumber = dialog.findViewById(R.id.edt_phone_number)
        imagePreviewProfile = dialog.findViewById(R.id.profile_image)
        val btnChooseImage: Button = dialog.findViewById(R.id.btn_choose_image)
        btnAddEntry = dialog.findViewById(R.id.btn_add_entry)

        btnChooseImage.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 101)
        }

        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == RESULT_OK) {
            imagePreviewProfile.visibility = View.VISIBLE
            imagePreviewProfile.setImageURI(data?.data)

            btnAddEntry.setOnClickListener {
                val name = name.text.toString()
                val phoneNumber = phoneNumber.text.toString()
                val imageRes = data?.data

                val entry = Entry(
                    name = name,
                    phoneNumber = phoneNumber,
                    profileImage = imageRes!!
                )
                listOfEntries.add(entry)
                entryAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
    }
}