package com.debug.dominators.services;

import java.sql.SQLException;

import com.debug.dominators.dao.ISeverityLevelDao;
import com.debug.dominators.dao.SeverityLevelDaoImpl;
import com.debug.dominators.model.SeverityLevel;

public class SeverityLevelServiceImpl implements ISeverityLevelService {

    private ISeverityLevelDao severityLevelDao;
    
    public SeverityLevelServiceImpl() throws ClassNotFoundException, SQLException {
        severityLevelDao = SeverityLevelDaoImpl.getSeverityLevelDao();
    }

    /**
     * Adds a severity level to the system.
     *
     * @param severityLevel The severity level to add.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void addSeverityLevel(SeverityLevel severityLevel) throws SQLException {
        severityLevelDao.addSeverityLevel(severityLevel);
    }
}
