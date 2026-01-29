package com.example.RevPlay.DAO;

import java.sql.Connection;

import com.example.RevPlay.config.DBConnection;

public abstract class BaseDAO {
    protected Connection connection;

    public BaseDAO() {
        this.connection = DBConnection.getInstance();
    }
}

