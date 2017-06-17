package buy.win.com.winbuy.model.net;

/**
 * Created by dada on 2017/6/14.
 */


public class BaseBean {

    private String error;
    private String error_code;

    private String response;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "error='" + error + '\'' +
                ", error_code='" + error_code + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

    public void setResponse(String response) {

        this.response = response;
    }
}
