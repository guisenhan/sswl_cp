package sswl.caipai.model;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
public class Result<T> {
    private String message;
    private T data;
    private String code;

    public Result() {

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
