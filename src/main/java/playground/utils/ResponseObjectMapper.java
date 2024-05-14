package playground.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseObjectMapper {

	public static <T, R> R map(T source, Function<T, R> mappingFunction) {
		return mappingFunction.apply(source);
	}

	public static <T, R> List<R> mapList(List<T> list, Function<T, R> mapperFunction) {
		return list.stream().map(mapperFunction).collect(Collectors.toList());
	}

}
