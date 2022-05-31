package com.gov.edu.gelephuHSS.GHSS.pUser;

import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ==================================================================================
 * Created by user on 3/12/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/public/locationChange")
public class LocationChangeController extends BaseController {


    @Autowired
    private CommonService commonService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        return "public/other_services_location";
    }
}
