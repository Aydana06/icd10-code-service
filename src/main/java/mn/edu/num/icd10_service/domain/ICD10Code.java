package mn.edu.num.icd10_service.domain;

public class ICD10Code {

	private String code;
	private String name;
	private String detail;

	public ICD10Code() {
	}

	public ICD10Code(String code, String name, String detail) {
		this.code = code;
		this.name = name;
		this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}