package com.gov.edu.gelephuHSS.base;

import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ====================================================================
 * Created by nimayoezer on 21/08/2018.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@Service
public class BaseService {
    protected static final int SUCCESSFUL_STATUS = 1;
    protected static final int UNSUCCESSFUL_STATUS =0;
    protected static final int TRUE_INT = 1;
    protected static final int FALSE_INT = 0;
    protected static final char CMD_FLAG_CREATE = 'C';
    protected static final char CMD_FLAG_MODIFY = 'M';
    protected static final char CMD_FLAG_DELETE = 'D';
    protected static final String MSG_DEFAULT_UNSUCCESSFUL= "There is some problem. Please logout and try again.";
    @Autowired
    protected ResponseMessage responseMessage;


}
