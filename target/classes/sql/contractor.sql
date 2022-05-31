
/*Contractor Dao*/
ContractorDao.gFeeStructure = SELECT cc.`Id` AS id,cc.`Name` AS name,cc.`Code` AS code,cc.`RegistrationFee` AS registrationFee,cc.`RenewalFee` AS renewalFee FROM `cmncontractorclassification` cc where (:category is null or cc.Id =:category) ORDER BY referenceNo ASC
ContractorDao.gContractCategory = SELECT wc.Id AS id, wc.`Name` AS name,wc.`Code` AS code FROM `cmncontractorworkcategory` wc ORDER BY referenceNo ASC
ContractorDao.gClassification = SELECT a.Id AS value, CONCAT(a.Code,':',a.Name) text FROM `cmncontractorclassification` a
ContractorDao.gEquipment = SELECT a.`Id` AS value,a.`Name` AS text,a.IsRegistered as obj1  FROM `cmnequipment` a
ContractorDao.isEmailUnique = SELECT a.Email FROM `crpcontractor` a WHERE a.Email =:email
ContractorDao.getTrainingDtl = SELECT a.`CmnTrainingTypeId` tTypeId,a.`CmnTrainingModuleId` tModuleId,tt.`Name` tType,tm.`Name` tModule,a.`TrainingFromDate` fromDate,a.`TrainingToDate` toDate,b.`CIDNo` cidNo,b.`Participant` participant FROM `crpcontractortraining` a INNER JOIN `crpcontractortrainingdetail` b ON a.`Id` = b.`CrpContractorTrainingId` INNER JOIN `cmnlistitem` tt ON tt.Id = a.`CmnTrainingTypeId` INNER JOIN `cmnlistitem` tm ON tm.Id = a.`CmnTrainingModuleId` WHERE b.`CIDNo` =:cidNo

/** ContractorActionDao */
ContractorActionDao.gGroupTaskList = SELECT a.`ReferenceNo` applicationNo, CONCAT(d.Name,' (Contractor)') serviceName, \
                    a.`NameOfFirm` firmName,a.`MobileNo` contactNo, \
                    b.`Name` AS appStatus,a.`ApplicationDate` applicationDate FROM `crpcontractor` a   \
                    INNER JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId`  \
                    INNER JOIN `crpcontractorappliedservice` c ON c.`CrpContractorId` = a.`CrpContractorId` \
                    INNER JOIN `crpservice` d ON d.`Id` = c.`CmnServiceTypeId` \
                    WHERE a.`SysLockedByUserId` IS NULL and a.`CmnApplicationRegistrationStatusId` = :status ORDER BY a.`ReferenceNo` DESC

ContractorActionDao.gMyTaskList = SELECT a.`ReferenceNo` applicationNo, CONCAT(d.Name,' (Contractor)') serviceName, \
                    a.`NameOfFirm` firmName,a.`MobileNo` contactNo, \
                    b.`Name` AS appStatus,a.`ApplicationDate` applicationDate FROM `crpcontractor` a   \
                    INNER JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId`  \
                    INNER JOIN `crpcontractorappliedservice` c ON c.`CrpContractorId` = a.`CrpContractorId` \
                    INNER JOIN `crpservice` d ON d.`Id` = c.`CmnServiceTypeId` \
                    WHERE a.`SysLockedByUserId`=:userId ORDER BY a.`ReferenceNo` DESC

ContractorActionDao.getContractorHRs = SELECT hr.`Id` AS id,hr.`CrpContractorId` contractorID,hr.`CIDNo`cidNo,hr.`Name` AS name, hr.`Sex` sex,hr.`ShowInCertificate` AS siCertificate \
,hr.`IsPartnerOrOwner` isPartnerOrOwner,hr.`JoiningDate` joiningDate,sa.`Name` salutationName,co.`Name` countryName,qu.`Name` qualificationName,st.`Name` serviceTypeName,td.`Name` tradeName,de.`Name` designationName \
FROM `crpcontractorhumanresource` hr INNER JOIN `cmnlistitem` sa ON sa.`Id` = hr.`CmnSalutationId` INNER JOIN `cmncountry` co ON co.`Id` = hr.`CmnCountryId` LEFT JOIN `cmnlistitem` qu ON qu.`Id` = hr.`CmnQualificationId` \
LEFT JOIN `cmnlistitem` st ON st.`Id` = hr.`CmnServiceTypeId` LEFT JOIN `cmnlistitem` td ON td.`Id` = hr.`CmnTradeId` INNER JOIN `cmnlistitem` de ON de.`Id` = hr.`CmnDesignationId` WHERE hr.`CrpContractorId` =:contractorId

ContractorActionDao.getCategoryClass = SELECT cc.`Id` id,cc.`CmnProjectCategoryId` categoryId,CONCAT(wc.`Code`,'-',wc.`Name`) categoryName, cl.`Name` aClassName \
FROM `crpcontractorworkclassification` cc INNER JOIN `cmncontractorworkcategory` wc ON wc.`Id` = cc.`CmnProjectCategoryId` \
INNER JOIN `cmncontractorclassification` cl ON cl.`Id` = cc.`CmnAppliedClassificationId` WHERE cc.`CrpContractorId` =:contractorId

ContractorActionDao.getFeeCategoryClass = SELECT cc.`Id` id,cc.`CmnCategoryId` categoryId,CONCAT(wc.`Code`,'-',wc.`Name`) categoryName, cl.`Name` aClassName,cc.ApprovedAmount feeAmount \
FROM `crpcontractorregistrationpayment` cc INNER JOIN `cmncontractorworkcategory` wc ON wc.`Id` = cc.`CmnCategoryId`  \
INNER JOIN `cmncontractorclassification` cl ON cl.`Id` = cc.`CmnApprovedClassificationId` WHERE cc.`CrpContractorFinalId` =:contractorId

ContractorActionDao.getEquipment = SELECT ce.`Id` id, ce.`CrpContractorId` contractorId, eq.`Name` equipmentName,ce.`RegistrationNo` registrationNo,ce.`SerialNo` serialNo,ce.`Quantity` quantity,ce.`ModelNo` modelNo \
FROM `crpcontractorequipment` ce INNER JOIN `cmnequipment`  eq ON ce.`CmnEquipmentId` = eq.`Id`WHERE ce.`CrpContractorId` = :contractorId
INNER JOIN `cmncontractorclassification` cl ON cl.`Id` = cc.`CmnAppliedClassificationId` WHERE cc.`CrpContractorId` =:contractorId

ContractorActionDao.verify = CALL ProCrpContractorApplicationVerify(:contractorId,:vUserId,:vRemarks)
ContractorActionDao.approve = CALL ProCrpContractorApplicationApprove(:contractorId,:aUserId,:aRemarks)
ContractorActionDao.reject = UPDATE crpcontractor c SET c.SysRejecterUserId =:userId, \
		                          c.RejectedDate = CURDATE(), \
		                          c.RemarksByRejector = :remarks, \
                              c.RegistrationStatus = 3, \
		                          c.CmnApplicationRegistrationStatusId =:applicationStatusId \
	                          WHERE c.CrpContractorId=:contractorId
ContractorActionDao.paymentUpdate = CALL ProCrpContractorNewRegistrationFinalData(:contractorId,:userId,:appStatusId,:createdBy)

ContractorActionDao.getAppHistoryDtl = SELECT c.* , case v.`FullName` when NULL then '(Citizen)' else v.`FullName` end userName FROM ( SELECT 'Submitted' appStatus,c.`CrpContractorId` contractorId,c.`CreatedBy` userId,c.`CreatedOn` actionDate,''remarks FROM `crpcontractor` c UNION ALL SELECT 'Verified',c.`CrpContractorId`,c.`SysVerifierUserId`,c.`RegistrationVerifiedDate`,c.`RemarksByVerifier`FROM `crpcontractor` c UNION ALL SELECT 'Approved for Payment',c.`CrpContractorId`,c.`SysApproverUserId`,c.`RegistrationApprovedDate`,c.`RemarksByApprover` FROM `crpcontractor` c UNION ALL SELECT 'Rejected',c.`CrpContractorId`,c.`SysRejecterUserId`,c.`RejectedDate`,c.`RemarksByRejector` FROM `crpcontractor` c ) c LEFT JOIN `sysuser` v ON c.`userId` = v.`Id` WHERE c.`contractorId` =:contractorId AND c.actionDate IS NOT NULL
ContractorActionDao.send2MyOrGroupTask = UPDATE crpcontractor a SET a.`SysLockedByUserId` =:lockUserId  WHERE a.ReferenceNo =:appNo

ContractorActionDao.getNextCDBNo = SELECT DISTINCT a.`CDBNo`+1 AS cdbNo  FROM crpcontractorfinal a ORDER BY CONVERT(a.`CDBNo`, DECIMAL) DESC  LIMIT 1
