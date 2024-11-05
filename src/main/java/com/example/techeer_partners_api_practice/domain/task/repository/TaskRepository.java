package com.example.techeer_partners_api_practice.domain.task.repository;

import com.example.techeer_partners_api_practice.domain.task.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByIsDone(boolean b);
}