package com.rishabh.intricare

import android.app.Application
import com.rishabh.intricare.data.db.AppDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getDatabase(this)
    }
}