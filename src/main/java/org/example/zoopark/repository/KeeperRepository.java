package org.example.zoopark.repository;

import org.example.zoopark.entity.Keeper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeeperRepository extends JpaRepository<Keeper, Long> {
}
