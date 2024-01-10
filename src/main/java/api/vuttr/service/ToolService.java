package api.vuttr.service;

import api.vuttr.data.ToolRecord;
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

    public List<ToolRecord> findAll() {
        var entityList = repository.findAll();
        return entityList.stream().map(e -> new ToolRecord(e.getId(), e.getTitle(), e.getDescription(), e.getUrl(), e.getTags())).toList();
    }

    public ToolRecord findById(Long id) throws ChangeSetPersister.NotFoundException {
        var entity = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return new ToolRecord(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getUrl(),
                entity.getTags()
        );
    }

    public ToolRecord create(ToolRecord tool) throws ChangeSetPersister.NotFoundException {
        var entity = new Tool(tool.title(), tool.description(), tool.url(), tool.tags());
        repository.save(entity);
        return findById(entity.getId());
    }

    public ToolRecord update(ToolRecord tool) throws ChangeSetPersister.NotFoundException {
        var updated = repository.findById(tool.id()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        updated.setTitle(tool.title());
        updated.setDescription(tool.description());
        updated.setUrl(tool.url());
        updated.setTags(tool.tags());
        var entity = repository.save(updated);
        return new ToolRecord(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getUrl(), entity.getTags());
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        var tool = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        repository.delete(tool);
    }
}
