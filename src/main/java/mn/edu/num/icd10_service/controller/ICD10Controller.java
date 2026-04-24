package mn.edu.num.icd10_service.controller;

import mn.edu.num.icd10_service.domain.Chapter;
import mn.edu.num.icd10_service.service.ICD10Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/icd10")
public class ICD10Controller {

    private final ICD10Service service;

    public ICD10Controller(ICD10Service service) {
        this.service = service;
    }

    /**
     * Бүх ICD-10 жагсаалт.
     * GET /api/icd10
     */
    @GetMapping
    public List<Chapter> getAllCodes() {
        return service.getAllCodes();
    }

    /**
     * Тодорхой chapter-г код-оор хайна.
     * GET /api/icd10/{code}
     * @param code chapter код — жишээ: "I"
     */
    @GetMapping("/{code}")
    public Chapter getByCode(@PathVariable String code) {
        return service.findByCode(code);
    }
}