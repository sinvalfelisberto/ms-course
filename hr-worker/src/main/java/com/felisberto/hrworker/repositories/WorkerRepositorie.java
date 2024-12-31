package com.felisberto.hrworker.repositories;

import com.felisberto.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepositorie extends JpaRepository<Worker, Long> {
}
