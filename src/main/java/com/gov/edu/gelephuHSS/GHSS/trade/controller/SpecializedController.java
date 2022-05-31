package com.gov.edu.gelephuHSS.GHSS.trade.controller;

import bt.gov.ditt.sso.client.SSOClientConstants;
import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.GHSS.trade.dto.TradeDto;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.TradeDocument;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import com.gov.edu.gelephuHSS.GHSS.trade.dto.TradeFeesDto;
import com.gov.edu.gelephuHSS.GHSS.trade.service.SpecializedService;
import org.apache.commons.io.IOUtils;
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
import java.io.FileInputStream;

/**
 * Created by USER on 5/5/2020.
 */
@Controller
@RequestMapping("")
public class SpecializedController extends BaseController {

    @Autowired
    private SpecializedService services;
    @Autowired
    private CommonService commonService;

    //@RequestMapping(value = "/public/architectIndex")
    @RequestMapping(value = "/tradeIndex")
    public String redirectToPage( ModelMap model) {
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";

        model.addAttribute("fee_details", services.getFeesDetals("Specialized"));
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("tradeList", commonService.gCmnListItem(trade));
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
        model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
        //model.addAttribute("undertaking", commonService.getundertaking("Architect_Registration"));
        model.addAttribute("categoryList", services.category());
        return "/trade/tradeIndex";
    }

    @ResponseBody
    @RequestMapping(value = "/trade/getPersonalInfo", method = RequestMethod.GET)
    public ResponseMessage getPersonalInfo(String cid) {
        try{
            ResponseMessage personal=commonService.getPersonalInfo(cid);
            return personal;
        }catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }
    @RequestMapping(value = "/public_access/emptylayout/saveSpecializedTrade",method = RequestMethod.POST)
    public String saveSpecializedTrade(TradeDto dto,TradeFeesDto tradeFeesDto,@RequestParam("files") MultipartFile[] files, HttpServletRequest request,ModelMap model) {
        UserSessionDetailDTO user = (UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);
        try{
            ResponseMessage personal=services.saveSpecializedTrade(dto, user);
            TradeDto resdto=(TradeDto)personal.getDto();
            String cid="";
            if(user==null){
                cid=resdto.getCidNo();
            }
            else{
                cid=user.getCid();
            }

            if(personal.getStatus()==1) {
                String saveWrk = services.saveWrkClassification(dto,tradeFeesDto, null);
                if (saveWrk.equalsIgnoreCase("success")) {
                    services.saveDoc(files, resdto.getCrpSpecializedTradeId(), "RegistrationOfSpecializedTrade", null);
                    personal.setResponseText("Your application for <label class='control-label'>Registration Of SpecializedTrade</label> has been submitted and your application number is <b>" + resdto.getReferenceNo() + "</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");
                } else{
                    model.addAttribute("status","failed");
                    return null;
                }
            }
            model.addAttribute("cidDetails",services.fetchdtls(resdto));
            model.addAttribute("acknowledgement_message", "Your application for <label class='control-label'>Registration Of SpecializedTrade</label> has been submitted and your application number is <b>"+resdto.getReferenceNo()+"</b> <br><p>You will receive an email as well as sms notification once take further action.</p><label class='control-label'>You can track your application using above Application Number.</label>");
            return "/trade/tradePrintPage";
        }catch (Exception e){
            System.out.print(e);
            model.addAttribute("status","failed");
            return  null;
        }
        //return String.valueOf(model.addAttribute("status","failed"));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/public_access_trade/emptylayout/donwloadFiles",method = RequestMethod.GET)
    public String donwloadFiles(@RequestParam("type") String type,TradeDto dto,HttpServletRequest request,HttpServletResponse response,ModelMap model) {
        String uploadDocId = request.getParameter("uuid"),requesttype=request.getParameter("type");
        try{
            TradeDocument doc = services.getDocumentDetailsByDocId(uploadDocId);
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
