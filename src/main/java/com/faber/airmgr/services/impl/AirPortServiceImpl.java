package com.faber.airmgr.services.impl;

import com.faber.airmgr.data.entities.AirPortEntity;
import com.faber.airmgr.data.repositories.impl.AirPortRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
@Service
public class AirPortServiceImpl {

    @Autowired
    private AirPortRepositoryImpl airPortRepository;

    public List<AirPortEntity> findAll() {
        try {
            return airPortRepository.findAll();
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
    @Cacheable("ports")
    public List<AirPortEntity> findById(Long id) {
        try {
            return airPortRepository.findById(id);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
