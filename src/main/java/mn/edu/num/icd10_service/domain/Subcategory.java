package mn.edu.num.icd10_service.domain;

import java.util.List;

public record Subcategory(String code, String name, List<Subcode> subcode) {
}
