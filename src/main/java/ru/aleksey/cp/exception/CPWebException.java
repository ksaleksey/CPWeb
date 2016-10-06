package ru.aleksey.cp.exception;

/**
 * Created by Алексей on 05.10.2016.
 */
public class CPWebException extends RuntimeException{
    private static final long serialVersionUID = 1L;



    private Exception innerException;
    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Exception getInnerException() {
        return innerException;
    }

    public void setInnerException(Exception innerException) {
        this.innerException = innerException;
    }

    public CPWebException(String errCode, String errMsg, Exception innerException) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.innerException = innerException;
    }


}
