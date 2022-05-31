package com.gov.edu.gelephuHSS.GHSS.admin.engineer;

import com.gov.edu.gelephuHSS.base.BaseController;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import com.gov.edu.gelephuHSS.GHSS.engineer.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by User on 1/2/2020.
 */

@Controller
@RequestMapping(value = "/admin/engineer")
public class EngineerActionController extends BaseController {

    @Autowired
    private EngineerService engineerService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("taskList", engineerService.getTaskList());
        return "admin/engineer_tasklist";
    }

    @RequestMapping(value = "/action", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response, BigInteger appNo,
                        String serviceType) {
        model.addAttribute("appNo", appNo);
        model.addAttribute("serviceType", serviceType);
        return "admin/engineer_action";
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

    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public void view(HttpServletRequest request,HttpServletResponse response,
                     String documentPath,String documentName) throws IOException {

        File file = new File(documentPath);
        byte[] bFile = Files.readAllBytes(file.toPath());
        if(file.exists())
            try {
                commonService.downloadFile(bFile, documentName, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
//        commonService.viewDownloadFile(tableName, filterCol, filterVal, response);
    }
}
