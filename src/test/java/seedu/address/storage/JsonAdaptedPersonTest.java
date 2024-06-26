package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
//import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;


public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PAYMENT = "abc";
    private static final String INVALID_PAYMENT_NEG = "-100";
    private static final String INVALID_SUBJECT = " ";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedExam> VALID_EXAM = BENSON.getExams().stream()
            .map(JsonAdaptedExam::new)
            .collect(Collectors.toList());
    private static final String VALID_PAYMENT = BENSON.getPayment().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_SUBJECT = BENSON.getSubject().toString();
    private static final String VALID_UNIQUEID = BENSON.getUniqueId().toString();
    private static final List<JsonAdaptedLog> VALID_LOGS = BENSON.getLogs().getList().stream()
            .map(JsonAdaptedLog::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedExam> VALID_EXAMS = BENSON.getExams().stream()
            .map(JsonAdaptedExam::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    //Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Subject subject,
    //                  Id uniqueId, Set<Exam> exams, Payment payment, LogList logs) {
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                         VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                         VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                    VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                    VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        null, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, INVALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = Subject.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, null,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, VALID_PAYMENT, VALID_LOGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPayment_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECT, VALID_UNIQUEID,
                        VALID_EXAMS, INVALID_PAYMENT, VALID_LOGS);
        String expectedMessage = Payment.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
