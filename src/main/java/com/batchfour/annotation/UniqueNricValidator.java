package com.batchfour.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.batchfour.repository.PatientRepository;

@Component
public class UniqueNricValidator implements ConstraintValidator<UniqueNric, String>{


    @Autowired
    private PatientRepository ptRepo;
    
    @Override
    public void initialize(UniqueNric constraintAnnotation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isValid(String patientNric, ConstraintValidatorContext context) {

        if(ptRepo==null) {
            return true;
        }
        return ptRepo.findByPatientNric(patientNric)==null;
    }

//    public boolean isValid(String patientNric, ConstraintValidatorContext context) {
//        // TODO Auto-generated method stub
//        if(ptRepo==null) {
//            return true;
//        }
//        return ptRepo.findByPatientNric(patientNric)==null;
//    }
//
//    public boolean supports(Class<?> clazz) {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    public void validate(Object target, Errors errors) {
//        // TODO Auto-generated method stub
//        Patient patient = (Patient) target;
//        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patientNric", "NotEmpty");
//        if (patient.getPatientNric().length() > 10) {
//            errors.rejectValue("patientNric", "Size.patient.patientNric");
//        }
//        if (ptRepo.findByPatientNric(patient.getPatientNric()) != null) {
//            errors.rejectValue("username", "Duplicate.patient.patientNric");
//        }
//        
//    }








}
