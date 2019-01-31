package cn.iustu.site.common.util;

import cn.iustu.site.common.model.Page;

public class PageUtil {

    public static void pageValidate(Page page){
        if(page.getPage() == null || page.getPage() < 1) page.setPage(1);
        if(page.getRows() == null || page.getRows() < 1) page.setRows(10);
    }
}
