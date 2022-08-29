package com.bsg.upm.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class SystemService extends BaseService {

    public Date getCurrentSqlDateTime() {
        return systemDAO.getCurrentSqlDateTime();
    }

}
