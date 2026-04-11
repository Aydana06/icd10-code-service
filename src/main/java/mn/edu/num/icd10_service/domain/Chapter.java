package mn.edu.num.icd10_service.domain;

import java.util.List;

public record Chapter(
		String chapter,
		String name,
		String range,
		List<Category> categories) {
}