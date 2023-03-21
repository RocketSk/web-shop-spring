package com.training.service;

import com.training.model.Good;

import java.sql.SQLException;
import java.util.List;

public interface GoodService {

    Good getById(Integer id) throws SQLException;

    List<Good> getAll() throws SQLException;

}
