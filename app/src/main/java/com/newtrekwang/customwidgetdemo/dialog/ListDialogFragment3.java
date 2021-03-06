package com.newtrekwang.customwidgetdemo.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.newtrekwang.customwidgetdemo.R;
import com.newtrekwang.customwidgetdemo.toast.ToastBuilder;

public class ListDialogFragment3 extends DialogFragment {
    private int selectedItem;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("这是个永久性单选列表")
                .setSingleChoiceItems(R.array.listmenuitems, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      selectedItem=which;
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new ToastBuilder().setContentString(""+selectedItem).createDefaultTextToast(getActivity()).show();
                    }
                })
                .setNegativeButton(R.string.cancel,null);

        return builder.create();
    }
}
