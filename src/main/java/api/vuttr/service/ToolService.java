package api.vuttr.service;

import api.vuttr.model.Tool;
import api.vuttr.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    @Autowired
    private ToolRepository repository;

    public List<Tool> findAll() {
        return repository.findAll();
    }
}
