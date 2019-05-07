package io.gank.gank.sort.data;

import java.util.List;

/**
 * Created by yinzhegang on 2019/3/15.
 */

public class SortData {

    /**
     * error : false
     * results : [{"_id":"5c8747cf9d2122032f6b5aaf","createdAt":"2019-03-12T05:46:55.816Z","desc":"Flutter日益恒行，一篇入门级BaseWidget项目架构值得您的关注~","images":["http://img.gank.io/2aff8927-bd5d-4d8f-9fac-6e4ed2013c87"],"publishedAt":"2019-03-13T01:32:11.450Z","source":"web","type":"Android","url":"https://blog.csdn.net/iamdingruihaha/article/details/88319883","used":true,"who":"潇湘剑雨"},{"_id":"5bbb061e9d2122610ee409d8","createdAt":"2018-10-08T07:24:14.959Z","desc":"WMRouter是一款Android路由框架，基于组件化的设计思路，有功能灵活、使用简单的特点。","publishedAt":"2019-03-12T01:41:06.815Z","source":"chrome","type":"Android","url":"https://github.com/meituan/WMRouter","used":true,"who":"潇湘剑雨"},{"_id":"5c8220399d2122032aa5cb11","createdAt":"2019-03-08T07:56:41.430Z","desc":"通知栏封装库，链式编程调用，解决了8.0以上通知栏不显示问题，兼容老版本。 ","images":["http://img.gank.io/0c5699e3-c096-42c6-88de-3b50768a0c3b"],"publishedAt":"2019-03-12T01:39:55.952Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCNotification","used":true,"who":"潇湘剑雨"},{"_id":"5bbbd36d9d212203189ecd46","createdAt":"2018-10-08T22:00:13.979Z","desc":"回归初心：极简 Android 组件化方案 \u2014 AppJoint","publishedAt":"2019-03-08T07:06:58.689Z","source":"web","type":"Android","url":"https://juejin.im/post/5bb9c0d55188255c7566e1e2","used":true,"who":"潇湘剑雨"},{"_id":"5c80c0e59d212233d5c508cf","createdAt":"2019-03-07T06:57:41.57Z","desc":"小红书图片裁剪库","publishedAt":"2019-03-07T06:59:41.704Z","source":"web","type":"Android","url":"https://juejin.im/post/5c7ce036e51d4558d36061ec","used":true,"who":"fingdo"},{"_id":"5c7f68cc9d212233da370f4d","createdAt":"2019-03-07T03:39:41.8Z","desc":"StarrySky 集成了音频播放需要的所有功能","images":["http://img.gank.io/17129b78-de3c-4fa7-9321-8839d218a4db"],"publishedAt":"2019-03-07T03:39:56.475Z","source":"web","type":"Android","url":"https://github.com/lizixian18/StarrySky","used":true,"who":"fingdo"},{"_id":"5c7df3b79d212233da370f49","createdAt":"2019-03-06T01:21:11.636Z","desc":" 可配置自定义规则来格式化输入内容的输入框","images":["http://img.gank.io/5235c384-e75d-4761-be5a-0913e8b606f0"],"publishedAt":"2019-03-06T01:21:34.150Z","source":"web","type":"Android","url":"https://github.com/dkzwm/FormatEditText","used":true,"who":"fingdo"},{"_id":"5c77484d9d212233d5c508b8","createdAt":"2019-02-28T02:32:45.646Z","desc":"码云最有价值开源项目：后端接口和文档自动化，前端(客户端) 定制返回JSON的数据和结构！","images":["http://img.gank.io/4f37384f-745d-4422-8b13-b9cdbdf8e136"],"publishedAt":"2019-03-03T13:19:31.291Z","source":"web","type":"Android","url":"https://gitee.com/TommyLemon/APIJSON","used":true,"who":"潇湘剑雨"},{"_id":"5c63734f9d21225dd1275931","createdAt":"2019-02-13T01:30:55.629Z","desc":"Android组件化开发实践和案例分享","images":["http://img.gank.io/a0f2cb98-d8f5-40ed-9c2c-0c4cfb045eea"],"publishedAt":"2019-03-03T13:16:28.307Z","source":"web","type":"Android","url":"https://juejin.im/post/5c46e6fb6fb9a049a5713bcc","used":true,"who":"潇湘剑雨"},{"_id":"5c789ee19d212233d4b3da89","createdAt":"2019-03-01T02:54:25.522Z","desc":"Android消息总线的演进之路：用LiveDataBus替代RxBus、EventBus","images":["http://img.gank.io/48d7129b-ee08-4a5d-8eb8-bd626254eeae","http://img.gank.io/85c21024-8f3b-4899-91b6-95451a0f3b07"],"publishedAt":"2019-03-01T03:13:05.882Z","source":"web","type":"Android","url":"https://tech.meituan.com/2018/07/26/android-livedatabus.html","used":true,"who":"潇湘剑雨"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5c8747cf9d2122032f6b5aaf
         * createdAt : 2019-03-12T05:46:55.816Z
         * desc : Flutter日益恒行，一篇入门级BaseWidget项目架构值得您的关注~
         * images : ["http://img.gank.io/2aff8927-bd5d-4d8f-9fac-6e4ed2013c87"]
         * publishedAt : 2019-03-13T01:32:11.450Z
         * source : web
         * type : Android
         * url : https://blog.csdn.net/iamdingruihaha/article/details/88319883
         * used : true
         * who : 潇湘剑雨
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
