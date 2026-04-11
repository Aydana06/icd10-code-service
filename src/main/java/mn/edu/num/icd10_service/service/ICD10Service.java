package mn.edu.num.icd10_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mn.edu.num.icd10_service.domain.Chapter;
import mn.edu.num.icd10_service.domain.Subcode;
import mn.edu.num.icd10_service.repository.ICD10Repository;

@Service
public class ICD10Service {

	private final ICD10Repository repository;

	public ICD10Service(ICD10Repository repository) {
		this.repository = repository;
	}

	public List<Chapter> getAllCodes() {
		return repository.findAll();
	}

	public Chapter findByCode(String code) {
		return repository.findAll().stream().filter(c -> c.chapter().equals(code)).findFirst().orElse(null);
	}
}