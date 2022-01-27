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
        return idCodeValue.length() == 11 && idCodeValue.matches("[0-9]+")
                && isControlNumberCorrect() && isDayNumberCorrect() && isGenderNumberCorrect()
                && isMonthNumberCorrect() && isYearNumberCorrect();
    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        return "This is a " + getGender() + " born on " +
                idCodeValue.substring(5, 7) + "." + idCodeValue.substring(3, 5) +
                "." + getFullYear() + " in " + getBirthPlace();
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
        String city = idCodeValue.substring(7, 10);
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
        String year = idCodeValue.substring(1, 3);

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
        return getFullYear() < 2100;
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    private boolean isMonthNumberCorrect() {
        return Integer.parseInt(idCodeValue.substring(3, 5)) < 13 && Integer.parseInt(idCodeValue.substring(3, 5)) > 0;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        String month = idCodeValue.substring(3, 5), day = idCodeValue.substring(5, 7);

        boolean day_30 = month.equals("04") || month.equals("05") || month.equals("08") || month.equals("10");

        if ((getFullYear() % 4 == 0 && getFullYear() % 100 != 0) || (getFullYear() % 400 == 0)) {
            if (month.equals("02")) {
                return Integer.parseInt(day) <= 29;
            } else if (day_30) {
                return Integer.parseInt(day) <= 30;
            } else {
                return Integer.parseInt(day) <= 31;
            }
        }
        if (month.equals("02")) {
            return Integer.parseInt(day) <= 28;
        } else if (day_30) {
            return Integer.parseInt(day) <= 30;
        } else {
            return Integer.parseInt(day) <= 31;
        }
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        int sum1 = Character.getNumericValue(idCodeValue.charAt(0))
                + Character.getNumericValue(idCodeValue.charAt(1)) * 2
                + Character.getNumericValue(idCodeValue.charAt(2)) * 3
                + Character.getNumericValue(idCodeValue.charAt(3)) * 4
                + Character.getNumericValue(idCodeValue.charAt(4)) * 5
                + Character.getNumericValue(idCodeValue.charAt(5)) * 6
                + Character.getNumericValue(idCodeValue.charAt(6)) * 7
                + Character.getNumericValue(idCodeValue.charAt(7)) * 8
                + Character.getNumericValue(idCodeValue.charAt(8)) * 9
                + Character.getNumericValue(idCodeValue.charAt(9));
        int sum2 = Character.getNumericValue(idCodeValue.charAt(0)) * 3
                + Character.getNumericValue(idCodeValue.charAt(1)) * 4
                + Character.getNumericValue(idCodeValue.charAt(2)) * 5
                + Character.getNumericValue(idCodeValue.charAt(3)) * 6
                + Character.getNumericValue(idCodeValue.charAt(4)) * 7
                + Character.getNumericValue(idCodeValue.charAt(5)) * 8
                + Character.getNumericValue(idCodeValue.charAt(6)) * 9
                + Character.getNumericValue(idCodeValue.charAt(7))
                + Character.getNumericValue(idCodeValue.charAt(8)) * 2
                + Character.getNumericValue(idCodeValue.charAt(9)) * 3;

        int k_num = sum1 % 11, k_num2 = sum2 % 11;
        boolean res = k_num == Character.getNumericValue(idCodeValue.charAt(10)),
                res2 = k_num2 == Character.getNumericValue(idCodeValue.charAt(10));
        if (res) {
            return true;
        } else {
            if (res2) {
                return true;
            } else {
                return Character.getNumericValue(idCodeValue.charAt(10)) == 10;
            }
        }
    }

    /**
     * Check if the given year is a leap year.
     *
     * @return boolean describing whether the given year is a leap year.
     */
    private boolean isLeapYear(int fullYear) {
        return ((getFullYear() % 4 == 0 && getFullYear() % 100 != 0) || (getFullYear() % 400 == 0));
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
