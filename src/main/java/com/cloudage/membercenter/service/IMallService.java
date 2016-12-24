package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;

public interface IMallService {
	Mall save(Mall mall);
	Mall ishavashop(User user);
	Mall getMall(Integer userid);

}
