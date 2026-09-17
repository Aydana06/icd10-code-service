package mn.edu.num.icd10_service.repository;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import mn.edu.num.icd10_service.domain.Chapter;
import mn.edu.num.icd10_service.domain.ICD10Wrapper;
import tools.jackson.databind.ObjectMapper;

@Repository
public class ICD10Repository {

	private List<Chapter> chapters;

	@PostConstruct 
	public void loadData(){
		try{
			ObjectMapper mapper = new ObjectMapper(); 
			try(InputStream is = new ClassPathResource("icd10_code.json").getInputStream()){
				ICD10Wrapper wrapper = mapper.readValue(is, ICD10Wrapper.class);
				this.chapters = wrapper.getChapters();
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to read ICD10 json", e);	
		}
	}

	public List<Chapter> findAll() {
		return chapters;
	}
}