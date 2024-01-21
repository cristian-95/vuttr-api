package api.vuttr.data.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToolVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;
    private String description;
    private String url;
    private List<String> tags;

    public ToolVO() {
        this.tags = new ArrayList<>();
    }

    public ToolVO(Long id, String title, String description, String url, List<String> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.tags = tags;
    }

    public ToolVO( String title, String description, String url, List<String> tags) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToolVO toolVO = (ToolVO) o;

        if (!Objects.equals(id, toolVO.id)) return false;
        if (!Objects.equals(title, toolVO.title)) return false;
        if (!Objects.equals(description, toolVO.description)) return false;
        if (!Objects.equals(url, toolVO.url)) return false;
        return Objects.equals(tags, toolVO.tags);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
