package com.tao.quickindex;


import android.content.Context;

/**
 * @author Administrator
 * @version $Rev: 8 $
 * @time 2015-7-15 上午10:59:15
 * @des 和ui相关的工具类
 * @updateAuthor $Author: admin $
 * @updateDate $Date: 2015-07-15 17:06:45 +0800 (星期三, 15 七月 2015) $
 * @updateDes TODO
 */
public class UIUtils {

    /**
     * dip-->px
     */
    public static int dip2Px(Context context, int dip) {
        // px/dip = density;
        float density = context.getResources().getDisplayMetrics().density;

        int px = (int) (dip * density + .5f);
        return px;
    }

    /**
     * px-->dip
     */
    public static int px2Dip(Context context, int px) {
        // px/dip = density;
        float density = context.getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + .5f);
        return dip;
    }

}
