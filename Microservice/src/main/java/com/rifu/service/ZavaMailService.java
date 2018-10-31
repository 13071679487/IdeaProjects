package com.rifu.service;

import com.rifu.common.ResponseEntity;
import com.rifu.entity.ZavaMail;

/**
 * @Author Rifu
 * @Date 2018/10/27  16:52
 */
public interface ZavaMailService {
    ResponseEntity sendMail(ZavaMail zavaMail);
    ResponseEntity sendMailSuccess(String to);
}
