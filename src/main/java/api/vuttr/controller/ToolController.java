package api.vuttr.controller;

import api.vuttr.model.Tool;
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
    public ResponseEntity<List<Tool>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> findById(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Tool> create(@RequestBody Tool tool) {
        return ResponseEntity.ok(service.create(tool));
    }

    @PutMapping
    public ResponseEntity<Tool> update(@RequestBody Tool tool) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.update(tool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
