package com.gov.edu.gelephuHSS.GHSS.contractor;

import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.ContractorDTO;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@RequestMapping("/public/contractor")
public class ContractorController extends BaseController {
    @Autowired
    private ContractorService contractorService;
    @Autowired
    private CommonService commonService;

	@RequestMapping(value ="", method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        String cmnOwnership = "08dada52-c651-11e4-b574-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String cmnDesignation = "599fbfdc-a250-11e4-b4d2-080027dcfac6";
        String cmnQualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
        String cmnTrade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnServiceType = "08dada52-c651-11e4-b574-080027dcfee6";
		model.addAttribute("countryList", commonService.gCountryList());
		model.addAttribute("ownershipList", commonService.gCmnListItem(cmnOwnership));
		model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
		model.addAttribute("designationList", commonService.gCmnListItem(cmnDesignation));
		model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
		model.addAttribute("feeStructureList", contractorService.gFeeStructure(null));
		model.addAttribute("categoryList", contractorService.gContractCategory());
		model.addAttribute("classification", contractorService.gClassification());
		model.addAttribute("qualificationList", commonService.gCmnListItem(cmnQualification));
		model.addAttribute("tradeList", commonService.gCmnListItem(cmnTrade));
		model.addAttribute("serviceTypeList", commonService.gCmnListItem(cmnServiceType));
		model.addAttribute("equipmentList", contractorService.gEquipment());

        return "contractor/contractor";
	}

    @ResponseBody
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request,ContractorDTO contractorDTO) throws Exception{
        loggedInUser = gLoggedInUser(request);
        return contractorService.save(contractorDTO,loggedInUser);
    }


    @ResponseBody
    @RequestMapping(value ="/getGewogList", method = RequestMethod.GET)
    public ResponseMessage getGewogList(String dzongkhagId){
        return commonService.getGewogList(dzongkhagId);
    }

    @ResponseBody
    @RequestMapping(value ="/getVillageList", method = RequestMethod.GET)
    public ResponseMessage getVillageList(String gewogId){
        return commonService.getVillageList(gewogId);
    }

    @ResponseBody
    @RequestMapping(value ="/getPersonalInfo", method = RequestMethod.GET)
    public ResponseMessage getPersonalInfo(String cidNo){
        return commonService.getPersonalInfo(cidNo);
    }

    @ResponseBody
    @RequestMapping(value ="/isEmailUnique", method = RequestMethod.GET)
    public Boolean isEmailUnique(String email){
        return contractorService.isEmailUnique(email);
    }

    @ResponseBody
    @RequestMapping(value ="/getTrainingDtl", method = RequestMethod.GET)
    public List getTrainingDtl(String cidNo){
        return contractorService.getTrainingDtl(cidNo);
    }


}