package com.android.hootr.test.data.api.models.login;

public class LoginResultServer {

    public boolean isError;

    public String descriptionError;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }
}
