package ua.blacky.douapp;

import android.text.TextUtils;

import org.htmlcleaner.BaseToken;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ua.blacky.douapp.models.Event;

/**
 * Created by amatsegor on 02.07.15.
 */
public class DouParser {

    private final Object lockObject = new Object();

    private List<Event> mEventList = new ArrayList<>();

    private final HtmlCleaner mCleaner = new HtmlCleaner();

    public DouParser(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockObject) {
                    int pageNum = 1;
                    while (true) {
                        StringBuilder sb = new StringBuilder();
                        try {
                            URL rootUrl = new URL(url + pageNum);
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(rootUrl.openStream()));

                            String inputLine;
                            while ((inputLine = in.readLine()) != null)
                                sb.append(inputLine);
                            in.close();
                            final String htmlContent = sb.toString();
                            addEvents(mCleaner.clean(htmlContent));
                        } catch (IOException e) {
                            break;
                        }
                        pageNum++;
                    }
                }
            }
        }).start();
    }

    private void addEvents(TagNode rootNode) {
        TagNode divRootNode = new TagNode("");
        try {
            divRootNode = (TagNode) rootNode.evaluateXPath("/body/div[1]/div[3]/div/div[2]/div/div/div[1]")[0];
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        for (BaseToken tagNode : divRootNode.getAllChildren()) {
            if (tagNode instanceof TagNode) {
                TagNode node = (TagNode) tagNode;
                final String nodeClass = node.getAttributeByName("class");
                if (nodeClass != null) {
                    if (TextUtils.equals(nodeClass, "event")) {
                        mEventList.add(getEventFromDiv(node));
                    }
                }
            }
        }
    }

    private Event getEventFromDiv(TagNode eventDiv) {
        final List<? extends BaseToken> allChildren = eventDiv.getAllChildren();

        TagNode node = new TagNode("");
        try {
            String when = ((ContentNode) ((TagNode) eventDiv.evaluateXPath("/div[1]/span")[0]).getAllChildren().get(0)).getContent();
            String where = eventDiv.evaluateXPath("/div[1]/text()")[0].toString().trim();
            String title = eventDiv.evaluateXPath("/div[2]/a/text()")[0].toString().trim();

            Object[] nodes = eventDiv.evaluateXPath("/div[2]/a/img");
            String url = null;
            if (nodes.length > 0) {
                node = (TagNode) nodes[0];
                url = node.getAttributeByName("src");
            }

            String desc = eventDiv.evaluateXPath("/div[3]/text()")[0].toString().trim();

            String commentsNum = "0";
            nodes = eventDiv.evaluateXPath("/div[3]/a");
            if (nodes.length > 0) {
                commentsNum = ((ContentNode) ((TagNode) nodes[0]).getAllChildren().get(0)).getContent().trim();
            }

            node = ((TagNode) allChildren.get(7));
            String coming = ((ContentNode) ((TagNode) node.getAllChildren().get(0)).getAllChildren().get(0)).getContent();

            List<String> tagsList = new ArrayList<>();
            for (TagNode tagNode : node.getElementListByName("a", true)) {
                String tag = ((ContentNode) tagNode.getAllChildren().get(0)).getContent();
                tagsList.add(tag);
            }
            return Event.newBuilder().
                    setName(title)
                    .setDate(when)
                    .setAddress(where)
                    .setPictureUrl(url)
                    .setDescription(desc)
                    .setCommentsNum(Integer.parseInt(commentsNum))
                    .setAttends(coming)
                    .setTags(tagsList)
                    .build();
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Event> getEventList() {
        synchronized (lockObject) {
            return mEventList;
        }
    }

/*    private Event getEventFromDiv(TagNode divNode){

    }*/
}
