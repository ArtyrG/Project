package authdemo.model.impl;

import authdemo.hibernate.entity.UserEntity;
import authdemo.model.DAO;

public class DAOimlStub implements DAO {

	@Override
	public String getPassword(String name) {
		return "admin";
	}

	@Override
	public String getName(String name) {
		
		
		
		return null;
	}

}
