package com.newtrekwang.practice.Helper;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by WJX .
 * Desc:
 * Created on 2017/1/15 15:34.
 * Mail:408030208@qq.com
 */

public interface ApiStores {
    @GET
    Observable<ResponseBody> downLoad(@Url String fileUrl);

}
