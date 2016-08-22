package br.com.musicaaqui.musicaaqui.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.ref.WeakReference;


/**
 * Classe para controlar a thread
 * 
 * @author ricardo
 * 
 */
public class TransacaoTask extends AsyncTask<Void, Void, Boolean> {
	private static final String TAG = "livroandroid";
	private Context context = null;
	private Transacao transacao = null;
	private ProgressDialog progresso;
	private Throwable exceptionErro;
	private String aguardeMsg;
	private WeakReference<Fragment> fragment;

	public TransacaoTask(Fragment fragment) {
		this.fragment = new WeakReference<>(fragment);
	}

	public void setFragment(Fragment fragment) {
		this.fragment = new WeakReference<>(fragment);
	}


	public TransacaoTask(Context context, Transacao transacao, String aguardeMsg) {
		this.context = context;
		this.transacao = transacao;
		this.aguardeMsg = aguardeMsg;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		transacao.progress();
		// Inicia a janela de aguarde...
	//	abrirProgress();
	}
	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			transacao.executar();
		} catch (Throwable e) {
			Log.e(TAG, e.getMessage(), e);
			// Salva o erro e retorna false
			this.exceptionErro = e;
			return false;
		} finally {
			try {
		//		fecharProgress();
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
		// OK
		return true;
	}
	@Override
	protected void onPostExecute(Boolean ok) {
		if (ok) {
			// Transa��o executou com sucesso
			transacao.atualizarView();
		} else {
			// Erro
			AndroidUtils.alertDialog(context, "Erro: " + exceptionErro.getMessage());
			//Log.e(TAG,  "Erro: " + exceptionErro.getMessage());
		}
	}
	public void abrirProgress() {
		try {
			progresso = ProgressDialog.show(context, "", aguardeMsg);
		} catch (Throwable e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
	public void fecharProgress() {
		try {
			if (progresso != null) {
				progresso.dismiss();
			}
		} catch (Throwable e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
}
