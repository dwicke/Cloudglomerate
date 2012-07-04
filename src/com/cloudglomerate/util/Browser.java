package com.cloudglomerate.util;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URI;

public class Browser {
	  /**
	   * Browser to open in case {@link Desktop#isDesktopSupported()} is {@code false} or {@code null}
	   * to prompt user to open the URL in their favorite browser.
	   */
	  private static final String BROWSER = "google-chrome";
	/** Open a browser at the given URL. */
	  public static void browse(String url) {
	    // first try the Java Desktop
	    if (Desktop.isDesktopSupported()) {
	      Desktop desktop = Desktop.getDesktop();
	      if (desktop.isSupported(Action.BROWSE)) {
	        try {
	          desktop.browse(URI.create(url));
	          return;
	        } catch (IOException e) {
	          // handled below
	        }
	      }
	    }
	    // Next try rundll32 (only works on Windows)
	    try {
	      Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
	      return;
	    } catch (IOException e) {
	      // handled below
	    }
	    // Next try the requested browser (e.g. "google-chrome")
	    if (BROWSER != null) {
	      try {
	        Runtime.getRuntime().exec(new String[] {BROWSER, url});
	        return;
	      } catch (IOException e) {
	        // handled below
	      }
	    }
	    // Finally just ask user to open in their browser using copy-paste
	    System.out.println("Please open the following URL in your browser:");
	    System.out.println("  " + url);
	  }
}
