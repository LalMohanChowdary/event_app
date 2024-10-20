package org.jsp.eventapp.serviceimpl;

import java.io.File;
import java.io.IOException;

import org.jsp.eventapp.DAO.ProfileDao;
import org.jsp.eventapp.entity.Profile;
import org.jsp.eventapp.responsestructure.ResponseStructure;
import org.jsp.eventapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileServiceImpl implements ProfileService {

	private static final String folder_path = "C:\\Users\\sake.anand\\OneDrive\\Desktop\\event_profile\\";

	@Autowired
	private ProfileDao profileDao;

	@Override
	public ResponseEntity<?> saveProfile(MultipartFile file) {

		String name = file.getOriginalFilename();
		String type = file.getContentType();
		String path = folder_path + name;

		try {
			file.transferTo(new File(path));

		} catch (IOException e) {
			e.printStackTrace();
		}

		Profile p = Profile.builder().name(name).type(type).path(path).build();

		Profile dbprofile = profileDao.saveProfile(p);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder()
				.status(HttpStatus.OK.value())
				.message("Profile Img Saved")
				.body(dbprofile).build());
	}

}
