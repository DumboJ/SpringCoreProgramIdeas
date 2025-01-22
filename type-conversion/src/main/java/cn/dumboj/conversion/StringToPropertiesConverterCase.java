package cn.dumboj.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
/**
 * 通过 实现 ConditionalGenericConverter 接口，实现自定义转换器
 * */
public class StringToPropertiesConverterCase implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals(targetType)
                && String.class.equals(sourceType);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Properties.class));
    }

    @Override
    public Object convert(Object object, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Properties properties = (Properties)object;
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Object, Object> entry : properties.entrySet()){
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
