package com.example.tetrisrecords;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/*
    Description: A class for creating a custom adapter to be used on the view in the addViewActivity
    in order to display previously entered scores in a neat format.
 */
@SuppressWarnings("ALL")
public class CustomAdapter extends ArrayAdapter<GameStat> {
    private Context mContext;
    private String m_Text = "";
    private List<GameStat> gameList;
    CustomAdapter(Context context, ArrayList<GameStat> list) {
        super(context, 0, list);
        mContext = context;
        gameList = list;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final DataBase db = new DataBase(getContext()); // allows access to database from SQLite
        View game = convertView;
        if(game == null) {
            game = LayoutInflater.from(mContext).inflate(R.layout.adapter_view_layout, parent, false);
        }
        final String date = getItem(position).getDate();
        final GameStat currentGame = gameList.get(position);
        final int score = getItem(position).getScore();
        int level = getItem(position).getLevel();
        final TextView tvDate = game.findViewById(R.id.dateView);
        TextView tvScore = game.findViewById(R.id.scoreView);
        TextView tvLevel = game.findViewById(R.id.levelView);
        ImageView ivEdit = game.findViewById(R.id.editView);
        ImageView ivX = game.findViewById(R.id.xView);
        tvDate.setTextColor(Color.WHITE);
        tvScore.setTextColor(Color.WHITE);
        tvLevel.setTextColor(Color.WHITE);
        tvDate.setText(date);
        tvScore.setText(Integer.toString(score));
        tvLevel.setText(Integer.toString(level));
        ivX.setOnClickListener(new View.OnClickListener() { // if user clicks X button
            @Override
            public void onClick(View v) {
                db.deleteGame(currentGame); // remove game from database
                gameList.remove(currentGame);
                notifyDataSetChanged();
            }
        });
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder edit = new AlertDialog.Builder(getContext()); // create alert for user to enter new values
                edit.setTitle("Edit Score");
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText dateEdit= new EditText(getContext());
                dateEdit.setHint("Date");
                dateEdit.setInputType(InputType.TYPE_CLASS_DATETIME);
                layout.addView(dateEdit);
                final EditText scoreEdit = new EditText(getContext());
                scoreEdit.setHint("Score");
                scoreEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(scoreEdit);
                final EditText levelEdit = new EditText(getContext());
                levelEdit.setHint("Starting level");
                levelEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(levelEdit);
                edit.setView(layout);
                edit.setPositiveButton("Save", new DialogInterface.OnClickListener() { // if user clicks Save button
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (!scoreEdit.getText().toString().isEmpty()) { // if user entered a score
                            if(levelEdit.getText().toString().isEmpty()) // if user didn't enter a level
                                levelEdit.setText("0");
                            DataBase db = new DataBase(getContext());
                            String date = dateEdit.getText().toString();
                            date = date.replaceAll("\\s", "");
                            if (date.isEmpty()) // if user didn't empty a date
                                db.editGame(currentGame, "''", Integer.parseInt(
                                        scoreEdit.getText().toString()), Integer.parseInt(levelEdit.
                                        getText().toString()));

                            else // if user did enter a date
                                db.editGame(currentGame, date,
                                        Integer.parseInt(scoreEdit.getText().toString()),
                                        Integer.parseInt(levelEdit.getText().toString()));
                            Toast.makeText(getContext(), "Score Changed", Toast.LENGTH_LONG).show();
                            currentGame.setDate(dateEdit.getText().toString());
                            currentGame.setScore(Integer.parseInt(scoreEdit.getText().toString()));
                            currentGame.setLevel(Integer.parseInt(levelEdit.getText().toString()));
                                notifyDataSetChanged();
                            }
                        else // if user didn't enter a score
                            Toast.makeText(getContext(), "Please enter a score!", Toast.LENGTH_LONG).show();
                    }
                });
                edit.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // if user clicks Cancel button
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                edit.show(); // display Alert
            }
        });
        return game;
    }
}
