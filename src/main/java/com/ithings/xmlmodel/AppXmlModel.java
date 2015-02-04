/**
 * @Title: AppXmlModel
 * @Description:
 * @author pyfeng
 * @date 2015-2-3
 * @version V1.0 Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
package com.ithings.xmlmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 * @ClassName: AppXmlModel
 * @Description:
 * @author pyfeng
 * @date 2015-2-3
 */
@XStreamAlias("app")
public class AppXmlModel {

    @XStreamImplicit(itemFieldName = "modual")
    private List<ModualXmlModel> modualList;

    public List<ModualXmlModel> getModualList() {
        return modualList;
    }

    public void setModualList(List<ModualXmlModel> modualList) {
        this.modualList = modualList;
    }

    @Override
    public String toString() {
        return "AppXmlModel{" + "modualList=" + modualList + '}';
    }
    
}
