package com.gabriel.usuario.infraestruture.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExistException extends RuntimeException {

    public ProblemDetail toProblem(){
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pb.setTitle("Error: User Already Exists");
        pb.setDetail("A template details for users already exists error usually happens when you try to create a new user template or configuration with a name or identifier that is already registered in the system.");
        return pb;
    }
}
