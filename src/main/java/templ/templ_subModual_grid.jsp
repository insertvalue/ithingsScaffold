<%-- 
    @Title: {$=menu.shortname$}
    @Description: {$=menu.name$}
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
        <div class="max_title" style="display: block">
            <div id='operButton' style="height: 35px;line-height: 2.5;" class="ui-accordion-header ui-helper-reset ui-state-default ui-accordion-header-active ui-state-active ui-accordion-icons ui-state-hover" role="tab" id="ui-accordion-maxList-header-0" aria-controls="ui-accordion-maxList-panel-0" aria-selected="true" tabindex="0">
                <div class="cssHtmlSearch" style="float:left; width: 80%">
                    <div class='cssHtmlSearchItem'>
                        <span class='textSpan'>查询条件</span>
                        <input type='text' placeholder="查询条件"/>
                    </div>
                    <div class="cssHtmlSearchItem">
                        <button id="submit" name="submit" class="bottom_tow">查&nbsp&nbsp询</button>
                    </div>
                </div>
            </div>
            <div class="grid">
                <table id="{$=menu.shortname$}Grid"></table>
                <div id="{$=menu.shortname$}GridPager"></div>
            </div>
        </div>
    </body>
</html>
