/*Common Dao*/
CommonDao.gCountryList = SELECT a.`Id`AS value, a.`Name` AS text FROM `cmncountry` a ORDER BY a.`Code` DESC, a.`Name` ASC
CommonDao.gDzongkhagList = SELECT a.`Id` AS value,CONCAT(a.`NameEn`,COALESCE(a.NameDz,'')) AS text FROM `cmndzongkhag` a
CommonDao.gCmnListItem = SELECT a.`Id` value,a.`Name` text FROM `cmnlistitem` a WHERE cmnlistid =:cmnListId
CommonDao.getGewogList = SELECT a.`Gewog_Id` AS value, CONCAT(a.`Gewog_Name` , '(',a.`Gewog_Name_Bh`,')') text FROM `cmngewog` a INNER JOIN `cmndzongkhag` b ON a.`Dzongkhag_Serial_No` = b.`Code`  WHERE b.`Id` =:dzongkhagId
CommonDao.getVillageList = SELECT a.`Village_Id` AS value, CONCAT(a.`Village_Name` , '(',a.`Village_Name_Bh`,')') text FROM `cmnvillage` a INNER JOIN `cmngewog` b ON a.`Gewog_Serial_No` = b.`Gewog_Serial_No`  WHERE b.`Gewog_Id` =:gewogId

CommonDao.getAttachmentDetail = SELECT a.`DocumentName` fileName,a.`DocumentPath` filePath,a.`FileType` fileType FROM :tableName a WHERE Id =:id