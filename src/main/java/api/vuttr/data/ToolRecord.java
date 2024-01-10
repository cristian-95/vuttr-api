package api.vuttr.data;

import java.util.List;

public record ToolRecord(Long id, String title, String description, String url, List<String> tags) {}
