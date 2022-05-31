package com.gov.edu.gelephuHSS.GHSS.admin.contractor;

import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * ==================================================================================
 * Created by user on 3/10/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin")
public class ContractorTaskListController extends BaseController {

    @Autowired
    private ContractorActionService contractorActionService;

    @RequestMapping(value = "/contractor", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request) {
        loggedInUser = gLoggedInUser(request);
        if(request.isUserInRole("ROLE_APPROVER")) {
            model.addAttribute("groupTaskList", contractorActionService.gGroupTaskList(ApplicationStatus.VERIFIED.getCode()));
        }else if(request.isUserInRole("ROLE_VERIFIER")){
            model.addAttribute("groupTaskList", contractorActionService.gGroupTaskList(ApplicationStatus.UNDER_PROCESS.getCode()));
        }else if(request.isUserInRole("ROLE_PAYMENT")){
            model.addAttribute("groupTaskList", contractorActionService.gGroupTaskList(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode()));
        }
        model.addAttribute("myTaskList", contractorActionService.gMyTaskList(loggedInUser.getUserID()));
        return "admin/contractor_tasklist";
    }

    @RequestMapping(value = "/contractor/send2MyOrGroupTask", method = RequestMethod.POST)
    public String send2MyOrGroupTask(HttpServletRequest request,String appNo,String flag) {
        contractorActionService.send2MyOrGroupTask(appNo,flag,getLoggedInUser().getUserID());
        return "redirect:/admin/contractor";
    }

    @RequestMapping(value = "/contractor/{appNo}", method = RequestMethod.GET)
    public RedirectView redirectToAction(RedirectAttributes attributes,@PathVariable String appNo) {
        attributes.addFlashAttribute("appNo", appNo);
        return new RedirectView("/admin/contractor/action");
    }
}
