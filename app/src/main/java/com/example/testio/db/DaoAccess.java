package com.example.testio.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface DaoAccess {
    @Insert
    void insertServer(Server server);

    @Query("SELECT * FROM Server")
    List<Server> getAll();

    @Delete
    void delete(Server server);

    @Update
    void update(Server server);
}
