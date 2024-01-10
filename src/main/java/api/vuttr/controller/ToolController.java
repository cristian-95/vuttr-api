package api.vuttr.controller;

import api.vuttr.data.ToolRecord;
import api.vuttr.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolController {


    @Autowired
    private ToolService service;

    @GetMapping
    public ResponseEntity<List<ToolRecord>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolRecord> findById(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ToolRecord> create(@RequestBody ToolRecord tool) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.create(tool));

    }

    @PutMapping
    public ResponseEntity<ToolRecord> update(@RequestBody ToolRecord tool) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.update(tool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
