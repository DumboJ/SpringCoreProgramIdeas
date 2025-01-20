package cn.dumboj.conversion;
/**
 * 类型转换 示例
 *
 * */
public class TypeConversionDemo {
    public static void main(String[] args) {
        String mock = "city : BeiJing";
        CustomerPropertyEditorSupport editor = new CustomerPropertyEditorSupport();
        editor.setAsText(mock);

        System.out.println(editor.getValue());
        System.out.println(editor.getAsText());
    }
}
