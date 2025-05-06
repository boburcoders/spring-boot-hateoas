package com.company.SpiringBootDataJpa.service;

import com.company.SpiringBootDataJpa.models.ToDos;
import com.company.SpiringBootDataJpa.repo.ToDosRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {
    private final ToDosRepo toDosRepo;

    public ResponseEntity<ToDos> createToDo(ToDos toDo) {
        return new ResponseEntity<>(toDosRepo.save(toDo), HttpStatus.CREATED);
    }

    public ResponseEntity<ToDos> getToDoById(Integer id) {
        return new ResponseEntity<>(this.toDosRepo.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<List<ToDos>> getAllToDos() {
        return new ResponseEntity<>(this.toDosRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<ToDos> updateToDo(ToDos toDo) {
        return new ResponseEntity<>(toDosRepo.save(toDo), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteToDoById(Integer id) {
        toDosRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
