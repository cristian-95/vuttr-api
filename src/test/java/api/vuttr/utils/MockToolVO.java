package api.vuttr.utils;

import api.vuttr.data.vo.ToolVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockToolVO {

    static Long ID = 1L;
    static String TITLE = "Intellij";
    static String DESCRIPTION = "O principal IDE para Java e Kotlin";
    static String URL = "www.jetbrains.com/idea";
    static List<String> TAGS = Arrays.asList("ide", "development", "java");

    public static ToolVO mockToolVOEntity() {
        var tool = new ToolVO(TITLE, DESCRIPTION, URL, TAGS);
        tool.setId(ID);
        return tool;
    }

    public static List<ToolVO> mockToolVOEntityList() {
        List<ToolVO> toolList = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            var tool = new ToolVO(TITLE, DESCRIPTION, URL, TAGS);
            tool.setId(i);
            toolList.add(tool);
        }
        return toolList;
    }

}
