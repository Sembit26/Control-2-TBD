package com.tbd.backend.Repository;

import com.tbd.backend.Entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}
