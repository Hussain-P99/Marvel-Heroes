/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hsn.marvelheroes.data.local.dao.CharacterDao
import com.hsn.marvelheroes.data.local.typeconverter.Converters
import com.hsn.marvelheroes.dto.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val characterDao: CharacterDao

    companion object {
        private lateinit var appDatabase: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                if (!this::appDatabase.isInitialized) {
                    appDatabase =
                        Room.databaseBuilder(context, AppDatabase::class.java, "Marvels Database")
                            .build()
                }
                return appDatabase
            }

        }
    }
}