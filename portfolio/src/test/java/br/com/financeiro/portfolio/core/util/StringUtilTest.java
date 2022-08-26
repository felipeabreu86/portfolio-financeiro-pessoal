package br.com.financeiro.portfolio.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    @Test
    public void testarStringNulaOuVazia() {
        assertTrue(StringUtil.isNullOrEmpty(""));
        assertTrue(StringUtil.isNullOrEmpty(null));
        assertFalse(StringUtil.isNullOrEmpty(" "));
        assertFalse(StringUtil.isNullOrEmpty("teste"));
        assertFalse(StringUtil.isNullOrEmpty("teste teste"));
        assertFalse(StringUtil.isNullOrEmpty("#"));
    }

    @Test
    public void testarStringNulaOuComEspacos() {
        assertTrue(StringUtil.isNullOrWhiteSpace(""));
        assertTrue(StringUtil.isNullOrWhiteSpace(null));
        assertTrue(StringUtil.isNullOrWhiteSpace(" "));
        assertFalse(StringUtil.isNullOrWhiteSpace("teste"));
        assertFalse(StringUtil.isNullOrWhiteSpace("teste teste"));
        assertFalse(StringUtil.isNullOrWhiteSpace("#"));
    }

    @Test
    public void testarCombinarStringsComBarra() {
        assertEquals("abc/def", StringUtil.combineStringsToPath("abc", "def"));
        assertEquals("abc/def", StringUtil.combineStringsToPath("abc/", "def"));
        assertEquals("abc/def", StringUtil.combineStringsToPath("abc/", "/def"));
        assertEquals("abc/def", StringUtil.combineStringsToPath("abc", "/def"));
        assertEquals("/def", StringUtil.combineStringsToPath(null, "/def"));
        assertEquals("abc", StringUtil.combineStringsToPath("abc", null));
        assertEquals("", StringUtil.combineStringsToPath(null, null));
    }

    @Test
    public void testarAddStringToArray() {
        String[] array = { "1" };
        String[] result = StringUtil.addStringToArray(array, "2");
        String[] result2 = StringUtil.addStringToArray(null, "2");
        assertEquals(2, result.length);
        assertEquals(1, result2.length);
    }

    @Test
    public void testarApplyRelativePath() {
        String result = StringUtil.applyRelativePath("/a/b", "/p");
        assertEquals("/a/p", result);
    }

    @Test
    public void testarArrayToCommaDelimitedString() {
        String[] array = { "1", "2", "3" };
        String result = StringUtil.arrayToCommaDelimitedString(array);
        assertEquals("1,2,3", result);
    }

    @Test
    public void testarArrayToDelimitedString() {
        String[] array = { "1", "2", "3" };
        String result = StringUtil.arrayToDelimitedString(array, "|");
        assertEquals("1|2|3", result);
    }

    @Test
    public void testarCapitalize() {
        String result = StringUtil.capitalize("teste teste");
        assertEquals("Teste teste", result);
    }

    @Test
    public void testarCleanPath() {
        String result = StringUtil.cleanPath("a/b/c/../d");
        assertEquals("a/b/d", result);
    }

    @Test
    public void testarCollectionToCommaDelimitedString() {
        Collection<String> colecao = new ArrayList<>();
        colecao.add("1");
        colecao.add("2");
        colecao.add("3");
        String result = StringUtil.collectionToCommaDelimitedString(colecao);
        assertEquals("1,2,3", result);
    }

    @Test
    public void testarCollectionToDelimitedString() {
        Collection<String> colecao = new ArrayList<>();
        colecao.add("1");
        colecao.add("2");
        colecao.add("3");
        String result = StringUtil.collectionToDelimitedString(colecao, "|");
        assertEquals("1|2|3", result);
    }

    @Test
    public void testarCollectionToDelimitedStringWithPrefixAndSufix() {
        Collection<String> colecao = new ArrayList<>();
        colecao.add("1");
        colecao.add("2");
        colecao.add("3");
        String result = StringUtil.collectionToDelimitedString(colecao, "|", "a", "b");
        assertEquals("a1b|a2b|a3b", result);
    }

    @Test
    public void testarCommaDelimitedListToSet() {
        Set<String> result = StringUtil.commaDelimitedListToSet("1,2,3");
        assertEquals(3, result.size());
    }

    @Test
    public void testarCommaDelimitedListToStringArray() {
        String[] result = StringUtil.commaDelimitedListToStringArray("1,2,3");
        assertEquals(3, result.length);
    }

    @Test
    public void testarConcatenateStringArrays() {
        String[] array1 = { "1", "2", "3" };
        String[] array2 = { "4", "5", "6" };
        String[] result = StringUtil.concatenateStringArrays(array1, array2);
        assertEquals(6, result.length);
    }

    @Test
    public void testarContainsWhitespace() {
        CharSequence seq = "a a";
        CharSequence seq2 = "aa";
        boolean result = StringUtil.containsWhitespace(seq);
        boolean result2 = StringUtil.containsWhitespace(seq2);
        assertTrue(result);
        assertFalse(result2);
    }

    @Test
    public void testarContainsWhitespaceString() {
        String seq = "a a";
        String seq2 = "aa";
        boolean result = StringUtil.containsWhitespace(seq);
        boolean result2 = StringUtil.containsWhitespace(seq2);
        assertTrue(result);
        assertFalse(result2);
    }

    @Test
    public void testarCountOccurrencesOf() {
        String seq = "abaca";
        int result = StringUtil.countOccurrencesOf(seq, "a");
        assertEquals(3, result);
    }

    @Test
    public void testarDelete() {
        String seq = "abaca";
        String result = StringUtil.delete(seq, "a");
        assertEquals("bc", result);
    }

    @Test
    public void testarDeleteAny() {
        String seq = "abacazd";
        String result = StringUtil.deleteAny(seq, "az");
        assertEquals("bcd", result);
    }

    @Test
    public void testarDelimitedListToStringArray() {
        String seq = "a,b,c,d";
        String[] result = StringUtil.delimitedListToStringArray(seq, ",");
        assertEquals(4, result.length);
    }

    @Test
    public void testarDelimitedListToStringArrayAndDelete() {
        String seq = "a,b,c,d" + "\\r\\n\\f";
        String[] result = StringUtil.delimitedListToStringArray(seq, ",", "\\r\\n\\f");
        assertEquals(4, result.length);
    }

    @Test
    public void testarEndsWithIgnoreCase() {
        String str = "teste";
        boolean result = StringUtil.endsWithIgnoreCase(str, "te");
        boolean result2 = StringUtil.endsWithIgnoreCase(str, "TE");
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    public void testarGetFilename() {
        String result = StringUtil.getFilename("C:/teste/teste.txt");
        assertEquals("teste.txt", result);
    }

    @Test
    public void testarGetFilenameExtension() {
        String result = StringUtil.getFilenameExtension("C:/teste/teste.txt");
        assertEquals("txt", result);
    }

    @Test
    public void testarHasText() {
        boolean result1 = StringUtil.hasText("C:/teste/teste.txt");
        boolean result2 = StringUtil.hasText("");
        boolean result3 = StringUtil.hasText(null);
        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    public void testarMatchesCharacter() {
        boolean result1 = StringUtil.matchesCharacter("a", 'a');
        boolean result2 = StringUtil.matchesCharacter("AA", 'a');
        boolean result3 = StringUtil.matchesCharacter("a", 'A');
        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    public void testarParseLocale() {
        Locale result = StringUtil.parseLocale("en");
        assertEquals(Locale.ENGLISH, result);
    }

    @Test
    public void testarParseLocaleString() {
        Locale result = StringUtil.parseLocaleString("en");
        assertEquals(Locale.ENGLISH, result);
    }

    @Test
    public void testarParseTimeZoneString() {
        TimeZone result = StringUtil.parseTimeZoneString("America/Sao_Paulo");
        assertEquals(TimeZone.getTimeZone("America/Sao_Paulo"), result);
    }

    @Test
    public void testarPathEquals() {
        boolean result = StringUtil.pathEquals("/a/b/c", "/a/b/c");
        assertTrue(result);
    }

    @Test
    public void testarQuote() {
        String result = StringUtil.quote("teste");
        assertEquals("'teste'", result);
    }

    @Test
    public void testarQuoteIfString() {
        Object result = StringUtil.quoteIfString("teste");
        assertEquals("'teste'", result);
    }

    @Test
    public void testarRemoveDuplicateStrings() {
        String[] array = { "1", "2", "1" };
        String[] result = StringUtil.removeDuplicateStrings(array);
        assertEquals(2, result.length);
    }

    @Test
    public void testarReplace() {
        String result = StringUtil.replace("abc", "b", "d");
        assertEquals("adc", result);
    }

    @Test
    public void testarSortStringArray() {
        String[] array = { "3", "2", "1" };
        String[] result = StringUtil.sortStringArray(array);
        assertEquals("1", result[0]);
        assertEquals("2", result[1]);
        assertEquals("3", result[2]);
    }

    @Test
    public void testarSplit() {
        String[] result = StringUtil.split("1.2.3", ".");
        assertEquals("1", result[0]);
        assertEquals("2.3", result[1]);
    }

    @Test
    public void testarStartsWithIgnoreCase() {
        assertTrue(StringUtil.startsWithIgnoreCase("teste", "te"));
        assertTrue(StringUtil.startsWithIgnoreCase("teste", "TE"));
    }

    @Test
    public void testarStripFilenameExtension() {
        String result = StringUtil.stripFilenameExtension("mypath/myfile.txt");
        assertEquals("mypath/myfile", result);
    }

    @Test
    public void testarSubstringMatch() {
        CharSequence seq = "abcd";
        boolean result = StringUtil.substringMatch(seq, 1, "bcd");
        assertTrue(result);
    }

    @Test
    public void testarToStringArray() {
        Collection<String> colecao = new ArrayList<>();
        colecao.add("1");
        colecao.add("2");
        colecao.add("3");
        String[] result = StringUtil.toStringArray(colecao);
        assertEquals(3, result.length);
    }

    @Test
    public void testarToStringArrayEnumeration() {
        Collection<String> colecao = new ArrayList<>();
        colecao.add("1");
        colecao.add("2");
        colecao.add("3");

        Enumeration<String> enumeration = Collections.enumeration(colecao);

        String[] result = StringUtil.toStringArray(enumeration);
        assertEquals(3, result.length);
    }

    @Test
    public void testarTrimAllWhitespace() {
        String seq = "a b c d";
        String result = StringUtil.trimAllWhitespace(seq);
        assertEquals("abcd", result);
    }

    @Test
    public void testarTrimArrayElements() {
        String[] array = { " 3 ", " 2 ", " 1 " };
        String[] result = StringUtil.trimArrayElements(array);
        assertEquals("3", result[0]);
        assertEquals("2", result[1]);
        assertEquals("1", result[2]);
    }

    @Test
    public void testarTrimLeadingCharacter() {
        String seq = "a b c d";
        String result = StringUtil.trimLeadingCharacter(seq, 'a');
        assertEquals(" b c d", result);
    }

    @Test
    public void testarTrimLeadingWhitespace() {
        String seq = " a b c d";
        String result = StringUtil.trimLeadingWhitespace(seq);
        assertEquals("a b c d", result);
    }

    @Test
    public void testarTrimTrailingWhitespace() {
        String seq = " a b c d ";
        String result = StringUtil.trimTrailingWhitespace(seq);
        assertEquals(" a b c d", result);
    }

    @Test
    public void testarTrimWhitespace() {
        String seq = " a b c d ";
        String result = StringUtil.trimWhitespace(seq);
        assertEquals("a b c d", result);
    }

    @Test
    public void testarUncapitalize() {
        String seq = "Teste";
        String result = StringUtil.uncapitalize(seq);
        assertEquals("teste", result);
    }

    @Test
    public void testarUnqualify() {
        String result = StringUtil.unqualify("this.name.is.qualified");
        assertEquals("qualified", result);
    }

    @Test
    public void testarUnqualifyWithSeparator() {
        String result = StringUtil.unqualify("this:name:is:qualified", ':');
        assertEquals("qualified", result);
    }

}
