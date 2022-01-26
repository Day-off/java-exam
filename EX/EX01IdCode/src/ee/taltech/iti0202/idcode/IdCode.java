
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

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    /**
     * Get person's birth location.
     *
     * @return String with the person's birth place.
     */
    public String getBirthPlace() {
        String city = idCodeValue.substring(7,10);
        int code = Integer.parseInt(city);
        if (1 <= code && code <= 10) {
            return "Kuressaare";
        } else if (11 <= code && code <= 20) {
            return "Tartu";
        } else if ((21 <= code && code <= 220) || (471 <= code && code <= 490)) {
            return "Tallinn";
        } else if (221 <= code && code <= 270) {
            return "Kohtla-Järve";
        } else if (271 <= code && code <= 370) {
            return "Tartu";
        } else if (371 <= code && code <= 420) {
            return "Narva";
        } else if (421 <= code && code <= 470) {
            return "Pärnu";
        } else if (491 <= code && code <= 520) {
            return "Paide";
        } else if (521 <= code && code <= 570) {
            return "Rakvere";
        } else if (571 <= code && code <= 600) {
            return "Valga";
        } else if (601 <= code && code <= 650) {
            return "Viljandi";
        } else if (651 <= code && code <= 710) {
            return "Võru";
        }
        return "unknown";
    }

    /**
     * Get the year that the person was born in.
     *
     * @return int with person's birth year.
     */
    public int getFullYear() {
        char sex = idCodeValue.charAt(0);
        String year = idCodeValue.substring(1,3);

        if (sex == '1' || sex == '2') {
            return Integer.parseInt("18" + year);
        } else if (sex == '3' || sex == '4') {
            return Integer.parseInt("19" + year);
        }
        return Integer.parseInt("20" + year);
    }

    /**
     * Check if gender number is correct.
     *
     * @return boolean describing whether the gender number is correct.
     */
    private boolean isGenderNumberCorrect() {
        return Integer.parseInt(String.valueOf(idCodeValue.charAt(0))) <= 6;
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
     * @param *fullYear
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
