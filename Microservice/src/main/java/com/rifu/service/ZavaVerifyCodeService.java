package com.rifu.service;

import com.rifu.common.ResponseEntity;

/**
 * @Author Rifu
 * @Date 2018/10/30  13:07
 */
public interface ZavaVerifyCodeService {
    ResponseEntity sendVerifyCode(String phone,String verifycode, Integer type);
}
