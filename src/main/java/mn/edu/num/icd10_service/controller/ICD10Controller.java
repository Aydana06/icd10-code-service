package mn.edu.num.icd10_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mn.edu.num.icd10_service.domain.ICD10Code;
import mn.edu.num.icd10_service.service.ICD10Service;

@RestController
@RequestMapping("/api/icd10")
public class ICD10Controller {

	private final ICD10Service service;

	public ICD10Controller(ICD10Service service) {
		this.service = service;
	}

	@GetMapping
	public List<ICD10Code> getAllCodes() {
		return service.getAllCodes();
	}

	@GetMapping("/{code}")
	public ICD10Code getByCode(@PathVariable String code) {
		return service.findByCode(code);
	}
}
