/**
 * @Title: ModualXmlModel
 * @Description:
 * @author pyfeng
 * @date 2015-2-3
 * @version V1.0 Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
package com.ithings.xmlmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 * @ClassName: ModualXmlModel
 * @Description:
 * @author pyfeng
 * @date 2015-2-3
 */
@XStreamAlias("modual")
public class ModualXmlModel {

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("shortname")
    private String shortname;
    
    @XStreamImplicit(itemFieldName = "menu")
    private List<MenuXmlModel> menuList;

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

    public List<MenuXmlModel> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuXmlModel> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "ModualXmlModel{" + "name=" + name + ", shortname=" + shortname + ", menuList=" + menuList + '}';
    }
    
    
}
