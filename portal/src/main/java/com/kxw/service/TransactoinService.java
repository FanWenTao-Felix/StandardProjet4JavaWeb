package com.kxw.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kxw on 2015/9/30.
 */

@Transactional(rollbackFor = {RuntimeException.class ,Exception.class},propagation = Propagation.REQUIRED)
public interface TransactoinService {
}
