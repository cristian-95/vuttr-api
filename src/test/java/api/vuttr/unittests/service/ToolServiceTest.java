package api.vuttr.unittests.service;

import api.vuttr.data.vo.ToolVO;
import api.vuttr.exception.ResourceNotFoundException;
import api.vuttr.model.Tool;
import api.vuttr.repository.ToolRepository;
import api.vuttr.service.ToolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static api.vuttr.utils.MockTool.mockToolEntity;
import static api.vuttr.utils.MockTool.mockToolEntityList;
import static api.vuttr.utils.MockToolVO.mockToolVOEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ToolServiceTest {

    @Mock
    ToolRepository repository;
    @InjectMocks
    @Autowired
    ToolService service;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a Tool list when findAllTools is called")
    void findAllTools() {
        when(repository.findAll()).thenReturn(mockToolEntityList());
        List<ToolVO> result = service.findAllTools();

        assertNotNull(result);
        var first = result.get(0);

        assertNotNull(first);
        assertEquals(0L, first.getId());
        assertEquals("Intellij", first.getTitle());
        assertEquals("O principal IDE para Java e Kotlin", first.getDescription());
        assertNotNull(first.getTags());
    }

    @Test
    @DisplayName("Should return a ToolVO when findToolById is called")
    void findToolByIdSuccess() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(mockToolEntity()));

        ToolVO result = service.findToolById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Intellij", result.getTitle());
        assertEquals("O principal IDE para Java e Kotlin", result.getDescription());
        assertNotNull(result.getTags());
    }

    @Test
    @DisplayName("Should return a ResourceNotFoundException when findToolById is called with wrong id")
    void findToolByIdError() {
        Long id = 1L;
        Long wrongID = 5L;
        when(repository.findById(id)).thenReturn(Optional.of(mockToolEntity()));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findToolById(wrongID);
        });
        assertEquals(exception.getMessage(), "Tool not found (id = " + wrongID + ")");
    }


    @Test
    @DisplayName("Should return a ToolVO when createTool is called")
    void createTool() {
        var newTool = mockToolVOEntity();
        when(repository.save(any())).thenReturn(mockToolEntity());

        ToolVO result = service.createTool(newTool);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Intellij", result.getTitle());
        assertEquals("O principal IDE para Java e Kotlin", result.getDescription());
        assertNotNull(result.getTags());
    }

    @Test
    @DisplayName("Should return an updated ToolVO when updateTool is called")
    void updateToolSuccess() {
        Long id = 1L;
        Tool validTool = mockToolEntity();

        when(repository.findById(id)).thenReturn(Optional.of(validTool));
        when(repository.save(any(Tool.class))).thenReturn(validTool);

        ToolVO toolToUpdate = service.findToolById(id);

        toolToUpdate.setDescription("New Intellij description");
        toolToUpdate.getTags().add("New Tag");

        ToolVO result = service.updateTool(toolToUpdate);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Intellij", result.getTitle());
        assertEquals("New Intellij description", result.getDescription());
        assertTrue(result.getTags().contains("New Tag"));
    }

    @Test
    @DisplayName("Should throw an exception when updateTool is called with an invalid ID")
    void updateToolException() {

        Long invalidId = 999L; // Um ID que não existe
        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        ToolVO toolToUpdate = new ToolVO();
        toolToUpdate.setId(invalidId);
        toolToUpdate.setDescription("New Intellij description");
        toolToUpdate.addTag("New Tag");

        assertThrows(ResourceNotFoundException.class, () -> service.updateTool(toolToUpdate));

        verify(repository, never()).save(any(Tool.class));
    }

    @Test
    @DisplayName("Should delete a ToolVO when deleteTool is called with a valid ID")
    void deleteToolSuccess() {
        Tool toolToDelete = mockToolEntity();
        Long idToDelete = toolToDelete.getId();
        when(repository.findById(idToDelete)).thenReturn(Optional.of(toolToDelete));

        service.deleteTool(idToDelete);

        verify(repository).delete(toolToDelete);
    }

    @Test
    @DisplayName("Shoud throw an exception when deleteTool is called with invalid ID")
    void deleteToolError() {
        Tool toolToDelete = mockToolEntity();
        Long idToDelete = toolToDelete.getId();
        Long wrongId = 1000L;

        when(repository.findById(idToDelete)).thenReturn(Optional.of(toolToDelete));

        assertThrows(ResourceNotFoundException.class, () -> service.deleteTool(wrongId));
        verify(repository, never()).delete(any(Tool.class));
    }
}
