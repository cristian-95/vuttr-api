package api.vuttr.utils.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ToolMapper {
    private static final ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseList(List<O> origin, Class<D> destination) {
        List<D> parsedList = new ArrayList<>();
        for (O o : origin) parsedList.add(mapper.map(o, destination));
        return parsedList;
    }
}
