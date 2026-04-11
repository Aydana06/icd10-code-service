package mn.edu.num.icd10_service.domain;

import java.util.List;

public record Category(
		String range,
		String name, 
		List<Subcategory> subcategories) {
}
