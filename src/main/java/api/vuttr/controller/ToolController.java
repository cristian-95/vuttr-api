package api.vuttr.controller;

import api.vuttr.data.vo.ToolVO;
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
    public ResponseEntity<List<ToolVO>> findAll() {
        return ResponseEntity.ok(service.findAllTools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolVO> findById(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.findToolById(id));
    }

    @PostMapping
    public ResponseEntity<ToolVO> create(@RequestBody ToolVO tool) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.createTool(tool));

    }

    @PutMapping
    public ResponseEntity<ToolVO> update(@RequestBody ToolVO tool) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.updateTool(tool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        service.deleteTool(id);
        return ResponseEntity.noContent().build();
    }
}
