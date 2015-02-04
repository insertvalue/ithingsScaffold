/**
 * @Title: MenuXmlModel
 * @Description:
 * @author pyfeng
 * @date 2015-2-3
 * @version V1.0 Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
package com.ithings.xmlmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @ClassName: MenuXmlModel
 * @Description:
 * @author pyfeng
 * @date 2015-2-3
 */
@XStreamAlias("menu")
public class MenuXmlModel {

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;
    
    @XStreamAsAttribute
    @XStreamAlias("shortname")
    private String shortname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return "MenuXmlModel{" + "name=" + name + ", shortname=" + shortname + '}';
    }
    
    
}
