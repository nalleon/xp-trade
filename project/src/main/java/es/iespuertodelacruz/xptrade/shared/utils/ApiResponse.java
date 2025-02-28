package es.iespuertodelacruz.xptrade.shared.utils;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public class ApiResponse <T> {
    /**
     * Properties
     */
    private int status;
    private String message;
    private T data;

    /**
     * Full constructor of the class
     * @param status of the petition
     * @param message of the petition
     * @param data of the petition
     */
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * Getters and setters
     */
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
