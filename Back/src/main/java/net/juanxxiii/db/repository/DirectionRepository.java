package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Direction;
import net.juanxxiii.dto.ClienteCompletoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Integer> {
}
