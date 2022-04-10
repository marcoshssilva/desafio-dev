package br.com.marcoshssilva.desafiodev.models;

import java.util.Date;

public class ErrorModel {

    private Date timestamp;
    private Integer status;
    private String error;
    private String path;

    public ErrorModel(Integer status, String error, String path) {
        this.timestamp = new Date();

        this.error = error;
        this.path = path;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
