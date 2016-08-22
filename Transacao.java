package br.com.musicaaqui.musicaaqui.utils;

/**
 * Created by saturnino on 29/05/2016.
 */
public interface Transacao {

     void progress();

     void executar() throws Exception;

     void atualizarView();
}
