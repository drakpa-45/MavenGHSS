package com.gov.edu.gelephuHSS.GHSS.schoolPage.controller;

import com.gov.edu.gelephuHSS.GHSS.schoolPage.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by USER on 5/10/2020.
 */
@Controller
@RequestMapping("/public_view")
public class SchoolDeailsController {
    @Autowired
    SchoolService service;

    @RequestMapping(value = "/scprofile")
    public String redirectToPage( ModelMap model) {
        return "/public/schoolProfile";
    }

    @RequestMapping(value = "/staffdtls")
    public String staffdetails( ModelMap model, @RequestParam("type") String type) {
        model.addAttribute("App_Details", service.populateStaffDetails(type));
        return "/public/staff_profile";
    }

    @RequestMapping(value = "/result")
    public String result( ModelMap model) {
        return "/public/result";
    }

    @RequestMapping(value = "/admissionform")
      public String admissionform( ModelMap model) {
        return "/public/admissionForm";
    }

    @RequestMapping(value = "/download")
    public String downloads( ModelMap model) {
        return "/public/downloadPastPapers";
    }

}
