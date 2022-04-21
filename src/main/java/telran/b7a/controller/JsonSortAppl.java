package telran.b7a.controller;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSortAppl {

	public static void main(String[] args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonData = objectMapper.readValue(new URL("file:target/BA.json"), TreeMap.class);
//		jsonData.entrySet().forEach(System.out::println);
		sortField(jsonData);
		objectMapper.writeValue(new File("target/BA_sorted.json"), jsonData);

	}

	@SuppressWarnings("unchecked")
	private static void sortField(Map<String, Object> jsonData) {
		Set<Entry<String, Object>> entries = jsonData.entrySet();
		entries.forEach(el -> {
			if (el.getValue() instanceof Map<?, ?>) {
				el.setValue(new TreeMap<String, Object>((Map<String, Object>) el.getValue()));
			}
			if (el.getValue() instanceof List<?>) {
				List<?> list = (List<?>) el.getValue();
				list.forEach(elem -> sortField((Map<String, Object>) elem));
			}
		}

		);
//		for (Entry<String, Object> entry : entries) {
//			if (entry.getValue() instanceof Map<?, ?>) {
//				entry.setValue(new TreeMap<String, Object>((Map<String, Object>) entry.getValue()));
//			}
//			if (entry.getValue() instanceof List<?>) {
//				List<?> list = (List<?>) entry.getValue();
//				if (list.size() > 0) {
//					list.forEach(e -> sortField((Map<String, Object>)e));
//				}
//			}
//			
//		}
	}

}
