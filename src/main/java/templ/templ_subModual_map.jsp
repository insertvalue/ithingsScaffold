<%-- 
    @Title: {$=name$}
    @Description: {$=shortname$}
    @author {$=author$}
    @date {$=date$}
    @version V1.0   
    Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">  </meta>
        <meta http-equiv="cache-control" content="no-cache"></meta>
        <meta http-equiv="expires" content="0"></meta>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div name="gis" id="mapDiv" style="width: 100%; height: 100%; solid: #000; ">
            <div id="popitmenu"></div>
            <!-- 坐标显示 -->
            <div id="info" style="display:none;position: absolute; left: 10px; bottom: 5px; padding: 5px;
                 margin-bottom: 30px; background-color: #60a0EE; opacity: 0.8; color: white; z-index: 9; 
                 font-size: 12px">
            </div>
            <!-- 面积计算结果显示DIV -->
            <div id="infor2" class="distanceInfo">
                <div id="distance"></div>
            </div>

            <!-- 地图工具条 -->
            <div id="mapToolBar"></div>
        </div>

        <!-- 右侧列表 -->
        <div id="mapMinList" name="gis"></div>
    </body>
</html>