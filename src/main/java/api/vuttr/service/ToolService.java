package api.vuttr.service;

import api.vuttr.model.Tool;
import api.vuttr.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    @Autowired
    private ToolRepository repository;

    public List<Tool> findAll() {
        return repository.findAll();
    }

    public Tool findById(Long id) throws ChangeSetPersister.NotFoundException {
        return repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public Tool create(Tool tool) {
        return repository.save(tool);
    }

    public Tool update(Tool tool) throws ChangeSetPersister.NotFoundException {
        var updated = repository.findById(tool.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        updated.setTitle(tool.getTitle());
        updated.setDescription(tool.getDescription());
        updated.setUrl(tool.getUrl());
        updated.setTags(tool.getTags());

        return repository.save(updated);
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        var tool = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        repository.delete(tool);
    }
}
