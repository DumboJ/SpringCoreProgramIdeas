package cn.dumboj.databind;

public class Source {
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Source(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Source() {
    }

    @Override
    public String toString() {
        return "Source{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
