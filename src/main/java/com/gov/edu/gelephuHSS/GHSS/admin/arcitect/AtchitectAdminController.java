package com.gov.edu.gelephuHSS.GHSS.admin.arcitect;

import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.architect.dto.ArchitectDto;
import com.gov.edu.gelephuHSS.GHSS.architect.services.ArchitectServices;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by USER on 3/29/2020.
 */

@Controller
@RequestMapping(value = "/admin_architect")
public class AtchitectAdminController extends BaseController {
    @Autowired
    private ArchitectServices services;
   /* @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("taskList", services.getTaskList());
        return "admin/engineer_tasklist";
    }
    @RequestMapping(value = "/tasklist/",method = RequestMethod.GET)
    public String tasklist(Model model) {
        model.addAttribute("taskList", services.getTaskList());
        return "admin/engineer_tasklist";
    }*/
    @RequestMapping(value = "/architect_tasklist", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request) {
        loggedInUser = gLoggedInUser(request);
        String type=request.getParameter("param");
        if(type.equalsIgnoreCase("new")){
            type="55a922e1-cbbf-11e4-83fb-080027dcfac6";
        }
        if(type.equalsIgnoreCase("renew")){
            type="45bc628b-cbbe-11e4-83fb-080027dcfac6";
        }
        if(type.equalsIgnoreCase("cancellation")){
            type="acf4b324-cbbe-11e4-83fb-080027dcfac6";
        }
        if(request.isUserInRole("ROLE_APPROVER")) {
            model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.VERIFIED.getCode(), "Group", getLoggedInUser().getUserID(),type));
            model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.VERIFIED.getCode(),"mytask",getLoggedInUser().getUserID(),type));
        }else if(request.isUserInRole("ROLE_VERIFIER")){
            model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.UNDER_PROCESS.getCode(),"Group",getLoggedInUser().getUserID(),type));
            model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.UNDER_PROCESS.getCode(),"mytask",getLoggedInUser().getUserID(),type));

        }else if(request.isUserInRole("ROLE_PAYMENT")){
           model.addAttribute("groupTaskList", services.getTaskList(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode(), "Group", getLoggedInUser().getUserID(),type));
            model.addAttribute("myTaskList", services.getTaskList(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode(),"mytask",getLoggedInUser().getUserID(),type));
        }
       // model.addAttribute("myTaskList", contractorActionService.gMyTaskList(loggedInUser.getUserID()));
        return "admin/architect_tasklist";
    }

    @RequestMapping(value = "/emptylayout/openApplication", method = RequestMethod.GET)
    public String send2MyOrGroupTask(HttpServletRequest request,String appNo,String type,Model model) {
        services.assignMyTask(appNo, getLoggedInUser().getUserID());
        ArchitectDto dto=services.getArchitetDetails(appNo);
        model.addAttribute("appDetails", dto);
        if(dto.getServiceTypeId().equalsIgnoreCase("New Registration")){
            return "architect/architect_verification";
        }
        else if(dto.getServiceTypeId().equalsIgnoreCase("Renewal of CDB Certificate")){
            return "architect/architect_renewal_verification";
        }
        else if(dto.getServiceTypeId().equalsIgnoreCase("Cancellation of Registration")){
            return "architect/cancellation_verification";
        }
        else{
            return null;
        }
    }

    @RequestMapping(value = "/emptylayout/updatereject", method = RequestMethod.GET)
    public String updatereject(HttpServletRequest request,ArchitectDto dto,Model model){
        dto=services.updateReject(dto, getLoggedInUser().getUserID(),request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have rejected this application due to <b>"+dto.getRemarks()+"</b>. Please check status from view status with application number: <b>"+dto.getReferenceNo()+"</b></div>");
        }
        else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to reject this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/verifyArchitectRegistration", method = RequestMethod.GET)
    public String verifyArchitectRegistration(HttpServletRequest request,ArchitectDto dto,Model model){
        dto=services.updateVerification(dto, getLoggedInUser().getUserID(), request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have verified application with application number: <b>"+dto.getReferenceNo()+"</b>. The application has been forwarded for further approval. Thank you</div>");
        }
        else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to verify this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/approveArchitectRegistration", method = RequestMethod.GET)
    public String approveArchitectRegistration(HttpServletRequest request,ArchitectDto dto,Model model){
        dto.setServiceTypeId(request.getParameter("servicefor"));
        dto=services.approveArchitectRegistration(dto, getLoggedInUser().getUserID(), request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(request.getParameter("servicefor").equalsIgnoreCase("cancel")){
                model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved application for the cancellation of certificate with application number: <b>"+dto.getReferenceNo()+"</b>. The details are updated and certificate is cancelled. Thank you</div>");
            }
            else{
                model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved application with application number: <b>"+dto.getReferenceNo()+"</b>. The application has been forwarded for further payment approval. Thank you</div>");
            }
        }
        else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to approve this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/emptylayout/approveAndGenerateCertificate", method = RequestMethod.POST)
    public String approveAndGenerateCertificate(HttpServletRequest request,ArchitectDto dto,Model model){
        dto.setServiceTypeId(request.getParameter("servicefor"));
        dto=services.approveAndGenerateCertificate(dto, getLoggedInUser().getUserID(), request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-info col-12 text-center'>You have approved payment for application : <b>"+dto.getReferenceNo()+"</b>. You may print certificate and issue. Thank you</div>");
        }
        else{
            model.addAttribute("acknowledgement_message", "<br /><div class='alert alert-danger col-12 text-center'>Not able to approve this application. "+dto.getUpdateStatus()+" Please try again</div>");
        }
        return "/architect/acknowledgement";
    }

    @RequestMapping(value = "/architect_prin_Certificate", method = RequestMethod.GET)
    public String architect_prin_Certificate(ModelMap model, HttpServletRequest request) {
        model.addAttribute("printList", services.getPrintList());
        return "architect/architectPrint";
    }

    @RequestMapping(value = "/emptylayout/printarchitectInfo", method = RequestMethod.GET)
    public String printarchitectInfo(HttpServletRequest request,ArchitectDto dto,Model model){
        return "jasperreport";
    }


}

