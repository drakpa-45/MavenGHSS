package com.gov.edu.gelephuHSS.GHSS.admin.engineer;

import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.GHSS.engineer.EngineerService;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * Created by User on 1/2/2020.
 */

@Controller
@RequestMapping(value = "/processEngineer")
public class EngineerProcessController extends BaseController {

    @Autowired
    private EngineerService engineerService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model,
                        Integer applicationNo) {
        if (Objects.isNull(applicationNo)) {
            model.addAttribute("taskList", engineerService.getTaskList());
            return "admin/engineerTaskList";
        } else {
            String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
            String cmnService = "599fbfdc-a250-11e4-b4d2-080027dcfac6";
            String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
            String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
            String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";

            model.addAttribute("serviceForList", commonService.gCmnListItem(cmnService));
            model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
            model.addAttribute("countryList", commonService.gCountryList());
            model.addAttribute("tradeList", commonService.gCmnListItem(trade));
            model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
            model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
            model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
            return "engineer/EngineerProcess";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getEngineerList", method = RequestMethod.GET)
    public List getEngineerList(String applicationNo) {
        return engineerService.getEngineerList(applicationNo);
    }

    @ResponseBody
    @RequestMapping(value = "/getGewogList", method = RequestMethod.GET)
    public ResponseMessage getGewogList(String dzongkhagId) {
        return commonService.getGewogList(dzongkhagId);
    }

    @ResponseBody
    @RequestMapping(value = "/getVillageList", method = RequestMethod.GET)
    public ResponseMessage getVillageList(String gewogId) {
        return commonService.getVillageList(gewogId);
    }

    @ResponseBody
    @RequestMapping(value = "/verifyApplication", method = RequestMethod.POST)
    public ResponseMessage verifyApplication(HttpServletRequest request, String remark, Integer applicationNo) {
        loggedInUser = gLoggedInUser(request);
        return engineerService.verifyApplication(applicationNo, loggedInUser, remark);
    }

    @ResponseBody
    @RequestMapping(value = "/approveApplication", method = RequestMethod.POST)
    public ResponseMessage approveApplication(HttpServletRequest request, String remark, Integer applicationNo) {
        loggedInUser = gLoggedInUser(request);
        return engineerService.approveApplication(applicationNo, loggedInUser, remark);
    }

    @ResponseBody
    @RequestMapping(value = "/rejectApplication", method = RequestMethod.POST)
    public ResponseMessage rejectApplication(HttpServletRequest request, String remark, Integer applicationNo) {
        loggedInUser = gLoggedInUser(request);
        return engineerService.rejectApplication(applicationNo, loggedInUser, remark);
    }


    @ResponseBody
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseMessage download(HttpServletRequest request, HttpServletResponse response,
                                    String documentPath) {
       /* String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");
        Path file = Paths.get(dataDirectory, fileName);*/
//        return engineerService.download(request,response, documentPath);
        return null;
    }
}
