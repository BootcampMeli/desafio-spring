package com.mercadolivre.socialmeli.response;

import java.time.LocalDate;

public class ErrorMessage {
    private LocalDate date;
    private String msg;

    public ErrorMessage(){}

    public ErrorMessage(LocalDate date, String msg) {
        this.date = date;
        this.msg = msg;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
