package com.trl.userservice.core.service;

import java.time.LocalDateTime;
import java.util.Date;

public interface DateTimeService {

    LocalDateTime now();

    Date toDate(LocalDateTime timestamp);

}

