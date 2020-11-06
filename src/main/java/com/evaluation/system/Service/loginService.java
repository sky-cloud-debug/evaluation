package com.evaluation.system.Service;

import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

public interface loginService {
    public boolean login(String username,String password,RedirectAttributesModelMap model);
}
