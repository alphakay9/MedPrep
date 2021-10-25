package com.example.medprep.helpers.helpers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Optional;

import com.example.medprep.db.db.Chat;
import com.example.medprep.db.db.User;

public class GlobalVariables {
    private static final GlobalVariables INSTANCE = new GlobalVariables();

    private User currentUser;
    private Chat currentChat;

    private GlobalVariables() {
    }

    public static GlobalVariables getInstance() {
        return INSTANCE;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(this.currentUser);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Optional<Chat> getCurrentChat() {
        return Optional.ofNullable(this.currentChat);
    }

    public void setCurrentChat(Chat currentChat) {
        this.currentChat = currentChat;
    }
}