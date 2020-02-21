package com.fody.app.ui.send;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonalDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PersonalDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}