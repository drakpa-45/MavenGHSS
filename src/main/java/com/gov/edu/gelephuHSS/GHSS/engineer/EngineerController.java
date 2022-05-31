package com.gov.edu.gelephuHSS.GHSS.engineer;

import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import com.gov.edu.gelephuHSS.GHSS.engineer.dto.EngineerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by User on 12/17/2019.
 */
@Controller
//@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/public/engineer")
public class EngineerController extends BaseController {

    @Autowired
    private EngineerService engineerService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";

        //TODO need to do again.
        //model.addAttribute("feeStructureList", engineerService.getFeeStructure(null));
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("tradeList", commonService.gCmnListItem(trade));
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
        return "engineer/engineer";
    }

    @ResponseBody
    @RequestMapping(value = "/getPersonalInfo", method = RequestMethod.GET)
    public ResponseMessage getPersonalInfo(String cid) {
        return commonService.getPersonalInfo(cid);
    }

    @ResponseBody
    @RequestMapping(value ="/isEmailUnique", method = RequestMethod.GET)
    public Boolean isEmailUnique(String email){
        //return engineerService.isEmailUnique(email);
        return null;
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request, EngineerDTO engineerDTO) {
        loggedInUser = gLoggedInUser(request);
        loggedInUser.setUserName("need to change");//TODO need to remove
        loggedInUser.setServerDate(new Date());//TODO need to remove
        return engineerService.save(engineerDTO, loggedInUser);
    }
}
