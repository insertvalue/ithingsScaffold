package com.ithings.ithingsscaffold;

import com.ithings.utils.XmlUtils;
import com.ithings.xmlmodel.AppXmlModel;
import com.ithings.xmlmodel.MenuXmlModel;
import com.ithings.xmlmodel.ModualXmlModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    private static String output;
    private static String templPath;
    private final static String TEMPL_MODUAL_JS = "templ_modual.js";
    private final static String TEMPL_SUBMODUAL_JS = "templ_subModual.js";
    private final static String TEMPL_SUBMODUAL_JSP = "templ_subModual_grid.jsp";
    private static StringBuilder[] MODUAL_JS_REG = new StringBuilder[2];
    private final static String JS_NS = "IFlood";
    private final static String AUTHOR = "pyfeng";
    private final static StringBuilder menuOneCss = new StringBuilder("//**********一级菜单样式**********//\n");
    private final static StringBuilder menuTwoCss = new StringBuilder("//**********二级菜单样式**********//\n");

    private static String cssOne = "/*{0}*/\n"
            + "#nav li .menu_1 .menu_1_{1}{\n"
            + "    margin-top: 10px;\n"
            + "    width: 100%;\n"
            + "    height: 30px;\n"
            + "    background:url(../images/menu/{1}.png) no-repeat bottom center;\n"
            + "}";

    private static String cssTwo = "/*{0}*/\n"
            + "#nav .menu_2 .menu_2_item .{1}{\n"
            + "    float: left;\n"
            + "    width: 40px;\n"
            + "    height: 30px;\n"
            + "    background:url(../images/menu/{1}.png) no-repeat center center;\n"
            + "}";

    public static void main(String[] args) throws Exception {
        File dir = new File(args[1]);
        output = dir.getParent() + "\\output\\";
        templPath = dir.getParent() + "\\templ\\";
        createOutputDir();//创建脚手架输出目录
        initScaffold(args[1]);//初始化脚手架
    }

    private static void initScaffold(String configPath) throws Exception {
        //读取配置文件
        String appXml = readFile(configPath);
        AppXmlModel appXmlModel = (AppXmlModel) XmlUtils.xmlToBean(appXml, AppXmlModel.class);
        List<ModualXmlModel> modualList = appXmlModel.getModualList();
        for (ModualXmlModel modualXmlModel : modualList) {
            createModual(modualXmlModel);//创建模块相关目录和文件
            setOneMenuCss(modualXmlModel);
        }
        createMenuCss(output + "menu");
    }

    private static void createModual(ModualXmlModel modualXmlModel) throws Exception {
        createDir(output + modualXmlModel.getShortname() + "\\");
        createDir(output + modualXmlModel.getShortname() + "\\controller\\");
        createDir(output + modualXmlModel.getShortname() + "\\css\\");
        createDir(output + modualXmlModel.getShortname() + "\\view\\");
        List<MenuXmlModel> menuXmlModelList = modualXmlModel.getMenuList();
        
        MODUAL_JS_REG[0] = new StringBuilder();
        MODUAL_JS_REG[1] = new StringBuilder();
        for (MenuXmlModel menuXmlModel : menuXmlModelList) {
            createSubModual(modualXmlModel, menuXmlModel);//创建子模块相关目录和文件
            setTwoMenuCss(menuXmlModel);
        }
        createModualJs(modualXmlModel, output + modualXmlModel.getShortname() + "\\" + modualXmlModel.getShortname());
        createModualCss(output + modualXmlModel.getShortname() + "\\css\\" + modualXmlModel.getShortname());
    }

    //菜单css文件
    private static void createMenuCss(String path) throws Exception {
        createFile(path, ".css");
        menuOneCss.append(menuTwoCss);
        writeFile(path + ".css", menuOneCss.toString());
    }

    //模块js文件
    private static void createModualJs(ModualXmlModel modualXmlModel, String path) throws Exception {
        createFile(path, ".js");
        String js = readFile(templPath + TEMPL_MODUAL_JS);
        js = replaceModualJs(modualXmlModel, js);
        writeFile(path + ".js", js);
    }

    //模块css文件
    private static void createModualCss(String path) throws Exception {
        createFile(path, ".css");
    }

    //子模块js文件
    private static void createSubModualJs(MenuXmlModel menuXmlModel, String path) throws Exception {
        createFile(path, ".js");
        String js = readFile(templPath + TEMPL_SUBMODUAL_JS);
        js = replaceSubModualJs(menuXmlModel, js);
        writeFile(path + ".js", js);
    }

    //子模块jsp文件
    private static void createSubModualJsp(MenuXmlModel menuXmlModel, String path) throws Exception {
        createFile(path, ".jsp");
        String jsp = readFile(templPath + TEMPL_SUBMODUAL_JSP);
        jsp = replaceSubModualJs(menuXmlModel, jsp);
        writeFile(path + ".jsp", jsp);
    }

    private static void createSubModual(ModualXmlModel modualXmlModel, MenuXmlModel menuXmlModel) throws Exception {
        createSubModualJs(menuXmlModel, output + modualXmlModel.getShortname() + "\\controller\\" + menuXmlModel.getShortname());
        createSubModualJsp(menuXmlModel, output + modualXmlModel.getShortname() + "\\view\\" + menuXmlModel.getShortname());
        String menuJsObj = firstToUpcase(menuXmlModel.getShortname());
        /**
         * 形如： var IssueMng = HdLib.IFlood.IssueMng = function() {};//发布管理
         */
        MODUAL_JS_REG[0].append("var ").append(menuJsObj)
                .append(" = HdLib.").append(JS_NS).append(".").append(menuJsObj)
                .append(" = function() {};//").append(menuXmlModel.getName()).append("\n");

        /**
         * 形如： case 'issueMng'://历史洪水 modelView = 'issueMng_view'; modelJs =
         * 'issueMng_js'; objModel = new IssueMng(); break;
         */
        MODUAL_JS_REG[1].append("case '").append(menuXmlModel.getShortname())
                .append("'://").append(menuXmlModel.getName()).append("\n                ")
                .append("modelView = '").append(menuXmlModel.getShortname()).append("_view';").append("\n                ")
                .append("modelJs = '").append(menuXmlModel.getShortname()).append("_js';").append("\n                ")
                .append("objModel = new ").append(menuJsObj).append("();\n                break;").append("\n            ");
    }

    private static void createOutputDir() {
        createDir(output);
    }

    //新建文件夹
    private static void createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    //设置模块一级菜单样式
    private static void setOneMenuCss(ModualXmlModel modualXmlModel) throws Exception {
        String ret = cssOne.replace("{0}", modualXmlModel.getName());
        ret = ret.replace("{1}", modualXmlModel.getShortname());
        menuOneCss.append(ret).append("\n\n");
    }

    //设置模块二级菜单样式
    private static void setTwoMenuCss(MenuXmlModel menuXmlModel) throws Exception {
        String ret = cssTwo.replace("{0}", menuXmlModel.getName());
        ret = ret.replace("{1}", menuXmlModel.getShortname());
        menuTwoCss.append(ret).append("\n\n");
    }

    //新建文件
    private static void createFile(String path, String suffix) throws Exception {
        File file = new File(path + suffix);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    //读取文件到字符串
    private static String readFile(String path) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        String line;
        StringBuilder sb = new StringBuilder();
        while (null != (line = br.readLine())) {
            sb.append(line).append("\n");
        }
        br.close();
        String xml = sb.toString();
        return xml;
    }

    //写字符串到文件
    private static void writeFile(String path, String content) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
        bw.write(content);
        bw.close();
    }

    //首字母转大写
    private static String firstToUpcase(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    //替换模块js
    private static String replaceModualJs(ModualXmlModel modualXmlModel, String str) {
        str = str.replace("{$=modual.shortname$}", modualXmlModel.getShortname());
        str = str.replace("{$=modual.name$}", modualXmlModel.getName());
        str = str.replace("{$=author$}", AUTHOR);
        str = str.replace("{$=date$}", new Date().toString());
        for (int i = 0; i < MODUAL_JS_REG.length; i++) {
            str = str.replace("{$=MODUAL_JS_REG[" + i + "]$}", MODUAL_JS_REG[i]);
        }
        return str;
    }

    //替换子模块js
    private static String replaceSubModualJs(MenuXmlModel menuXmlModel, String str) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        str = str.replace("{$=menu.shortname$}", menuXmlModel.getShortname());
        str = str.replace("{$=menu.name$}", menuXmlModel.getName());
        str = str.replace("{$=author$}", AUTHOR);
        str = str.replace("{$=date$}", sFormat.format(c.getTime()));
        str = str.replace("{$=menu.js_obj$}", firstToUpcase(menuXmlModel.getShortname()));

        return str;
    }
}
