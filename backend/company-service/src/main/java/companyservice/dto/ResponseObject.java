package companyservice.dto;

import lombok.Data;

@Data
public class ResponseObject {

    public String message;
    public boolean status;
    public String error;
}