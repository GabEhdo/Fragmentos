package com.example.fragments.AdapterView;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class ActivityBase extends AppCompatActivity implements  FragmentBase.OnVisibilityChangeListener {

    private boolean isKeyboardHidden;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isKeyboardHidden = newConfig.keyboardHidden ==Configuration.KEYBOARDHIDDEN_YES;
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoading() {

    }

   /* @Override
    public void showNetworkUnavailable() {
        SimpleAlertDialog simpleAlertDialog;
        simpleAlertDialog = new SimpleAlertDialog();
        simpleAlertDialog.setCancelable(false);
        simpleAlertDialog.setMessage(getString(R.string.message_network_unavailable_1));
        simpleAlertDialog.setPositiveButton(getString(R.string.accept_1), (view) -> simpleAlertDialog.dismiss());
        simpleAlertDialog.setTitle(getString(R.string.notice_1));
        simpleAlertDialog.show(getSupportFragmentManager(), null);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
      /*  Context contexto = getApplicationContext();
        RealmRepositoryFactory data = new RealmRepositoryFactory();
        DataProviderFactory dataProviderFactory = new RetrofitDataProviderFactory(contexto);


        if (data.createProcedureRepository().getFirst() !=null){
            String folio = data.createProcedureRepository().getFirst().getProcedureFolio();
            if(!folio.isEmpty()){
                ActivityBaseInteractor.unmarkAcount(data, dataProviderFactory, contexto);
            }
        }

       */
    }



}
