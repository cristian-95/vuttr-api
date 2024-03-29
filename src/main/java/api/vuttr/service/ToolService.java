package api.vuttr.service;

import api.vuttr.controller.ToolController;
import api.vuttr.data.vo.ToolVO;
import api.vuttr.exception.MissingFieldsFromInputException;
import api.vuttr.exception.ResourceNotFoundException;
import api.vuttr.model.Tool;
import api.vuttr.repository.ToolRepository;
import api.vuttr.utils.mapper.ToolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ToolService {

    private final static Logger logger = Logger.getLogger(ToolController.class.getName());
    @Autowired
    private ToolRepository repository;


    public List<ToolVO> findAllTools() {
        logger.info("Finding all tools in the database");

        var entityList = repository.findAll();

        List<ToolVO> voList = ToolMapper.parseList(entityList, ToolVO.class);
        voList.forEach((vo -> vo.add(linkTo(methodOn(ToolController.class).findById(vo.getId())).withSelfRel())));
        return voList;
    }

    public ToolVO findToolById(Long id) throws ResourceNotFoundException {
        logger.info("Finding a tool (id = " + id + ")");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tool not found (id = " + id + ")"));

        var vo = ToolMapper.parseObject(entity, ToolVO.class);
        vo.add(linkTo(methodOn(ToolController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public ToolVO createTool(ToolVO toolVO) {
        logger.info("Creating a tool");

        validate(toolVO);
        var entity = ToolMapper.parseObject(toolVO, Tool.class);
        repository.save(entity);

        var vo = ToolMapper.parseObject(entity, ToolVO.class);
        vo.add(linkTo(methodOn(ToolController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public ToolVO updateTool(ToolVO tool) {
        logger.info("Updating a tool (id = " + tool.getId() + ")");

        var updated = repository.findById(tool.getId()).orElseThrow(() -> new ResourceNotFoundException("Tool not found (id = " + tool.getId() + ")"));
        updated.setTitle(tool.getTitle());
        updated.setDescription(tool.getDescription());
        updated.setUrl(tool.getUrl());
        updated.setTags(tool.getTags());
        var entity = repository.save(updated);

        var vo = ToolMapper.parseObject(entity, ToolVO.class);
        vo.add(linkTo(methodOn(ToolController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public void deleteTool(Long id) {
        logger.info("Deleting a tool (id = " + id + ")");

        var tool = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tool not found (id = " + id + ")"));

        repository.delete(tool);
    }

    private static void validate(ToolVO toolVO) {
        if (toolVO.getTitle() == null || toolVO.getDescription() == null || toolVO.getUrl() == null) {
            List<String> fields = new ArrayList<>();
            if (toolVO.getTitle() == null) fields.add("title");
            if (toolVO.getDescription() == null) fields.add("description");
            if (toolVO.getUrl() == null) fields.add("url");
            throw new MissingFieldsFromInputException("Missing required field(s): " + fields);
        }
    }
}
