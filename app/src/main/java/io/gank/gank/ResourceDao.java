package io.gank.gank;

import java.util.List;

import io.gank.gank.sqlBean.ResourceBean;

/**
 * Created by yinzhegang on 2019/3/25.
 */

public class ResourceDao {
    public static void  insertResource(ResourceBean resourceBean){
//        Resource.getDaoSession().insertOrReplace(resourceBean);
        Resource.getDaoSession().getResourceBeanDao().insertOrReplace(resourceBean);
    }
    public static List<ResourceBean> queryResource() {
        return Resource.getDaoSession().getResourceBeanDao().loadAll();
    }

}
