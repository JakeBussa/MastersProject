package enums;

public enum Keyword {

    SELECT, MIN, MAX, AVG, COUNT, SUM, FROM, JOIN, USING, WHERE, AND, OR,
    GROUP, BY, HAVING, CREATE, TABLE, NUMBER, CHAR, DATE, DROP, INSERT, INTO, VALUES, DELETE, UPDATE,
    SET, GRANT, ALTER, INDEX, REFERENCES, ALL, PRIVILEGES, TO, REVOKE, ON;

    /**
     * Basically takes and returns a string in uppercase if it's a keyword, else, just returns the String.
     * @param toUppercase is the string to convert to uppercase
     * @return the string in uppercase if it's a keyword, else, the string provided
     */
    public static String toUppercase(String toUppercase) {
        for (Keyword keyword : Keyword.values()) {
            if (keyword.toString().equalsIgnoreCase(toUppercase)) {
                return toUppercase.toUpperCase();
            }
        }
        return toUppercase;
    }

    /**
     * @param value is the value to convert
     * @return the value as a keyword, ignoring case
     */
    public static Keyword toKeyword(String value) {
        return valueOf(value.toUpperCase());
    }

    public static boolean isAggregateFunction(Keyword candidate) {
        return  candidate == Keyword.MIN || candidate == Keyword.MAX   ||
                candidate == Keyword.AVG || candidate == Keyword.COUNT ||
                candidate == Keyword.SUM;
    }
}