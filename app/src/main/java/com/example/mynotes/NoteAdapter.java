package com.example.mynotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.activities.AddingNewNoteActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.mynotes.activities.MainActivity.viewModel;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private final LayoutInflater inflater;

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_TITLE = "TITLE";
    public static final String EXTRA_SUBTITLE = "SUBTITLE";
    public static final String EXTRA_DEADLINE_CHECKBOX = "CHECKBOX";
    public static final String EXTRA_DEADLINE = "DEADLINE";
    public static boolean savingUpdate = false;
    private final Calendar currentCalendar = Calendar.getInstance();

    public NoteAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setNotes(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = noteList.get(position);

        holder.titleString.setText(note.getTitle());
        if (note.getTitle().equals("")) {
            holder.titleString.setVisibility(View.GONE);
        } else {
            holder.titleString.setVisibility(View.VISIBLE);
        }

        holder.subtitleString.setText(note.getSubtitle());
        if (note.getSubtitle().equals("")) {
            holder.subtitleString.setVisibility(View.GONE);
        } else {
            holder.subtitleString.setVisibility(View.VISIBLE);
        }

        long deadline = note.getDeadline();
        if (deadline == 0) {
            holder.deadlineString.setVisibility(View.GONE);
        } else {
            Context context = holder.itemView.getContext();
            holder.deadlineString.setText(getDate(deadline, context));
            holder.deadlineString.setVisibility(View.VISIBLE);
        }

        Context deadlineStringContext = holder.deadlineString.getContext();
        if ((currentCalendar.getTimeInMillis() - deadline) > 0) {
            holder.deadlineString.setTextColor(ContextCompat.getColor(deadlineStringContext, R.color.red));
        } else if ((currentCalendar.getTimeInMillis() - deadline) < 0 &&
                deadline - (currentCalendar.getTimeInMillis()) < 86400000) {
            holder.deadlineString.setTextColor(ContextCompat.getColor(deadlineStringContext, R.color.yellow));
        } else {
            holder.deadlineString.setTextColor(ContextCompat.getColor(deadlineStringContext, R.color.darkerGray));
        }
    }

    private String getDate(long time, Context context) {
        Date date = new Date(time);
        return DateUtils.formatDateTime(context, date.getTime(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE |
                        DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME |
                        DateUtils.FORMAT_24HOUR);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (noteList != null) {
            return noteList.size();
        } else {
            return 0;
        }
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final TextView titleString;
        private final TextView subtitleString;
        private final TextView deadlineString;

        private NoteViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            titleString = itemView.findViewById(R.id.titleString);
            subtitleString = itemView.findViewById(R.id.subtitleString);
            deadlineString = itemView.findViewById(R.id.deadlineString);

            cardView.setOnLongClickListener(v -> {
                String title = titleString.getText().toString();
                int maxLengthTitle = 30;
                if (titleString.length() > maxLengthTitle) {
                    title = title.substring(0, maxLengthTitle);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(R.string.deletingNote)
                        .setMessage(title + "  ?");

                builder.setPositiveButton(R.string.yes, (dialog, i) -> {
                    viewModel.delete(noteList.get(getAdapterPosition()));
                    Toast.makeText(v.getContext(), R.string.toastNoteDeleted, Toast.LENGTH_LONG).show();
                });

                builder.setNegativeButton(R.string.not, (dialog, i) -> {
                    Toast.makeText(v.getContext(), R.string.toastCancelDeletion, Toast.LENGTH_LONG).show();
                    dialog.cancel();
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                notifyDataSetChanged();
                return false;
            });

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), AddingNewNoteActivity.class);
                Note note = noteList.get(getAdapterPosition());
                savingUpdate = true;
                intent.putExtra(EXTRA_ID, note.getId());
                intent.putExtra(EXTRA_TITLE, note.getTitle());
                intent.putExtra(EXTRA_SUBTITLE, note.getSubtitle());
                intent.putExtra(EXTRA_DEADLINE_CHECKBOX, note.isDeadlineCheckbox());
                intent.putExtra(EXTRA_DEADLINE, note.getDeadline());
                v.getContext().startActivity(intent);
            });
        }
    }
}