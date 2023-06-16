package com.example.dataentryapptest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntryAdapter(
    private val listOfEntries: List<Entry>
) : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {

    class EntryViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.text_name)
        val phoneNumber: TextView = itemView.findViewById(R.id.text_phone_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        return EntryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_entry, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.name.text = listOfEntries[position].name
        holder.phoneNumber.text = listOfEntries[position].phoneNumber
        holder.profileImage.setImageURI(listOfEntries[position].profileImage)
    }

    override fun getItemCount(): Int {
        return listOfEntries.size
    }
}