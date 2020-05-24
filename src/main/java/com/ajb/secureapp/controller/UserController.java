package com.ajb.secureapp.controller;

import com.ajb.secureapp.dto.SignUpRequest;
import com.ajb.secureapp.service.UserService;
import com.ajb.secureapp.validator.SignUpRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    final private UserService userService;
    final private SignUpRequestValidator signUpRequestValidator;

    @GetMapping("/registration")
    public String registration(SignUpRequest signUpRequest) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("signUpRequest") @NotNull @Valid SignUpRequest signUpRequest,
                           BindingResult result) {
        signUpRequestValidator.validate(signUpRequest, result);
        if (result.hasErrors()) {
            return "registration";
        }
        userService.register(signUpRequest);
        return "redirect:/user/registration?userCreated";
    }
}
