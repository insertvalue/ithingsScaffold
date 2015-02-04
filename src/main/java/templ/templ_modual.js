/**   
 * @Title: {$=modual.shortname$}
 * @Description: {$=modual.name$}
 * @author {$=author$}
 * @date {$=date$}
 * @version V1.0   
 * Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */

{$=MODUAL_JS_REG[0]$}
function execute() {
    //点击二级菜单
    var menu_2_a = $(parent.frames["frame_menu"].document).find("#{$=modual.shortname$} a");
    menu_2_a.off().on('click', function() {
        var divIdM2ShortName;
        divIdM2ShortName = $(this).attr("id");
        var modelView = null, modelJs = null, objModel = null;
        switch (divIdM2ShortName) {
            {$=MODUAL_JS_REG[1]$}
        }
        if (!modelView) {
            HdLib.Art.artOK("提示", "warning", '[' + $(this)[0].text + ']没有定义视图！');
            return;
        } else if (!modelJs) {
            HdLib.Art.artOK("提示", "warning", '[' + $(this)[0].text + ']没有定义控制js！');
            return;
        }
        //写入模块控制页面
        $(" .cssBodyDiv").empty().load($.i18n.prop(modelView), function() {
            HdLib.loadScript($.i18n.prop(modelJs), function() {
                if (null != objModel) {
                    objModel.init();
                    objModel = null;
                }
            });
        });
    });

    //选择一个默认二级菜单
    var currentFlag = $(parent.frames["frame_menu"].document).find(' .menu_2_current');
    if (currentFlag.length > 0) {
        $(currentFlag[0]).trigger('click');
    } else {
        //第一个二级菜单默认选择
        $(menu_2_a[0]).trigger('click');
    }
};
