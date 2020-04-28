package com.example.demo;

import com.example.demo.entity.CommentItem;
import com.example.demo.entity.JsonResult;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Author godv
 * Date on 2020/4/27  23:33
 */
public interface RetrofitHelper {
    //不带参数的get
    @GET("/get/text")
    Call<JsonResult> getJson();

    //带参数的get 1 Query 相当于自动拼接url get?id=1. post带url也是这样的
    @GET("/get")
    Call<ResponseBody> getWithParam(@Query("id")String id);
    //带参数get 2 QueryMap
    @GET("/get")
    Call<ResponseBody> getWithParam(@QueryMap Map<String,Object> params);

    //带body的post
    @POST("/post/comment")
    Call<ResponseBody> postWithBodyContent(@Body CommentItem commentItem);

    //单文件上传 @Part注解，要跟@Multipart注解一起使用。
    /**调用
     * File file = new File("/storage/emulated/0/Download/1.jpg");
     * MediaType mediaType = MediaType.parse("image/jpg");
     * RequestBody fileBody = RequestBody.create(mediaType,file);
     * MultipartBody.Part part = MultipartBody.Part.createFormData("file",file.getName(),fileBody);
     * Call<FileUploadResult> task = mSobMiniWebInterface.postFile(part);
     * @param file
     * @return
     */
    @Multipart
    @POST("/file/upload")
    Call<ResponseBody> postFile(@Part MultipartBody.Part file);

    //单文件上传，并携带信息 @PartMap注解
    @Multipart
    @POST("/multiFiles/upload")
    Call<ResponseBody> postFileWithParams(@PartMap Map<String,Object> params, @Part MultipartBody.Part file);

    //多文件上传 使用一个list
    @Multipart
    @POST("/files/upload")
    Call<ResponseBody> postFiles(@Part List<MultipartBody.Part> files);

    //文件下载
    /**调用。传入资源路径
     *   Call<ResponseBody> task = mSobMiniWebInterface.downFile("/download/10");
     * @param url
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downFile(@Url String url);

    //提交表单 还可以使用@FailedMap 和前面@QueryMap类似
    @FormUrlEncoded
    @POST("/login")
    Call<ResponseBody> doLogin(@Field("userName") String userName, @Field("password") String password);






}
