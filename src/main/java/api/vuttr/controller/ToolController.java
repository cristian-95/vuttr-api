package api.vuttr.controller;

import api.vuttr.model.Tool;
import api.vuttr.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
