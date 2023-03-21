package com.training.service.impl;

import com.training.DAO.GoodDAO;
import com.training.DAO.impl.GoodDaoImpl;
import com.training.model.Good;
import com.training.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    GoodDaoImpl dao;

    @Autowired
    public GoodServiceImpl(GoodDaoImpl dao){
        this.dao = dao;
    }

    @Override
    public Good getById(Integer id) throws SQLException {
        return dao.getById(id);
    }

    @Override
    public List<Good> getAll() throws SQLException {
        return dao.getAll();
    }
}
