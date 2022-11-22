package com.cognologix.banksystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse implements Serializable {
    private String message;
//    private boolean success;

//    public BaseResponse(final boolean success){
//        this.success = success;
//    }
//    public boolean getSuccess(){
//        return success;
//    }
}
