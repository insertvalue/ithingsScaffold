/**
 * @Title: XmlUtils
 * @Description:
 * @author pyfeng
 * @date 2014-4-21
 * @version V1.0 Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
package com.ithings.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;

/**
 * @ClassName: XmlUtils
 * @Description: xml和bean互相转换
 * @author pyfeng
 * @date 2014-4-21
 */
public class XmlUtils {

    /**
     * javabean转换为xml字符串
     *
     * @param obj
     * @param type
     * @return
     */
    public static String beanToXml(Object obj, Class type) {

        XStream stream = new XStream(new XppDriver(new NoNameCoder()));
        stream.processAnnotations(type);
        String str = stream.toXML(obj);
        return str;
    }

    /**
     * xml字符串转换为javabean
     *
     * @param content
     * @param type
     * @return
     */
    public static Object xmlToBean(String content, Class type) {

        XStream stream = new XStream(new DomDriver());
        stream.processAnnotations(type);
        Object obj = stream.fromXML(content);
        return obj;
    }
}
