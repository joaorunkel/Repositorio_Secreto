package com.confradestech.waterly.di

import android.app.Application
import com.confradestech.waterly.utilites.ConnectivityChecker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //region utilites
    @Provides
    @Singleton
    fun connectivityChecker(application: Application): ConnectivityChecker =
        ConnectivityChecker(application)


    @Provides
    @Singleton
    fun fireStore(): FirebaseFirestore = Firebase.firestore
    //endregion utilites

}
