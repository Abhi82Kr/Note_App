package com.example.mvvmnote

import android.app.Application


// Notes App
class Notes :Application(){
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}