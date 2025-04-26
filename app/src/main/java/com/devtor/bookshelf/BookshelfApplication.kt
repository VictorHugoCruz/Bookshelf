package com.devtor.bookshelf

import android.app.Application
import com.devtor.bookshelf.data.AppContainer
import com.devtor.bookshelf.data.DefaultContainer

class BookshelfApplication (): Application(){
    lateinit var container :AppContainer
    override fun onCreate() {
        super.onCreate()
        container=DefaultContainer()
    }
}