package CuentasClaras.CuentasClaras.Modelos;


import java.io.Serializable;

public class ApiError implements Serializable {

    private static final long serialVersionUID = -2980617456780590389L;

    private String message;

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

