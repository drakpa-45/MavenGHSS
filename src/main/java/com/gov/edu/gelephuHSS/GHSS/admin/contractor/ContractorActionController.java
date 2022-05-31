package com.gov.edu.gelephuHSS.GHSS.admin.contractor;

import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.admin.dto.PaymentUpdateDTO;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin/contractor/action")
public class ContractorActionController extends BaseController {

    @Autowired
    private ContractorActionService contractorActionService;

    @Autowired
    private CommonService commonService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("appNo")String appNo) {
        if(appNo == null || appNo.isEmpty()){
            model.remove("appNo");
            return "redirect:/admin/contractor";
        }
        model.addAttribute("appNo", appNo);
        String appStatus = contractorActionService.getApplicationStatus(appNo);
        if(appStatus.equals(ApplicationStatus.APPROVED_FOR_PAYMENT.getCode())){
            return "admin/contractor_payment";
        }

        return "admin/contractor_action";
    }


    @ResponseBody
    @RequestMapping(value = "/getContractorInfo", method = RequestMethod.GET)
    public ResponseMessage getContractorInfo(HttpServletRequest request, BigInteger appNo,Character flag) {
        return contractorActionService.getContractorData(appNo,flag);
    }

    @ResponseBody
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseMessage verify(HttpServletRequest request, BigInteger appNo, String vRemarks) {
        loggedInUser = gLoggedInUser(request);
        return contractorActionService.verify(appNo, vRemarks, loggedInUser);
    }

    @RequestMapping(value = "/viewDownload", method = RequestMethod.GET)
    public void viewDownload(HttpServletRequest request, HttpServletResponse response, String filterVal,
                             String filterCol, String tableName) throws Exception{
        commonService.viewDownloadFile(tableName, filterCol, filterVal, response);
    }


    @ResponseBody
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ResponseMessage approve(HttpServletRequest request, BigInteger appNo, String remarks) {
        loggedInUser = gLoggedInUser(request);
        return contractorActionService.approve(appNo, remarks, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public ResponseMessage reject(HttpServletRequest request, BigInteger appNo, String remarks) {
        loggedInUser = gLoggedInUser(request);
        return contractorActionService.reject(appNo, remarks, loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/paymentUpdate", method = RequestMethod.POST)
    public ResponseMessage paymentUpdate(HttpServletRequest request, PaymentUpdateDTO paymentUpdateDTO) throws Exception{
        loggedInUser = gLoggedInUser(request);
        return contractorActionService.paymentUpdate(paymentUpdateDTO, loggedInUser);
    }

}
