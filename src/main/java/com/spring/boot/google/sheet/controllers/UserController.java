package com.spring.boot.google.sheet.controllers;

import com.spring.boot.google.sheet.commons.CommonUtils;
import com.spring.boot.google.sheet.model.User;
import com.spring.boot.google.sheet.model.UserAndRole;
import com.spring.boot.google.sheet.service.SecurityService;
import com.spring.boot.google.sheet.service.UserService;
import com.spring.boot.google.sheet.service.impl.UserAndRoleImpl;
import com.spring.boot.google.sheet.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by FRAMGIA\dang.ngoc.thien on 08/11/2016.
 */
@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserAndRoleImpl userAndRoleImpl;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "user/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        logger.info(bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            return "user/registration";
        }
        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    /*
    * Mapping about to setting service for the system
    * */
    @PreAuthorize("hasAnyRole('Manager')")
    @RequestMapping(value = "/settingService", method = RequestMethod.GET)
    public String settingService(Model model){
        model.addAttribute("listUser", userService.getListUser());
        model.addAttribute("listRole", userService.getListRole());
        model.addAttribute("currentUser", CommonUtils.getCurrentUserInfo());
        return "user/adminsetting";
    }
    /*
    * Mapping for setting service with mehthod POST
    * */
    @PreAuthorize("hasAnyRole('Manager')")
    @RequestMapping(value = "/processSettingService", method = RequestMethod.POST)
    public String settingServicePost(@ModelAttribute("userAndRole")UserAndRole userAndRole){
        logger.info("data insert database:"+userAndRole);
        try{
            userAndRoleImpl.save(userAndRole);
            return "success";
       }catch (Exception ex){
            logger.error("Process error"+ex.getMessage());
        }
        return "error";
    }

    @PreAuthorize("hasAnyRole('Manager')")
    @RequestMapping(value = "/updateSettingService", method = RequestMethod.POST)
    public String updateSettingService(@ModelAttribute("userAndRole")UserAndRole userAndRole){
        logger.info("data update database:"+userAndRole);
        try{
            userAndRoleImpl.updateRole(userAndRole);
            return "success";
        }catch (Exception ex){
            logger.error("Process error"+ex.getMessage());
        }
        return "error";
    }

    @PreAuthorize("hasAnyRole('Manager')")
    @RequestMapping(value = "/remoteSettingService", method = RequestMethod.POST)
    public String remoteSettingService(@ModelAttribute("userAndRole")UserAndRole userAndRole){
        logger.info("data remove database:"+userAndRole);
        try{
            userAndRoleImpl.deleteRole(userAndRole);
            return "success";
        }catch (Exception ex){
            logger.error("Process error"+ex.getMessage());
        }
        return "error";
    }



}
