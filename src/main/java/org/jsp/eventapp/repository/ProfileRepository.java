package org.jsp.eventapp.repository;

import org.jsp.eventapp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
 
}
