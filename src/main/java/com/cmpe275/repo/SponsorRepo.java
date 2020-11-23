
package com.cmpe275.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.entity.Player;
import com.cmpe275.entity.Sponsor;

import java.util.Optional;

/*
 * Sponser Repository Interface which includes all the method signatures for sponsors
 */
@Transactional
@Repository
public interface  SponsorRepo extends JpaRepository<Sponsor , Long> {
	
    public Optional<Sponsor> getById(long id);

	public Optional<Sponsor> findByName(String name);
}
