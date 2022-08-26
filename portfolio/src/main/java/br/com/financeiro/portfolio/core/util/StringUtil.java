package br.com.financeiro.portfolio.core.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public final class StringUtil {

    private StringUtil() {
        super();
    }

    /**
     * Verifica se a String passada por parâmetro é nula ou vazia
     * 
     * @param str - texto, pode ser nulo
     * @return true se a String for nula ou vazia
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Verifica se a String passada por parâmetro é nula, vazia ou apresenta espaços
     * em branco
     * 
     * @param str - texto, pode ser nulo
     * @return true se a String for nula, vazia ou apresentar espaços em branco
     */
    public static boolean isNullOrWhiteSpace(String str) {
        return isNullOrEmpty(str) || str.trim().length() == 0;
    }

    /**
     * Combina duas Strings com uma barra "/"
     * 
     * @param str1 - 1a parte do endere篍
     * @param str2 - 2a parte do endere篍
     * @return endere篠combinado com uma barra "/"
     */
    public static String combineStringsToPath(String str1, String str2) {
        String path = "";

        if (str1 != null && str2 != null) {
            if (str1.endsWith("/")) {
                if (str2.startsWith("/")) {
                    path = str1.substring(0, str1.length() - 1) + str2;
                } else {
                    path = str1 + str2;
                }
            } else {
                if (str2.startsWith("/")) {
                    path = str1 + str2;
                } else {
                    path = String.format("%s/%s", str1, str2);
                }
            }
        } else if (str1 == null && str2 != null) {
            path = str2;
        } else if (str1 != null) {
            path = str1;
        }

        return path;
    }

    /**
     * Append the given String to the given String array, returning a new array
     * consisting of the input array contents plus the given String.
     * 
     * @param array - the array to append to (can be null)
     * @param str   - the String to append
     * @return the new array (never null)
     */
    public static String[] addStringToArray(@Nullable String[] array, String str) {
        return StringUtils.addStringToArray(array, str);
    }

    /**
     * Apply the given relative path to the given Java resource path, assuming
     * standard Java folder separation (i.e. "/" separators).
     * 
     * @param path         - the path to start from (usually a full file path)
     * @param relativePath - the relative path to apply (relative to the full file
     *                     path above)
     * @return the full file path that results from applying the relative path
     */
    public static String applyRelativePath(String path, String relativePath) {
        return StringUtils.applyRelativePath(path, relativePath);
    }

    /**
     * Convert a String array into a comma delimited String (i.e., CSV). Useful for
     * toString() implementations.
     * 
     * @param arr - the array to display (potentially null or empty)
     * @return the delimited String
     */
    public static String arrayToCommaDelimitedString(@Nullable Object[] arr) {
        return StringUtils.arrayToCommaDelimitedString(arr);
    }

    /**
     * Convert a String array into a delimited String (e.g. CSV). Useful for
     * toString() implementations.
     * 
     * @param arr   - the array to display (potentially null or empty)
     * @param delim - the delimiter to use (typically a ",")
     * @return the delimited String
     */
    public static String arrayToDelimitedString(@Nullable Object[] arr, String delim) {
        return StringUtils.arrayToDelimitedString(arr, delim);
    }

    /**
     * Capitalize a String, changing the first letter to upper case as per
     * Character.toUpperCase(char). No other letters are changed.
     * 
     * @param str - the String to capitalize
     * @return the capitalized String
     */
    public static String capitalize(String str) {
        return StringUtils.capitalize(str);
    }

    /**
     * Normalize the path by suppressing sequences like "path/.." and inner simple
     * dots.
     * 
     * The result is convenient for path comparison. For other uses, notice that
     * Windows separators ("\") are replaced by simple slashes.
     * 
     * NOTE that cleanPath should not be depended upon in a security context. Other
     * mechanisms should be used to prevent path-traversal issues.
     * 
     * @param path - the original path
     * @return the normalized path
     */
    public static String cleanPath(String path) {
        return StringUtils.cleanPath(path);
    }

    /**
     * Convert a Collection into a delimited String (e.g., CSV).
     * 
     * Useful for toString() implementations.
     * 
     * @param coll - the Collection to convert (potentially null or empty)
     * @return the delimited String
     */
    public static String collectionToCommaDelimitedString(@Nullable Collection<?> coll) {
        return StringUtils.collectionToCommaDelimitedString(coll);
    }

    /**
     * Convert a Collection into a delimited String (e.g. CSV).
     * 
     * Useful for toString() implementations.
     * 
     * @param coll  - the Collection to convert (potentially null or empty)
     * @param delim - the delimiter to use (typically a ",")
     * @return
     */
    public static String collectionToDelimitedString(@Nullable Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim);
    }

    /**
     * Convert a Collection to a delimited String (e.g. CSV).
     * 
     * Useful for toString() implementations.
     * 
     * @param coll   - the Collection to convert (potentially null or empty)
     * @param delim  - the delimiter to use (typically a ",")
     * @param prefix - the String to start each element with
     * @param suffix - the String to end each element with
     * @return the delimited String
     */
    public static String collectionToDelimitedString(@Nullable Collection<?> coll, String delim, String prefix,
            String suffix) {
        return StringUtils.collectionToDelimitedString(coll, delim, prefix, suffix);
    }

    /**
     * Convert a comma delimited list (e.g., a row from a CSV file) into a set.
     * 
     * Note that this will suppress duplicates, and as of 4.2, the elements in the
     * returned set will preserve the original order in a LinkedHashSet.
     * 
     * @param str - the input String (potentially null or empty)
     * @return set of String entries in the list
     */
    public static Set<String> commaDelimitedListToSet(@Nullable String str) {
        return StringUtils.commaDelimitedListToSet(str);
    }

    /**
     * Convert a comma delimited list (e.g., a row from a CSV file) into an array of
     * strings.
     * 
     * @param str - the input String (potentially null or empty)
     * @return an array of strings, or the empty array in case of empty input
     */
    public static String[] commaDelimitedListToStringArray(@Nullable String str) {
        return StringUtils.commaDelimitedListToStringArray(str);
    }

    /**
     * Concatenate the given String arrays into one, with overlapping array elements
     * included twice.
     * 
     * The order of elements in the original arrays is preserved.
     * 
     * @param array1 - the first array (can be null)
     * @param array2 - the second array (can be null)
     * @return the new array (null if both given arrays were null)
     */
    public static String[] concatenateStringArrays(@Nullable String[] array1, @Nullable String[] array2) {
        return StringUtils.concatenateStringArrays(array1, array2);
    }

    /**
     * Check whether the given CharSequence contains any whitespace characters.
     * 
     * @param str - the CharSequence to check (may be null)
     * @return true if the CharSequence is not empty and contains at least 1
     *         whitespace character
     */
    public static boolean containsWhitespace(@Nullable CharSequence str) {
        return StringUtils.containsWhitespace(str);
    }

    /**
     * Check whether the given String contains any whitespace characters.
     * 
     * @param str - the String to check (may be null)
     * @return true if the String is not empty and contains at least 1 whitespace
     *         character
     */
    public static boolean containsWhitespace(@Nullable String str) {
        return StringUtils.containsWhitespace(str);
    }

    /**
     * Count the occurrences of the substring sub in string str
     * 
     * @param str - string to search in
     * @param sub - string to search for
     * @return occurrences of the substring sub in string str
     */
    public static int countOccurrencesOf(String str, String sub) {
        return StringUtils.countOccurrencesOf(str, sub);
    }

    /**
     * Delete all occurrences of the given substring
     * 
     * @param inString - the original String
     * @param pattern  - the pattern to delete all occurrences of
     * @return the resulting String
     */
    public static String delete(String inString, String pattern) {
        return StringUtils.delete(inString, pattern);
    }

    /**
     * Delete any character in a given String
     * 
     * @param inString      - the original String
     * @param charsToDelete - a set of characters to delete. E.g. "az\n" will delete
     *                      'a's, 'z's and new lines.
     * @return the resulting String
     */
    public static String deleteAny(String inString, @Nullable String charsToDelete) {
        return StringUtils.deleteAny(inString, charsToDelete);
    }

    /**
     * Take a String that is a delimited list and convert it into a String array.
     * 
     * A single delimiter may consist of more than one character, but it will still
     * be considered as a single delimiter string, rather than as a bunch of
     * potential delimiter characters, in contrast to
     * tokenizeToStringArray(java.lang.String, java.lang.String).
     * 
     * @param str       - the input String (potentially null or empty)
     * @param delimiter - the delimiter between elements (this is a single
     *                  delimiter, rather than a bunch individual delimiter
     *                  characters)
     * @return an array of the tokens in the list
     */
    public static String[] delimitedListToStringArray(@Nullable String str, @Nullable String delimiter) {
        return StringUtils.delimitedListToStringArray(str, delimiter);
    }

    /**
     * Take a String that is a delimited list and convert it into a String array.
     * 
     * A single delimiter may consist of more than one character, but it will still
     * be considered as a single delimiter string, rather than as a bunch of
     * potential delimiter characters, in contrast to
     * tokenizeToStringArray(java.lang.String, java.lang.String).
     * 
     * @param str           - the input String (potentially null or empty)
     * @param delimiter     - the delimiter between elements (this is a single
     *                      delimiter, rather than a bunch individual delimiter
     *                      characters)
     * @param charsToDelete - a set of characters to delete; useful for deleting
     *                      unwanted line breaks: e.g. "\r\n\f" will delete all new
     *                      lines and line feeds in a String
     * @return an array of the tokens in the list
     */
    public static String[] delimitedListToStringArray(@Nullable String str, @Nullable String delimiter,
            @Nullable String charsToDelete) {
        return StringUtils.delimitedListToStringArray(str, delimiter, charsToDelete);
    }

    /**
     * Test if the given String ends with the specified suffix, ignoring upper/lower
     * case.
     * 
     * @param str    - the String to check
     * @param suffix the suffix to look for
     * @return return if the given String ends with the specified suffix
     */
    public static boolean endsWithIgnoreCase(@Nullable String str, @Nullable String suffix) {
        return StringUtils.endsWithIgnoreCase(str, suffix);
    }

    /**
     * Extract the filename from the given Java resource path, e.g.
     * "mypath/myfile.txt" &rarr; "myfile.txt".
     * 
     * @param path - the file path (may be null)
     * @return the extracted filename, or null if none
     */
    public static String getFilename(@Nullable String path) {
        return StringUtils.getFilename(path);
    }

    /**
     * Extract the filename extension from the given Java resource path, e.g.
     * "mypath/myfile.txt" -> "txt".
     * 
     * @param path - the file path (may be null)
     * @return the extracted filename extension, or null if none
     */
    public static String getFilenameExtension(@Nullable String path) {
        return StringUtils.getFilenameExtension(path);
    }

    /**
     * Check whether the given String contains actual text.
     * 
     * More specifically, this method returns true if the String is not null, its
     * length is greater than 0, and it contains at least one non-whitespace
     * character.
     * 
     * @param str - the String to check (may be null)
     * @return true if the String is not null, its length is greater than 0, and it
     *         does not contain whitespace only
     */
    public static boolean hasText(@Nullable String str) {
        return StringUtils.hasText(str);
    }

    /**
     * Test if the given String matches the given single character.
     * 
     * @param str             - the String to check
     * @param singleCharacter - the character to compare to
     * @return return if the given String matches the given single character
     */
    public static boolean matchesCharacter(@Nullable String str, char singleCharacter) {
        return StringUtils.matchesCharacter(str, singleCharacter);
    }

    /**
     * Parse the given String value into a Locale, accepting the Locale.toString()
     * format as well as BCP 47 language tags as specified by
     * Locale.forLanguageTag(java.lang.String).
     * 
     * @param localeValue - the locale value: following either Locale's toString()
     *                    format ("en", "en_UK", etc), also accepting spaces as
     *                    separators (as an alternative to underscores), or BCP 47
     *                    (e.g. "en-UK")
     * @return a corresponding Locale instance, or null if none
     */
    public static Locale parseLocale(String localeValue) {
        return StringUtils.parseLocale(localeValue);
    }

    /**
     * Parse the given String representation into a Locale.
     * 
     * For many parsing scenarios, this is an inverse operation of Locale's
     * toString, in a lenient sense. This method does not aim for strict Locale
     * design compliance; it is rather specifically tailored for typical Spring
     * parsing needs.
     * 
     * Note: This delegate does not accept the BCP 47 language tag format. Please
     * use parseLocale(java.lang.String) for lenient parsing of both formats.
     * 
     * @param localeString - the locale String: following Locale's toString() format
     *                     ("en", "en_UK", etc), also accepting spaces as separators
     *                     (as an alternative to underscores)
     * @return a corresponding Locale instance, or null if none
     */
    public static Locale parseLocaleString(String localeString) {
        return StringUtils.parseLocaleString(localeString);
    }

    /**
     * Parse the given timeZoneString value into a TimeZone.
     * 
     * @param timeZoneString - the time zone String, following
     *                       TimeZone.getTimeZone(String) but throwing
     *                       IllegalArgumentException in case of an invalid time
     *                       zone specification
     * @return a corresponding TimeZone instance
     */
    public static TimeZone parseTimeZoneString(String timeZoneString) {
        return StringUtils.parseTimeZoneString(timeZoneString);
    }

    /**
     * Compare two paths after normalization of them.
     * 
     * @param path1 - first path for comparison
     * @param path2 - second path for comparison
     * @return whether the two paths are equivalent after normalization
     */
    public static boolean pathEquals(String path1, String path2) {
        return StringUtils.pathEquals(path1, path2);
    }

    /**
     * Quote the given String with single quotes.
     * 
     * @param str - the input String (e.g. "myString")
     * @return the quoted String (e.g. "'myString'"), or null if the input was null
     */
    public static String quote(@Nullable String str) {
        return StringUtils.quote(str);
    }

    /**
     * Turn the given Object into a String with single quotes if it is a String;
     * keeping the Object as-is else.
     * 
     * @param obj - the input Object (e.g. "myString")
     * @return the quoted String (e.g. "'myString'"), or the input object as-is if
     *         not a String
     */
    public static Object quoteIfString(@Nullable Object obj) {
        return StringUtils.quoteIfString(obj);
    }

    /**
     * Remove duplicate strings from the given array.
     * 
     * @param array - the String array (potentially empty)
     * @return an array without duplicates, in natural sort order
     */
    public static String[] removeDuplicateStrings(String[] array) {
        return StringUtils.removeDuplicateStrings(array);
    }

    /**
     * Replace all occurrences of a substring within a string with another string.
     * 
     * @param inString   - String to examine
     * @param oldPattern - String to replace
     * @param newPattern - String to insert
     * @return a String with the replacements
     */
    public static String replace(String inString, String oldPattern, @Nullable String newPattern) {
        return StringUtils.replace(inString, oldPattern, newPattern);
    }

    /**
     * Sort the given String array if necessary.
     * 
     * @param array - the original array (potentially empty)
     * @return the array in sorted form (never null)
     */
    public static String[] sortStringArray(String[] array) {
        return StringUtils.sortStringArray(array);
    }

    /**
     * Split a String at the first occurrence of the delimiter. Does not include the
     * delimiter in the result.
     * 
     * @param toSplit   - the string to split (potentially null or empty)
     * @param delimiter - to split the string up with (potentially null or empty)
     * @return a two element array with index 0 being before the delimiter, and
     *         index 1 being after the delimiter (neither element includes the
     *         delimiter); or null if the delimiter wasn't found in the given input
     *         String
     */
    public static String[] split(@Nullable String toSplit, @Nullable String delimiter) {
        return StringUtils.split(toSplit, delimiter);
    }

    /**
     * Take an array of strings and split each element based on the given delimiter.
     * A Properties instance is then generated, with the left of the delimiter
     * providing the key, and the right of the delimiter providing the value.
     * 
     * Will trim both the key and value before adding them to the Properties.
     * 
     * @param array     - the array to process
     * @param delimiter - to split each element using (typically the equals symbol)
     * @return a Properties instance representing the array contents, or null if the
     *         array to process was null or empty
     */
    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
        return StringUtils.splitArrayElementsIntoProperties(array, delimiter);
    }

    /**
     * Take an array of strings and split each element based on the given delimiter.
     * A Properties instance is then generated, with the left of the delimiter
     * providing the key, and the right of the delimiter providing the value.
     * 
     * Will trim both the key and value before adding them to the Properties
     * instance.
     * 
     * @param array         - the array to process
     * @param delimiter     - to split each element using (typically the equals
     *                      symbol)
     * @param charsToDelete - one or more characters to remove from each element
     *                      prior to attempting the split operation (typically the
     *                      quotation mark symbol), or null if no removal should
     *                      occur
     * @return a Properties instance representing the array contents, or null if the
     *         array to process was null or empty
     */
    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter,
            @Nullable String charsToDelete) {
        return StringUtils.splitArrayElementsIntoProperties(array, delimiter, charsToDelete);
    }

    /**
     * Test if the given String starts with the specified prefix, ignoring
     * upper/lower case.
     * 
     * @param str    - the String to check
     * @param prefix - the prefix to look for
     * @return returns if the given String starts with the specified prefix
     */
    public static boolean startsWithIgnoreCase(@Nullable String str, @Nullable String prefix) {
        return StringUtils.startsWithIgnoreCase(str, prefix);
    }

    /**
     * Strip the filename extension from the given Java resource path, e.g.
     * "mypath/myfile.txt" -> "mypath/myfile".
     * 
     * @param path - the file path
     * @return the path with stripped filename extension
     */
    public static String stripFilenameExtension(String path) {
        return StringUtils.stripFilenameExtension(path);
    }

    /**
     * Test whether the given string matches the given substring at the given index
     * 
     * @param str       - the original string (or StringBuilder)
     * @param index     - the index in the original string to start matching against
     * @param substring - the substring to match at the given index
     * @return returns whether the given string matches the given substring at the
     *         given index
     */
    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
        return StringUtils.substringMatch(str, index, substring);
    }

    /**
     * Copy the given Collection into a String array.
     * 
     * The Collection must contain String elements only.
     * 
     * @param collection - the Collection to copy (potentially null or empty)
     * @return the resulting String array
     */
    public static String[] toStringArray(@Nullable Collection<String> collection) {
        return StringUtils.toStringArray(collection);
    }

    /**
     * Copy the given Enumeration into a String array.
     * 
     * The Enumeration must contain String elements only.
     * 
     * @param enumeration - the Enumeration to copy (potentially null or empty)
     * @return the resulting String array
     */
    public static String[] toStringArray(@Nullable Enumeration<String> enumeration) {
        return StringUtils.toStringArray(enumeration);
    }

    /**
     * Trim all whitespace from the given String: leading, trailing, and in between
     * characters.
     * 
     * @param str - the String to check
     * @return the trimmed String
     */
    public static String trimAllWhitespace(String str) {
        return StringUtils.trimAllWhitespace(str);
    }

    /**
     * Trim the elements of the given String array, calling String.trim() on each
     * non-null element.
     * 
     * @param array - the original String array (potentially empty)
     * @return the resulting array (of the same size) with trimmed elements
     */
    public static String[] trimArrayElements(String[] array) {
        return StringUtils.trimArrayElements(array);
    }

    /**
     * Trim all occurrences of the supplied leading character from the given String.
     * 
     * @param str              - the String to check
     * @param leadingCharacter - the leading character to be trimmed
     * @return the leading character to be trimmed
     */
    public static String trimLeadingCharacter(String str, char leadingCharacter) {
        return StringUtils.trimLeadingCharacter(str, leadingCharacter);
    }

    /**
     * Trim leading whitespace from the given String.
     * 
     * @param str - the String to check
     * @return the trimmed String
     */
    public static String trimLeadingWhitespace(String str) {
        return StringUtils.trimLeadingWhitespace(str);
    }

    /**
     * Trim all occurrences of the supplied trailing character from the given
     * String.
     * 
     * @param str               - the String to check
     * @param trailingCharacter - the trailing character to be trimmed
     * @return the trimmed String
     */
    public static String trimTrailingCharacter(String str, char trailingCharacter) {
        return StringUtils.trimTrailingCharacter(str, trailingCharacter);
    }

    /**
     * Trim trailing whitespace from the given String.
     * 
     * @param str - the String to check
     * @return the trimmed String
     */
    public static String trimTrailingWhitespace(String str) {
        return StringUtils.trimTrailingWhitespace(str);
    }

    /**
     * Trim leading and trailing whitespace from the given String.
     * 
     * @param str - the String to check
     * @return the trimmed String
     */
    public static String trimWhitespace(String str) {
        return StringUtils.trimWhitespace(str);
    }

    /**
     * Uncapitalize a String, changing the first letter to lower case as per
     * Character.toLowerCase(char). No other letters are changed.
     * 
     * @param str - the String to uncapitalize
     * @return the uncapitalized String
     */
    public static String uncapitalize(String str) {
        return StringUtils.uncapitalize(str);
    }

    /**
     * Unqualify a string qualified by a '.' dot character. For example,
     * "this.name.is.qualified", returns "qualified".
     * 
     * @param qualifiedName - the qualified name
     * @return Unqualified string
     */
    public static String unqualify(String qualifiedName) {
        return StringUtils.unqualify(qualifiedName);
    }

    /**
     * Unqualify a string qualified by a separator character. For example,
     * "this:name:is:qualified" returns "qualified" if using a ':' separator.
     * 
     * @param qualifiedName - the qualified name
     * @param separator     - the separator
     * @return Unqualified string
     */
    public static String unqualify(String qualifiedName, char separator) {
        return StringUtils.unqualify(qualifiedName, separator);
    }

}