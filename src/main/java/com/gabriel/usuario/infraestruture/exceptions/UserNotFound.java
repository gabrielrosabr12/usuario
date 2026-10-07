package com.gabriel.usuario.infraestruture.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFound extends RuntimeException {

    public ProblemDetail toProblem(){
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("User Not Found");
        pb.setDetail("User not found or not exists in database");

        return pb;
    }


}
