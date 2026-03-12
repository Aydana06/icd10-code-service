package mn.edu.num.icd10_service.domain;

import java.util.List;

public class ICD10Wrapper {

	private List<ICD10Code> codes;

	public List<ICD10Code> getCodes() {
		return codes;
	}

	public void setCodes(List<ICD10Code> codes) {
		this.codes = codes;
	}
}
