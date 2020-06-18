package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.data.models.User;

import java.util.Date;


public class SessionHandler {
    private static final String PREF_NAME = "UserSession";
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;
    private static final String KEY_EXPIRES = "expires";
    private static SessionHandler instance  ;

    public static SessionHandler getInstance( Context context) {
        if (instance == null) {
            instance =  new  SessionHandler(context) ;
        }
        return instance;
    }

    private static final String KEY_EMPTY = "";

    private SessionHandler(Context mContext) {

        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

   
    public void loginUser(User user) {
        mEditor.putString(AppConstants.KEY_USERNAME, user.getUsername());
        mEditor.putString(AppConstants.KEY_NOM, user.getNom());
        mEditor.putString(AppConstants.KEY_PRENOM, user.getPrenom());
        mEditor.putString(AppConstants.KEY_VILLE, user.getVille());
        mEditor.putString(AppConstants.KEY_ADRESSE, user.getAdresse());
        mEditor.putString(AppConstants.KEY_EMAIL, user.getEmail());
        mEditor.putString(AppConstants.KEY_TEL, user.getTel());
        mEditor.putInt(AppConstants.KEY_CODE_POSTAL, user.getCodePostal());
        mEditor.putInt(AppConstants.KEY_ID, user.getId());
        mEditor.putString(AppConstants.KEY_API_KEY, user.getApiKey());






        Date date = new Date();
        long millis = date.getTime() ;
        mEditor.putLong(KEY_EXPIRES, millis);
        mEditor.commit();
    }
   

    /**
     * Checks whether User is logged in
     *
     * @return
     */
    public boolean isLoggedIn() {
    

        long millis = mPreferences.getLong(KEY_EXPIRES, 0);

        /* If shared preferences does not have a value
         then user is not logged in
         */
     
        return millis!=0 ;
    }

   
   
    /**
  

    /**
     * Fetches and returns User details
     *
     * @return User details
     */
    
    public User getUserDetails() {
        //Check if User is logged in first
      /*  if (!isLoggedIn()) {
            return null;
        }*/
        User user = new User();
        user.setUsername(mPreferences.getString(AppConstants.KEY_USERNAME, KEY_EMPTY));
        user.setNom(mPreferences.getString(AppConstants.KEY_NOM, KEY_EMPTY));
        user.setPrenom(mPreferences.getString(AppConstants.KEY_PRENOM, KEY_EMPTY));
        user.setVille(mPreferences.getString(AppConstants.KEY_VILLE,KEY_EMPTY));
        user.setAdresse(mPreferences.getString(AppConstants.KEY_ADRESSE,KEY_EMPTY));
        user.setEmail(mPreferences.getString(AppConstants.KEY_EMAIL,KEY_EMPTY));
        user.setTel(mPreferences.getString(AppConstants.KEY_TEL,KEY_EMPTY));
        user.setCodePostal(mPreferences.getInt(AppConstants.KEY_CODE_POSTAL,0));
        user.setId(mPreferences.getInt(AppConstants.KEY_ID,0));
        user.setApiKey(mPreferences.getString(AppConstants.KEY_API_KEY,KEY_EMPTY));


        return user;
    }





    /**
     * Logs out User by clearing the session
     */
    public void logoutUser(){
        mEditor.remove(AppConstants.KEY_USERNAME);
        mEditor.remove(AppConstants.KEY_NOM);
        mEditor.remove(AppConstants.KEY_PRENOM);
        mEditor.remove(AppConstants.KEY_VILLE);
        mEditor.remove(AppConstants.KEY_ADRESSE);
        mEditor.remove(AppConstants.KEY_EMAIL);
        mEditor.remove(AppConstants.KEY_TEL);
        mEditor.remove(AppConstants.KEY_CODE_POSTAL);
        mEditor.remove(AppConstants.KEY_ID);
        mEditor.remove(AppConstants.KEY_API_KEY);


        mEditor.remove(KEY_EXPIRES);
        mEditor.commit();
    }


}
