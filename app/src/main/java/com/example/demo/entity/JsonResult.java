package com.example.demo.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * @Author godv
 * Date on 2020/4/28  10:09
 */
public class JsonResult {

    /**
     * success : true
     * code : 10000
     * message : 获取成功
     * data : [{"id":"1254956052393525248","title":"Android加载大图片，解决OOM问题","viewCount":277,"commentCount":99,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/3.png"},{"id":"1254956052393525249","title":"Volley/Xutils对大图片处理算法源码分析","viewCount":81,"commentCount":25,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/6.png"},{"id":"1254956052393525250","title":"Android开发网络安全配置","viewCount":75,"commentCount":22,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/8.png"},{"id":"1254956052393525251","title":"Android开发网络编程，请求图片","viewCount":285,"commentCount":92,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/11.png"},{"id":"1254956052393525252","title":"Intent页面跳转工具类分享","viewCount":161,"commentCount":39,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/11.png"},{"id":"1254956052393525253","title":"阳光沙滩商城的API文档","viewCount":211,"commentCount":40,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/1.png"},{"id":"1254956052393525254","title":"Android课程视频打包下载","viewCount":268,"commentCount":92,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/4.png"},{"id":"1254956052393525255","title":"非常轻量级的gif录制软件","viewCount":106,"commentCount":100,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/5.png"},{"id":"1254956052393525256","title":"Fiddler抓包工具，墙裂推荐，功能很强大很全的一个工具","viewCount":310,"commentCount":30,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/0.png"},{"id":"1254956052393525257","title":"AndroidStudio奇淫技巧-代码管理","viewCount":194,"commentCount":16,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/8.png"},{"id":"1254956052393525258","title":"OC和Swift混编","viewCount":153,"commentCount":48,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/15.png"},{"id":"1254956052393525259","title":"最新的Android studio是不是没有Android Device Monitor","viewCount":158,"commentCount":18,"publishTime":"2020-04-28T02:10:13.503+0000","userName":"程序员拉大锯","cover":"/imgs/16.png"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBeans> data;

    public static JsonResult objectFromData(String str) {

        return new Gson().fromJson(str, JsonResult.class);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBeans> getData() {
        return data;
    }

    public void setData(List<DataBeans> data) {
        this.data = data;
    }

    public static class DataBeans {
        /**
         * id : 1254956052393525248
         * title : Android加载大图片，解决OOM问题
         * viewCount : 277
         * commentCount : 99
         * publishTime : 2020-04-28T02:10:13.503+0000
         * userName : 程序员拉大锯
         * cover : /imgs/3.png
         */

        private String id;
        private String title;
        private int viewCount;
        private int commentCount;
        private String publishTime;
        private String userName;
        private String cover;

        public static DataBeans objectFromData(String str) {

            return new Gson().fromJson(str, DataBeans.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
