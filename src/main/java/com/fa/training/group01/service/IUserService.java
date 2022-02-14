package com.fa.training.group01.service;

import com.fa.training.group01.domain_model.User;
import com.fa.training.group01.security.AuthenciationToken;

public interface IUserService {
	User getByEmail(String email);

	User getByAuthenciation(AuthenciationToken authenciationToken);
}
