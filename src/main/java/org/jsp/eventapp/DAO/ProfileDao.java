package org.jsp.eventapp.DAO;

import java.util.List;
import java.util.Optional;

import org.jsp.eventapp.entity.Profile;

public interface ProfileDao {

	Profile saveProfile(Profile profile);
	
	Profile updateProfile(Profile profile);
	 
	Optional<Profile> findProfileById(int id);
	
	List<Profile> findAllProfiles();
	
	
}
