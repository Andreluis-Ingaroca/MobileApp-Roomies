package Beans;

public class DNIData {
    private boolean success;
    private Data data;
    private String source;

    public DNIData(boolean success, Data data, String source) {
        this.success = success;
        this.data = data;
        this.source = source;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
