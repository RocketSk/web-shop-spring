package com.training.DAO;

import com.training.model.Good;

import java.sql.SQLException;
import java.util.List;

public interface GoodDAO {

    List<Good> getAll() throws SQLException;

    Good getById(Integer id) throws SQLException;

}
