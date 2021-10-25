package com.example.medprep;

import com.example.medprep.model.Results;
import com.example.medprep.remote.IGoogleService;
import com.example.medprep.remote.RetrofitClient;

/**
 * Created by vikas on 23/6/18.
 */

public class Common {

    private static final String GOOGLE_API_URL="https://maps.googleapis.com/";
    public static Results currentResult;

    public static IGoogleService getGoogleService(){
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleService.class);
    }
}
