package mn.edu.num.icd10_service.exception;

public class ICD10NotFoundException extends RuntimeException{
    public ICD10NotFoundException(String code) {
        super("ICD10 chapter олдсонгүй: " + code);
    }
}
