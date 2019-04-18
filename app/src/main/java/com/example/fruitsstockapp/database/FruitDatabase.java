package com.example.fruitsstockapp.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fruitsstockapp.model.Fruit;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Fruit.class}, version = 1)
public abstract class FruitDatabase extends RoomDatabase {

    private static FruitDatabase fruitDatabaseInstance;

    public abstract FruitDao fruitDao();

    public FruitDao fruitDao;

    public static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PrepulateDataAsyncTask(fruitDatabaseInstance).execute();
        }
    };

    public synchronized static FruitDatabase getInstance(Context context) {
        if(fruitDatabaseInstance == null) {

            fruitDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    FruitDatabase.class,
                    "fruit_database")
                    .addCallback(callback)
                    .build();
        }
        return fruitDatabaseInstance;
    }

    public static class PrepulateDataAsyncTask extends AsyncTask<Void, Void, Void> {

        FruitDao fruitDao;

        public PrepulateDataAsyncTask(FruitDatabase fruitDatabase) {
            fruitDao = fruitDatabase.fruitDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fruitDatabaseInstance.fruitDao().insertAll(Fruit.populateDatabase());
            return null;
        }
    }

}
