package com.edu.huatec.httprequest.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

public class ProgressDialogSubscriber<T> extends Subscriber<T> {


    private Context context;
    private ProgressDialog mDialog;

    public ProgressDialogSubscriber(Context ctx) {
        this.context = ctx;
    }


    @Override
    public void onCompleted() {
        dismissDialog();
    }

    private void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查网络状态！", Toast.LENGTH_LONG).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查网络状态！", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "error : " + e.getMessage() + "！", Toast.LENGTH_LONG).show();
        }
        e.printStackTrace();
        dismissDialog();
    }

    @Override
    public void onNext(T t) {

    }


    @Override
    public void onStart() {
        super.onStart();

        showDialog();

    }

    private void showDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(context);
            mDialog.setCancelable(true);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    unsubscribe();
                }
            });
        }

        if(mDialog!=null && !mDialog.isShowing()){
            mDialog.show();
        }
    }
}
