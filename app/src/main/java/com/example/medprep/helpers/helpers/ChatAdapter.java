package com.example.medprep.helpers.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import com.example.medprep.Menu;
import com.example.medprep.R;
import com.example.medprep.activity.AddProfileFragment;
import com.example.medprep.db.db.Chat;
import com.example.medprep.db.db.ChatMessage;
import com.example.medprep.db.db.ChatSQLiteDBHelper;

public class ChatAdapter extends ArrayAdapter<Chat> {

    Context context;
    AddProfileFragment.AddProfileFragmentListener mListener;
    List<Chat> chatList;

    View.OnClickListener openChatOnClickListener = v -> {
        Chat chat = (Chat) v.getTag();
        GlobalVariables.getInstance().setCurrentChat(chat);
        if (mListener != null) {
            mListener.callback(context.getString(R.string.openChat));
        }
    };

    //from here
    public ChatAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }//to here

    /*public ChatAdapter(Menu activity, ArrayList<Chat> chats) {
        super(activity, 0, chats);
        this.context = activity;
        this.chatList = chats;
        setmListener(activity);
    }*/

    @Nullable
    @Override
    public Chat getItem(int position) {
        return chatList.get(position);
    }

    public void setmListener(AddProfileFragment.AddProfileFragmentListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Chat chat = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat, parent, false);
        }
        if (chat != null) {
            String date = ChatSQLiteDBHelper.DB_DATE_MESSAGE_FORMAT.format(chat.getDate());
            TextView tvChatDate = convertView.findViewById(R.id.chatDate);
            TextView tvChatTime = convertView.findViewById(R.id.chatTime);
            tvChatDate.setText(getFirstMessage(chat));
            tvChatTime.setText(date.substring(0, 10) + "    " + date.substring(11));
            convertView.setTag(chat);
            convertView.setOnClickListener(openChatOnClickListener);
        }
        return convertView;
    }

    private String getFirstMessage(Chat chat) {
        List<ChatMessage> messages = ChatSQLiteDBHelper.getAllMessagesForChat(this.context, chat.getId());
        if (messages != null) {
            return messages.get(1).getMessage();
        }
        return "";
    }

}
