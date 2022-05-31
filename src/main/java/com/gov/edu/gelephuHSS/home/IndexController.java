package com.gov.edu.gelephuHSS.home;

import com.gov.edu.gelephuHSS.auth.LoginDTO;
import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import com.gov.edu.gelephuHSS.lib.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value ="", method = RequestMethod.GET)
    public String index(ModelMap model,HttpServletRequest request, HttpServletResponse response) {return "index";
    }

    @RequestMapping(value ="/home", method = RequestMethod.GET)
    public String home(ModelMap model,HttpServletRequest request, HttpServletResponse response) {return "index";
    }

    @RequestMapping(value ={"/sc_dtls"}, method = RequestMethod.GET)
    public String RedirectToPage(ModelMap model,HttpServletRequest request, HttpServletResponse response) {return "public/schoolProfile";
    }

    @PreAuthorize("isAuthenticated()")
	@RequestMapping(value ={"/admin"}, method = RequestMethod.GET)
	public String index_admin(ModelMap model,HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();

        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setUserID(loginDTO.getUserId());
        loggedInUser.setUserName(loginDTO.getUsername());
        loggedInUser.setFullName(loginDTO.getFullName());
        loggedInUser.setProfileId(loginDTO.getProfileId());
        loggedInUser.setServerDate(new Date());
        model.addAttribute("orgName", "Construction Development Board");
        String registrationtype="";
        //loggedInUser = gLoggedInUser(request);
        if(request.isUserInRole("ROLE_APPROVER")) {
            registrationtype= ApplicationStatus.VERIFIED.getCode();
        }else if(request.isUserInRole("ROLE_VERIFIER")){
            registrationtype=ApplicationStatus.UNDER_PROCESS.getCode();

        }else if(request.isUserInRole("ROLE_PAYMENT")){
            registrationtype=ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
        }
        request.setAttribute("architect_Count", commonService.populateCount("crparchitect",registrationtype));
        request.getSession().setAttribute("loggedInUser", loggedInUser);
        if(request.isUserInRole("ROLE_PUBLIC")) {
            return "redirect:/public_access";
        }
        return "index_admin";
	}
    @PreAuthorize("isAuthenticated()")
	@RequestMapping(value ={"/public_access"}, method = RequestMethod.GET)
	public String index_public(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();
        String cdbdet=commonService.getCdbNo(loginDTO);
        session.setAttribute("App_Details", cdbdet);
        model.addAttribute("newsAndNotifications", commonService.getdashboardDetails(cdbdet.split("999")[0]));
        model.addAttribute("rejectedApplications", commonService.populaterejectedApplications(cdbdet));
        if(cdbdet.split("999")[0].equalsIgnoreCase("Architect")){
            model.addAttribute("registrationDetails", commonService.populateApplicantDetails(cdbdet.split("999")[1]));
        }
        session.setAttribute("loginDetails", loginDTO);
        return "index_public";

	}

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/profile"}, method = RequestMethod.GET)
    public String profile(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String type=request.getParameter("param");
        if(type.split("999")[0].equalsIgnoreCase("Architect")){
            model.addAttribute("registrationDetails", commonService.populateApplicantDetails(type.split("999")[1]));
            model.addAttribute("App_Details", commonService.populateApplicantDetails(type.split("999")[1]));
            return "architect/architect_profile";

        }
        return null;
    }

}