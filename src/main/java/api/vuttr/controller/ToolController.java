package api.vuttr.controller;

import api.vuttr.data.vo.ToolVO;
import api.vuttr.service.ToolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ToolVO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findToolById(id));
    }

    @PostMapping
    public ResponseEntity<ToolVO> create(@Valid @RequestBody ToolVO tool) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTool(tool));

    }

    @PutMapping
    public ResponseEntity<ToolVO> update(@Valid @RequestBody ToolVO tool) {
        return ResponseEntity.ok(service.updateTool(tool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deleteTool(id);
        return ResponseEntity.noContent().build();
    }
}
