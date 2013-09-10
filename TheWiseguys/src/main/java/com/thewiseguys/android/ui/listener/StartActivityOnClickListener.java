package com.thewiseguys.android.ui.listener;

/**
 * Version 0.0.1 - Last Modified 6/09/13.
 * Created by Chris Krause on 6/09/13.
 * TheWiseguys - com.thewiseguys.android.ui.listener
 */
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

public class StartActivityOnClickListener implements View.OnClickListener {

    /**
     *
     */
    private Context context ;

    /**
     *
     */
    private Intent intent ;

    /**
     *
     */
    private Fragment fragment ;

    /**
     * @param context
     * @param objectClass
     */
    public StartActivityOnClickListener(Context context, Class<?> objectClass) {
        super();
        this.context = context ;
        this.intent = new Intent(this.context, objectClass);
    }

    /**
     * @param objectClass
     */
    public StartActivityOnClickListener(Fragment frament, Class<?> objectClass) {
        super();
        this.fragment = frament ;
        this.intent = new Intent(frament.getActivity(), objectClass);
    }

    /**
     * @param context
     * @param intent
     */
    public StartActivityOnClickListener(Context context, Intent intent) {
        super();
        this.context = context ;
        this.intent = intent;
    }

    /**
     * {@inheritDoc}
     */
    public void onClick(View view) {
        if ( this.context != null ) {
            this.context.startActivity(this.intent);
        }
        else {
            this.fragment.startActivity(this.intent);
        }
    }
}
