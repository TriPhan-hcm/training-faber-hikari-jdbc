package com.faber.airmgr.data.repositories.impl;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class BaseRepositoryImpl<TEntity> {


    protected java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

}
