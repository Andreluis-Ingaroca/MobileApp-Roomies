package Beans;

import java.io.Serializable;

public class User implements Serializable {

    private Result result;
    private int id;
    private String exception;
    private int status;
    private boolean isCanceled;
    private boolean isCompleted;
    private boolean isCompletedSuccessfully;
    private int creationOptions;
    private String asyncState;
    private boolean isFaulted;

    public User(Result result, int id, String exception, int status, boolean isCanceled, boolean isCompleted, boolean isCompletedSuccessfully, int creationOptions, String asyncState, boolean isFaulted) {
        this.result = result;
        this.id = id;
        this.exception = exception;
        this.status = status;
        this.isCanceled = isCanceled;
        this.isCompleted = isCompleted;
        this.isCompletedSuccessfully = isCompletedSuccessfully;
        this.creationOptions = creationOptions;
        this.asyncState = asyncState;
        this.isFaulted = isFaulted;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isCompletedSuccessfully() {
        return isCompletedSuccessfully;
    }

    public void setCompletedSuccessfully(boolean completedSuccessfully) {
        isCompletedSuccessfully = completedSuccessfully;
    }

    public int getCreationOptions() {
        return creationOptions;
    }

    public void setCreationOptions(int creationOptions) {
        this.creationOptions = creationOptions;
    }

    public String getAsyncState() {
        return asyncState;
    }

    public void setAsyncState(String asyncState) {
        this.asyncState = asyncState;
    }

    public boolean isFaulted() {
        return isFaulted;
    }

    public void setFaulted(boolean faulted) {
        isFaulted = faulted;
    }
}
