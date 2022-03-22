package com.example.stlukesmedicalcentre.Hospital;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Doctor.class},version = 1,exportSchema = false)
public abstract class Doc_Database extends RoomDatabase {

    public abstract doc_dao vet_dao();

    private static volatile Doc_Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static Doc_Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Doc_Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Doc_Database.class, "word_database")
                            .createFromAsset("HospitalDatabase/data.sqlite")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                doc_dao dao = INSTANCE.vet_dao();
                dao.deleteAll();

                Doctor vd = new Doctor(10000,"baal","baal","baal");
                dao.insert(vd);

            });
        }
    };
}
