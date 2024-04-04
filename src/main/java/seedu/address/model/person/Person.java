package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    //private String uniqueId;
    private Id uniqueId;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Subject subject;
    private final Set<Exam> exams;
    private final Payment payment;
    private final LogList logs;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Subject subject,
                  Id uniqueId, Set<Exam> exams, Payment payment, LogList logs) {
        requireAllNonNull(name, phone, email, address, tags, subject, payment);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.subject = subject;
        this.uniqueId = uniqueId;
        this.exams = exams == null ? new HashSet<>() : new HashSet<>(exams);
        this.payment = payment;
        this.logs = logs;
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, Subject subject, Set<Exam> exams, Payment payment, LogList logs) {
        requireAllNonNull(name, phone, email, address, tags, subject);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.subject = subject;
        this.exams = exams == null ? new HashSet<>() : new HashSet<>(exams);
        this.payment = payment;
        this.logs = logs;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Subject getSubject() {
        return subject;
    }

    public Payment getPayment() {
        return payment;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /** Returns the uniqueId of the person */
    public Id getUniqueId() {
        return uniqueId;
    }
    /** Sets the uniqueId of the person */
    public Id setUniqueId(Id uniqueId) {
        return this.uniqueId = uniqueId;
    }

    /** Sets the exams of the person */
    public void setExams(Set<Exam> exams) {
        this.exams.clear();
        this.exams.addAll(exams);
    }

    /** Returns the exams of the person */
    public Set<Exam> getExams() {
        return Collections.unmodifiableSet(exams);
    }

    public LogList getLogs() {
        return logs;
    }

    public void addLog(Log entry) {
        logs.addEntry(entry);
        setLearningStyle(entry);
    }

    public void setLearningStyle(Log entry) {
        String learningStyle = entry.getLearningStyle();
        if (!(tags.contains(learningStyle) && learningStyle.isEmpty())) {
            addTag(learningStyle);
        }
        
    }

    public void addTag(String tag) {
        tags.add(new Tag(tag));
    }


    /**
     * Returns true if both persons have the same ID.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getUniqueId().equals(this.uniqueId);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && subject.equals(otherPerson.subject)
                && tags.equals(otherPerson.tags)
                && exams.equals(otherPerson.exams)
                && payment.equals(otherPerson.payment);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, uniqueId, exams, payment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("subject", subject)
                .add("tags", tags)
                .add("exams", exams)
                .add("payment", payment)
                .toString();
    }

}
