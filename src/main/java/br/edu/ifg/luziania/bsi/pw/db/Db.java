package br.edu.ifg.luziania.bsi.pw.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface Db {

    Connection getConnection();

    void closeStatement(Statement st);

    void closeResultSet(ResultSet rs);
}
