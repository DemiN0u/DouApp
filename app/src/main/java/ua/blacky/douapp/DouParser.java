package ua.blacky.douapp;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

/**
 * Created by amatsegor on 02.07.15.
 */
public class DouParser {

    private TagNode mRootNode;

    public DouParser(String url) {
        HtmlCleaner cleaner = new HtmlCleaner();
        mRootNode = cleaner.clean(url);
    }
}
