LoginDao.login = SELECT id AS userId, username AS username, password,status,FullName as fullName FROM sysuser WHERE username =:username
LoginDao.getUserRoleList = SELECT b.`SysUserId` userId, b.`SysRoleId` roleId,c.`Name`roleName, c.`ReferenceNo` roleRefNo FROM `sysuserrolemap` b INNER JOIN `sysrole` c ON c.`Id` = b.`SysRoleId` where b.`SysUserId` =:userId
