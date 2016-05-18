package org.chzz.mvp.base;

import org.chzz.mvp.App;
import org.chzz.mvp.engine.Engine;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/5/17
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/5/17--15:51
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public interface  BaseModel {
    public Engine mEngine = App.getInstance().getEngine();
}
