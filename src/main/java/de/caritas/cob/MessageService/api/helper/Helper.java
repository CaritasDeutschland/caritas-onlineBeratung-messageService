package de.caritas.cob.MessageService.api.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.InternalServerErrorException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Whitelist;

/**
 * Helper class
 *
 */
public class Helper {

  /**
   * 
   * Remove HTML code from a text (XSS-Protection)
   * 
   * @param text
   * @return the given text without html
   */
  public static String removeHTMLFromText(String text) {

    OutputSettings outputSettings = new OutputSettings();
    outputSettings.prettyPrint(false);

    try {
      text = Jsoup.clean(text, StringUtils.EMPTY, Whitelist.none(), outputSettings);
    } catch (Exception exception) {
      throw new InternalServerErrorException("Error while removing HTML from text", exception);
    }
    return text;
  }

  /**
   * URL encoding for a given string
   * 
   * @param stringToEncode
   * @return the encoded string
   * @throws UnsupportedEncodingException
   */
  public static String urlEncodeString(String stringToEncode) {
    try {
      return URLEncoder.encode(stringToEncode, StandardCharsets.UTF_8.name());

    } catch (UnsupportedEncodingException ex) {
      return null;
    }
  }

  /**
   * Url decoding for a given string
   * 
   * @param stringToDecode
   * @return the decoded string
   * @throws UnsupportedEncodingException
   */
  public static String urlDecodeString(String stringToDecode) {
    try {
      return java.net.URLDecoder.decode(stringToDecode, StandardCharsets.UTF_8.name());

    } catch (UnsupportedEncodingException ex) {
      return null;
    }
  }
}
