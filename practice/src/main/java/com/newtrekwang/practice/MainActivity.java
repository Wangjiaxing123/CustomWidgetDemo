package com.newtrekwang.practice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Test;
import com.newtrekwang.practice.Helper.OkhttpHelper;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Test()
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity>>>>";
    @BindView(R.id.et_downloadLink)
    EditText etDownloadLink;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.btn_get)
    Button btnGet;

    @BindView(R.id.btn_post)
    Button btnPost;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

//
//    private void downloadFile(String url) {
//        Call<ResponseBody> call = MyHttpClient.getApi().downLoad(url);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    writeResponseBodyToDisk(response.body(), "hehe.html");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
//    }
//
    private boolean writeResponseBodyToDisk(ResponseBody responseBody, String saveName) {
        File file = new File(getExternalFilesDir(null) + File.separator + saveName);
        Logger.i("saveName>>>" + file.getAbsolutePath().toString());

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            byte[] bytes = new byte[2048];

            long fileSize = responseBody.contentLength();

            long fileDownLoadSize = 0;

            inputStream = responseBody.byteStream();
            outputStream = new FileOutputStream(file);

            while (true) {
                int read = inputStream.read(bytes);
                if (read == -1) {
                    break;
                }
                outputStream.write(bytes, 0, read);
                fileDownLoadSize += read;
                Log.i(TAG, "writeResponseBodyToDisk: " + "file download :" + fileDownLoadSize + " of " + fileSize);
            }
            outputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.btn_get, R.id.btn_post,R.id.btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                doGet();
                break;
            case R.id.btn_post:
                break;
            case R.id.btn_download:
                break;
        }
    }


    private void doGet(){
        Request.Builder builder=new Request.Builder()
                .url("http://inews.gtimg.com/newsapp_match/0/1436742931/0")
                .get();
        final Request request=builder.build();

         Call call=OkhttpHelper.getOkhttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: !!!!!!!!!!");
                handler.sendEmptyMessage(100);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.i(response.headers().toString());
                writeResponseBodyToDisk(response.body(),"haha.gif");
            }
        });
    }
}
