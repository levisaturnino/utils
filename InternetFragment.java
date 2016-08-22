package br.com.musicaaqui.musicaaqui.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;

import br.com.musicaaqui.musicaaqui.fragments.BaseFragment;

public abstract class InternetFragment extends BaseFragment {
    private ConexaoReceiver mReceiver;
    @Override
    public void onResume() {
        super.onResume();
        mReceiver = new ConexaoReceiver();
        IntentFilter filter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(mReceiver, filter);
    }
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mReceiver);
    }
    public abstract void iniciarDownload();
    class ConexaoReceiver extends BroadcastReceiver {
        boolean mPrimeiraVez = true;
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mPrimeiraVez) {
                mPrimeiraVez = false;
                return;
            }
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                if (AndroidUtils.isNetworkAvailable(context)) {
                    iniciarDownload();
                }
            }
        }
    }
}

