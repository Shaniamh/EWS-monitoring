package com.belajar.kp.service;

import java.util.List;


import com.belajar.kp.model.Data;
import com.belajar.kp.model.Parent;
import com.belajar.kp.model.Status;


public interface UserService {
	Parent findAllParent();
	List<Data> findAllData();
	void saveUser(Data data);
}
