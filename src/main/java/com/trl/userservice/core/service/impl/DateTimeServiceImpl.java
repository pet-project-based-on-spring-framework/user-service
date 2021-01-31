package com.trl.userservice.core.service.impl;

import com.trl.userservice.core.service.DateTimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DateTimeServiceImpl implements DateTimeService {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @Override
    public Date toDate(LocalDateTime timestamp) {
        return Date.from(timestamp.atZone(ZoneId.systemDefault()).toInstant());
    }

}
