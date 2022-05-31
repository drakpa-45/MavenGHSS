/** EngineerDao **/
EngineerDao.getTaskList = SELECT a.ReferenceNo applicationNo, '1' serviceNo,'Registration of Engineer' serviceName,a.`MobileNo` contactNo,b.`Name` AS appStatus,a.`ApplicationDate` applicationDate FROM `crpengineer` a INNER JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId` ORDER BY a.ReferenceNo ASC
EngineerDao.verifyApplication = UPDATE 	crpengineer SET SysVerifierUserId =:verifiedBy, VerifiedDate =:verifiedDate,RemarksByVerifier=:remark WHERE ReferenceNo=:applicationNo
EngineerDao.approveApplication = UPDATE crpengineer SET `SysApproverUserId` =:approvedBy,`RemarksByApprover`=:remark WHERE ReferenceNo=:applicationNo
EngineerDao.rejectApplication = UPDATE 	crpengineer SET `SysRejectorUserId` =:rejectedBy,`RemarksByRejector`=:remark,`RejectedDate`=:rejectedDate,`SysRejectionCode`=:rejectedCode WHERE ReferenceNo=:applicationNo
EngineerDao.getMaxId = SELECT  A.ReferenceNo FROM crpengineer A ORDER BY A.ReferenceNo DESC LIMIT 1

EngineerDao.getFeeStructure = SELECT cc.`Id` AS id,cc.`Name` AS name,cc.`Code` AS code,cc.`RegistrationFee` AS registrationFee,cc.`RenewalFee` AS renewalFee FROM `cmncontractorclassification` cc where (:category is null or cc.Code =:category) ORDER BY referenceNo ASC
EngineerDao.getEngineerList = SELECT a.id,b.CmnServiceTypeId serviceTypeId, \
a.CmnCountryId countryId, \
a.CmnServiceSectorTypeId serviceSectorType, \
a.CmnTradeId trade, \
a.CmnSalutationId salutation, \
a.CmnDzongkhagId dzongkhagId, \
a.Gewog gewog, \
a.Village village, \
a.CIDNo cid, \
a.Name name, \
a.Email email, \
a.mobileNo mobileNo, \
a.EmployerName employeeName, \
a.EmployerAddress employeeAddress, \
a.CmnQualificationId qualificationId, \
a.GraduationYear graduationYear, \
a.NameOfUniversity universityName, \
a.CMnUniversityCountryId universityCountry, \
a.ApplicationDate applicationDate, \
cl.Name appStatus, \
da.DocumentName documentName, \
da.DocumentPath documentPath, \
cc.Name countryText, \
cd.NameEn dzongkhagText, \
cg.Gewog_Name gewogText, \
cv.Village_Name villageText, \
uc.Name universityCountryText, \
sal.Name salutationText, \
sec.Name typeText, \
td.Name tradeText, \
q.Name qualificationText, \
st.Name serviceTypeText \
FROM crpengineer a INNER JOIN crpengineerappliedservice b ON a.id= b.CrpEngineerId \
LEFT JOIN crpengineerattachment da ON a.CrpEngineerId = da.CrpEngineerId \
inner join cmnlistitem cl on cl.id=a.CmnApplicationRegistrationStatusId \
INNER JOIN cmncountry cc ON a.CmnCountryId =cc.Id \
INNER JOIN cmndzongkhag cd ON a.CmnDzongkhagId = cd.Id \
INNER JOIN cmngewog cg ON a.Gewog = cg.Gewog_Id \
INNER JOIN cmnvillage cv ON a.Village = cv.Village_Id \
INNER JOIN cmncountry uc ON a.CmnUniversityCountryId = uc.Id \
INNER JOIN cmnlistitem sal ON a.CmnSalutationId = sal.Id \
INNER JOIN cmnlistitem sec ON a.CmnServiceSectorTypeId = sec.Id \
INNER JOIN cmnlistitem td ON a.CmnTradeId = td.Id \
INNER JOIN cmnlistitem q ON a.CmnQualificationId = q.Id \
INNER JOIN cmnlistitem st ON b.CmnServiceTypeId=st.id \
WHERE  a.ReferenceNo =:applicationNo

EngineerDao.isEmailUnique = SELECT * FROM crpengineer A WHERE A.Email=:email
