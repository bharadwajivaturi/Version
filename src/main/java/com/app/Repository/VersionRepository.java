package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.app.Entity.Version;

import jakarta.transaction.Transactional;

public interface VersionRepository extends JpaRepository<Version, Long>
{
	Version findByVersion(Double version);
	
	@Transactional
	@Modifying
	void deleteByVersion(Double version);
	boolean existsByVersion(Double version);
}