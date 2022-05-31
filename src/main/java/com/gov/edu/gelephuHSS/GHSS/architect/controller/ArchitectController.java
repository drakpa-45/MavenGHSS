package com.gov.edu.gelephuHSS.GHSS.architect.controller;

import bt.gov.ditt.sso.client.SSOClientConstants;
import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.gov.edu.gelephuHSS.auth.LoginDTO;
import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import com.gov.edu.gelephuHSS.GHSS.architect.dto.ArchitectDto;
import com.gov.edu.gelephuHSS.GHSS.architect.entity.ArchitectDocument;
import com.gov.edu.gelephuHSS.GHSS.architect.services.ArchitectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

/**
 * Created by USER on 3/19/2020.
 */
@Controller
@RequestMapping("")
public class ArchitectController extends BaseController {
    @Autowired
    private ArchitectServices services;

    @Autowired
    private CommonService commonService;
    //@RequestMapping(value = "/public/architectIndex")
    @RequestMapping(value = "/architectIndex")
    public String redirectToPage( ModelMap model) {
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";

        model.addAttribute("fee_details", services.getFeesDetals("Architect"));
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("tradeList", commonService.gCmnListItem(trade));
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
        model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
        model.addAttribute("undertaking", commonService.getundertaking("Architect_Registration"));
        return "/architect/architectIndex";
    }

    @ResponseBody
    @RequestMapping(value = "/architects/getPersonalInfo", method = RequestMethod.GET)
    public ResponseMessage getPersonalInfo(String cid) {
        try{
            ResponseMessage personal=commonService.getPersonalInfo(cid);
            return personal;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }
    @RequestMapping(value = "/public_access/emptylayout/saveArchitect",method = RequestMethod.POST)
    public String saveArchitect(ArchitectDto dto,@RequestParam("files") MultipartFile[] files, HttpServletRequest request,ModelMap model) {
        UserSessionDetailDTO user = (UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);
        try{
            ResponseMessage personal=services.saveArchitect(dto,user);
            ArchitectDto resdto=(ArchitectDto)personal.getDto();
            String cid="";
            if(user==null){
                cid=resdto.getCidNo();
            }
            else{
                cid=user.getCid();
            }

            if(personal.getStatus()==1){
                services.saveDoc(files,resdto.getCrpArchitectId(),"RegistrationOfArchitect",null);
                personal.setResponseText("Your application for <label class='control-label'>Registration Of Architecture</label> has been submitted and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");
            }
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Registration Of Architecture</label> has been submitted and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");
            return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            return  null;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/renewal"}, method = RequestMethod.GET)
    public String renewal(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        String type=request.getParameter("param");
        if(type.split("999")[0].equalsIgnoreCase("Architect")){
            model.addAttribute("cdbNo",type.split("999")[1]);
            model.addAttribute("fee_details", services.getFeesDetals("Architect"));
            model.addAttribute("checkOngoingApplication", services.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateApplicantDetails(type.split("999")[1]));
            return "architect/architect_renewal_index";
        }
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/cancel"}, method = RequestMethod.GET)
    public String cancel(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        String type=request.getParameter("param");
        if(type.split("999")[0].equalsIgnoreCase("Architect")){
            model.addAttribute("checkOngoingApplication", services.checkOngoingApplication(type.split("999")[1]));
            model.addAttribute("registrationDetails", commonService.populateApplicantDetails(type.split("999")[1]));
            return "architect/architect_cancellation_index";
        }
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/emptylayout/submitRenwalApplication"}, method = RequestMethod.POST)
    public String submitRenwalApplication(ArchitectDto dto,@RequestParam("files") MultipartFile[] files,ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginDTO loginDTO =(LoginDTO)session.getAttribute("loginDetails");
        try{
            ResponseMessage personal=services.saveArchitectRenenwal(dto, loginDTO.getUserId());
            ArchitectDto resdto=(ArchitectDto)personal.getDto();
            if(personal.getStatus()==1){
                services.saveDoc(files,resdto.getCrpArchitectId(),"RenewalOfArchitect",loginDTO.getUserId());
                personal.setResponseText("Your application for <label class='control-label'>Renewal Of Architecture</label> has been submitted and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            }
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Renewal Of Architecture</label> has been submitted and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            model.addAttribute("acknowledgement_message", "Not able to submit details. "+e+". Please try again");
            return "/architect/acknowledgement";
        }
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ={"/public_access/emptylayout/submitcancellation"}, method = RequestMethod.POST)
    public String submitcancellation(ArchitectDto dto,ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginDTO loginDTO =(LoginDTO)session.getAttribute("loginDetails");
        try{
            ResponseMessage personal=services.saveArchitectcancellation(dto, loginDTO.getUserId());
            ArchitectDto resdto=(ArchitectDto)personal.getDto();
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Cancellation Of Architecture</label> has been submitted and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number. <br /> Thank you.</label>");
            return "/architect/acknowledgement";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            model.addAttribute("acknowledgement_message", "Not able to submit details. "+e+". Please try again");
            return "/architect/acknowledgement";
        }
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/public_access/emptylayout/donwloadFiles",method = RequestMethod.GET)
    public String donwloadFiles(@RequestParam("type") String type,ArchitectDto dto,HttpServletRequest request,HttpServletResponse response,ModelMap model) {
        String uploadDocId = request.getParameter("uuid"),requesttype=request.getParameter("type");
        try{
            ArchitectDocument doc = services.getDocumentDetailsByDocId(uploadDocId);
            byte[] fileContent = downloadFile(doc.getDocumentPath());
            if(requesttype.equalsIgnoreCase("view")){
                if(doc.getDocumentName().substring(doc.getDocumentName().length()-3).equalsIgnoreCase("JPG")||doc.getDocumentName().substring(doc.getDocumentName().length()-4).equalsIgnoreCase("jpeg") || doc.getDocumentName().substring(doc.getDocumentName().length()-3).equalsIgnoreCase("png")){
                    response.setContentType("image/jpeg");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocumentName().substring(doc.getDocumentName().length()-3).equalsIgnoreCase("pdf")){
                    response.setContentType("APPLICATION/PDF");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocumentName().substring(doc.getDocumentName().length()-4).equalsIgnoreCase("docx")){
                    response.reset();
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Content-Disposition", "inline;filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocumentName().substring(doc.getDocumentName().length()-3).equalsIgnoreCase("xls")){
                    response.setContentType("APPLICATION/vnd.ms-excel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getDocumentName().substring(doc.getDocumentName().length()-4).equalsIgnoreCase("xlsx")){
                    response.setContentType("Application/x-msexcel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else{
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-disposition", "attachment; filename="+doc.getDocumentName());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
            }
            else{
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename="+doc.getDocumentName());
                response.getOutputStream().write(fileContent);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        }catch (Exception e){
            System.out.print(e);
            return  ""+e;
        }
        return null;
    }
    public static byte[] downloadFile(String uploadUlr) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(uploadUlr);
        return IOUtils.toByteArray(fileInputStream);
    }
}
