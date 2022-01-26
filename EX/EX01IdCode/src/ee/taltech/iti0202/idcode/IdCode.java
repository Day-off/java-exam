
package ee.taltech.iti0202.idcode;

public class IdCode {

    private final String idCodeValue;

    enum Gender {
        MALE, FEMALE
    }

    /**
     * Method returns the id code.
     *
     * @return id code.
     */
    public String getIdCodeValue() {
        return idCodeValue;
    }

    public IdCode(String idCodeValue) {
        this.idCodeValue = idCodeValue;
    }

    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        return idCodeValue.length() == 11 && idCodeValue.matches("[0-9]+");
    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        String sex = idCodeValue.substring(0, 0),
               day = idCodeValue.substring(1, 3),
               month = idCodeValue.substring(3,5),
               year = idCodeValue.substring(5,7),
               city = idCodeValue.substring(7,10);

        return null;
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        int sex = idCodeValue.charAt(0);

        if (sex % 2 == 0) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }

    /**
     * Get person's birth location.
     *
     * @return String with the person's birth place.
     */
    public String getBirthPlace() {
        return null;
    }

    /**
     * Get the year that the person was born in.
     *
     * @return int with person's birth year.
     */
    public int getFullYear() {
        return 0;
    }

    /**
     * Check if gender number is correct.
     *
     * @return boolean describing whether the gender number is correct.
     */
    private boolean isGenderNumberCorrect() {
        return false;
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     */
    private boolean isYearNumberCorrect() {
        return false;
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    private boolean isMonthNumberCorrect() {
        return false;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        return false;
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        return false;
    }

    /**
     * Check if the given year is a leap year.
     *
     * @param fullYear
     * @return boolean describing whether the given year is a leap year.
     */
    private boolean isLeapYear(int fullYear) {
        return false;
    }

    /**
     * Run tests.
     *
     * @param args info.
     */
    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("37605030299");
        System.out.println(validMaleIdCode.isCorrect());
        System.out.println(validMaleIdCode.getInformation());
        System.out.println(validMaleIdCode.getGender());
        System.out.println(validMaleIdCode.getBirthPlace());
        System.out.println(validMaleIdCode.getFullYear());
        System.out.println(validMaleIdCode.isGenderNumberCorrect());
        System.out.println(validMaleIdCode.isYearNumberCorrect());
        System.out.println(validMaleIdCode.isMonthNumberCorrect());
        System.out.println(validMaleIdCode.isDayNumberCorrect());
        System.out.println(validMaleIdCode.isControlNumberCorrect());
        System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
    }

}
