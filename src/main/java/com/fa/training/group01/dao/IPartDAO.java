package com.fa.training.group01.dao;

import com.fa.training.group01.domain_model.Part;

public interface IPartDAO {
	Part save(Part part);
	
	Part update(Part part);
}
