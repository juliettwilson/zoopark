package org.example.zoopark.repository;

import org.example.zoopark.entity.Keeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeeperRepository extends JpaRepository<Keeper, Long> {
}
