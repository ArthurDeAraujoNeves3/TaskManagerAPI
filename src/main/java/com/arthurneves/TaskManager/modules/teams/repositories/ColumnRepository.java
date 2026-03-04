package com.arthurneves.TaskManager.modules.teams.repositories;

import com.arthurneves.TaskManager.modules.teams.entities.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.ByteOrder;
import java.util.Optional;
import java.util.UUID;

public interface ColumnRepository extends JpaRepository<ColumnEntity, UUID> {
    @Modifying
    @Query("update Columns c set c.columnOrder = c.columnOrder + 1 where c.columnOrder >= :columnOrder")
    void reorderColumns(@Param("columnOrder") Byte columnOrder);

    Optional<ColumnEntity> findByColumnOrder(Byte columnOrder);
}
