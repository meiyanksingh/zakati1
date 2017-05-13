package com.mks.zakati.interfaces;

/**
 * Created by rahil on 13/11/15.
 */
public interface BaseInterfaces {


    /**
     * Method to initialize ui parameters and will be called just after setContentView Method in any activity
     */
     void initializeUi();

    /**
     * Method to return the activities layout res id
     */
     int getLayoutId();
}
