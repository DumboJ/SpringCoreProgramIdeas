package cn.dumboj.databind;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;
/**
 * 数据绑定示例：对象属性绑定和类型转换的底层实现逻辑示例
 *
 * @see org.springframework.beans.MutablePropertyValues
 * */
public class DataBinderDemo {
    public static void main(String[] args) {
        Book book = new Book();

        //1.DataBinder 绑定数据
        DataBinder dataBinder = new DataBinder(book,"");

        //2. A: set parameter map
        Map<String, Object> map = new HashMap<>();
        map.put("name", "计算机导论");
        map.put("price", 30.0);


        // f1 :PropertyValues 存在 实体对象中没有的属性值
        //DataBinder 忽略
        map.put("id", 18);

        //f2: 嵌套关联属性值
        /*Source source = new Source();
        source.setName("dumboJ");
        source.setCode(20);
        map.put("source", source);*/
        map.put("source.name", "dumboJ");

        //3.使用 mutablePropertyValues 装填属性值
        PropertyValues propertyValues = new MutablePropertyValues(map);

        //Features
        //f1:忽略未知字段 default:true
//        dataBinder.setIgnoreUnknownFields(true);
        //f2:自动扩展嵌套属性 default:false  ->需要配合setIgnoreInvalidFields(BOOLEAN) 来使用，
        // 否则会报错
        dataBinder.setAutoGrowNestedPaths(false);
        dataBinder.setIgnoreInvalidFields(true);

        //f3:设置必要字段
        dataBinder.setRequiredFields("name", "price");

        //4.DataBinder 绑定 PropertyValues
        dataBinder.bind(propertyValues);
        //5.validation
        System.out.println(book);

        //打印绑定结果
        System.out.println(dataBinder.getBindingResult());
    }
}
