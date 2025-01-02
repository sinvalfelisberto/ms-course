package com.felisberto.hrworker.resources;

import com.felisberto.hrworker.entities.Worker;
import com.felisberto.hrworker.repositories.WorkerRepositorie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResources {

    private static Logger logger = LoggerFactory.getLogger(WorkerResources.class);

    @Autowired
    private Environment environment;

    @Autowired
    private WorkerRepositorie workerRepositorie;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = workerRepositorie.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        logger.info("PORT= " + environment.getProperty("local.server.port"));
        var worker = workerRepositorie.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //WorkerResponseDTO wDto = new WorkerResponseDTO(worker.getName(), worker.getDailyIncome());
        return ResponseEntity.ok(worker);
    }
}
