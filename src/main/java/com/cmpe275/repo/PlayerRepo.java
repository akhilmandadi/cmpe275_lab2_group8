package com.cmpe275.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.entity.Player;
import com.cmpe275.entity.Sponsor;

import java.util.Optional;

@Transactional
@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
	
    public Optional<Player> getById(long id);

    public Optional<Player> getPlayerByEmail(String email);
    
	public Optional<Player> findByEmail(String parameter);
}
