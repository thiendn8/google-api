package com.spring.boot.google.sheet.service;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
