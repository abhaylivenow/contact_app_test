package com.example.dataentryapptest

import android.net.Uri

data class Entry(
    val profileImage: Uri,
    val name: String,
    val phoneNumber: String
)