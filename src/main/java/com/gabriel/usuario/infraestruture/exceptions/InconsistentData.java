package com.gabriel.usuario.infraestruture.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InconsistentData extends RuntimeException {
    private String details;
    private String title;

    public InconsistentData(String details, String title){
        this.details = details;
        this.title = title;
    }


    public ProblemDetail toProblem() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);

        pb.setTitle(this.title);
        pb.setDetail(this.details);

        return pb;
    }
}
