package com.ajb.secureapp.validator;

import com.ajb.secureapp.dto.SignUpRequest;
import com.ajb.secureapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@RequiredArgsConstructor
@Component
public class SignUpRequestValidator implements Validator {

    final private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return SignUpRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        SignUpRequest user = (SignUpRequest) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 40) {
            errors.rejectValue("username", "Length");
        }
        if (userService.userExists(user.getUsername())) {
            errors.rejectValue("username", "Exists");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 16) {
            errors.rejectValue("password", "Length");
        }
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff");
        }
    }
}
