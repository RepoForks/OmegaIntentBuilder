package com.omega_r.omegaintentbuilder;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.omega_r.libs.omegaintentbuilder.AppOmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes;
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import omega.com.annotations.OmegaActivity;
import omega.com.annotations.OmegaExtra;

@OmegaActivity
public class ShareFilesActivity extends AppCompatActivity implements View.OnClickListener {

    @OmegaExtra
    protected String url1;

    @OmegaExtra("var2")
    protected String url2;

    @Nullable
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_files);
        AppOmegaIntentBuilder.inject(this);
        findViewById(R.id.button_share).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_share:
                downlodFiles();
                break;
        }
    }

    private void downlodFiles() {
        showProgress();
        OmegaIntentBuilder.from(this).share()
                .emailTo("your_email_here@gmail.com")
                .subject("Great library")
                .filesUrls(url1)
                .filesUrlWithMimeType(url2, MimeTypes.IMAGE_PNG)
                .download(new DownloadCallback() {
                    @Override
                    public void onDownloaded(boolean success, @NotNull ContextIntentHandler contextIntentHandler) {
                        hideProgress();
                        contextIntentHandler.startActivity();
                    }
                });
    }

    private void showProgress() {
        if (progressDialog == null) {
            progressDialog = createProgressDialog();
        }
        assert progressDialog != null;
        progressDialog.show();
    }

    private ProgressDialog createProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
