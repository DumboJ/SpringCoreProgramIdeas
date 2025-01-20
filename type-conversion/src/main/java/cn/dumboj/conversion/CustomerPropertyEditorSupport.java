package cn.dumboj.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 模拟 spring framework 操作 property
 */
public class CustomerPropertyEditorSupport extends PropertyEditorSupport implements PropertyEditor {
    /* setAsText()*/

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //1.Properties 存储 key-value
        Properties properties = new Properties();
        try {
            //读取值
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        //3.设置值
        setValue(properties);
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties)getValue();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Object, Object> entry : properties.entrySet()){
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
