package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Person's log details in the address book.
 */
public class Log {
    private final String date;
    private final String learningStyle;
    private final String hours;
    private final String lessonContent;
    private final String notes;

    /**
     * Constructs a {@code Log}.
     *
     * @param learningStyle A valid learning style.
     * @param hours A valid number of hours.
     * @param lessonContent A valid lesson content.
     * @param notes A valid note.
     */
    public Log(String learningStyle, String hours, String lessonContent, String notes, String date) {
        requireAllNonNull(learningStyle, hours, lessonContent, notes);
        if (date == null) {
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dateFormat.format(currentDate);
        }

        this.date = date;
        this.learningStyle = learningStyle;
        this.hours = hours;
        this.lessonContent = lessonContent;
        this.notes = notes;
    }

    public String getDate() {
        return this.date;
    }

    public String getLearningStyle() {
        return this.learningStyle;
    }

    public String getHours() {
        return this.hours;
    }

    public String getLessonContent() {
        return this.lessonContent;
    }

    public String getNotes() {
        return this.notes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Log on %s\n", date))
                .append("Hours: " + hours + "\n")
                .append("Learning Style: " + learningStyle + "\n")
                .append("Lesson Content: " + lessonContent + "\n")
                .append("Notes: " + notes + "\n");
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Log)) {
            return false;
        }

        Log otherLog = (Log) other;
        return otherLog.getDate().equals(getDate())
                && otherLog.getLearningStyle().equals(getLearningStyle())
                && otherLog.getHours().equals(getHours())
                && otherLog.getLessonContent().equals(getLessonContent())
                && otherLog.getNotes().equals(getNotes());
    }
}
