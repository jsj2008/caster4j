package caster.demo.code.rss;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RomeRssOld {

    public enum RssType {
        RSS_0_90("rss_0.90"),
        RSS_0_91("rss_0.91"),
        RSS_0_92("rss_0.92"),
        RSS_0_93("rss_0.93"),
        RSS_0_94("rss_0.94"),
        RSS_1_0("rss_1.0"),
        RSS_2_0("rss_2.0"),
        ATOM_0_3("atom_0.3")
        ;

        private String type;

        RssType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }

    }

    public static RomeRssOld on() {
        return new RomeRssOld();
    }

    public static RomeRssOld on(String title, String link, String description) {
        return new RomeRssOld().setTitle(title).setLink(link).setDescription(description);
    }

    public static RomeRssOld on(InputStream in) throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        XmlReader xmlReader = new XmlReader(in);
        return new RomeRssOld().setFeed(input.build(xmlReader));
    }

    public static RomeRssOld on(String xml, String charsetName) throws IOException, FeedException {
        Charset charset = Charset.forName(charsetName);
        return RomeRssOld.on(new ByteArrayInputStream(xml.getBytes(charset)));
    }

    private SyndFeed feed;
    private List<SyndEntry> entries = new ArrayList<>();

    private RomeRssOld() {
        this.feed = new SyndFeedImpl();
        this.feed.setEntries(entries);
    }

    public SyndFeed getFeed() {
        return feed;
    }

    @SuppressWarnings("unchecked")
    public RomeRssOld setFeed(SyndFeed feed) {
        this.feed = feed;
        List list = feed.getEntries();
        if (list == null) feed.setEntries(entries);
        return this;
    }

    public RomeRssOld setFeedType(RssType rssType) {
        feed.setFeedType(rssType.toString());
        return this;
    }

    public RomeRssOld setTitle(String title) {
        feed.setTitle(title);
        return this;
    }

    public String getTitle() {
        return feed.getTitle();
    }

    public RomeRssOld setLink(String link) {
        feed.setLink(link);
        return this;
    }

    public String getLink() {
        return feed.getLink();
    }

    public RomeRssOld setDescription(String description) {
        feed.setDescription(description);
        return this;
    }

    public String getDescription() {
        return feed.getDescription();
    }

    public RomeRssOld setEntries(List<SyndEntry> entries) {
        this.entries = entries;
        this.feed.setEntries(entries);
        return this;
    }

    public List<SyndEntry> getEntries() {
        return entries;
    }

    public RomeRssOld addEntries(List<? extends SyndEntry> entries) {
        this.entries.addAll(entries);
        return this;
    }

    public RomeRssOld addEntry(SyndEntry entry) {
        this.entries.add(entry);
        return this;
    }

    public RomeRssOld addEntry(String title, String description) {
        SyndEntry entry= new SyndEntryImpl();
        entry.setTitle(title);
        SyndContent desc = new SyndContentImpl();
        desc.setValue(description);
        entry.setDescription(desc);
        entries.add(entry);
        return this;
    }

    public RomeRssOld addEntry(String title, String link, Date pubDate, Date updDate, String descriptionType, String description) {
        SyndEntry entry= new SyndEntryImpl();
        entry.setTitle(title);
        if (StringUtils.isNotBlank(link)) entry.setLink(link);
        if (pubDate != null) entry.setPublishedDate(pubDate);
        if (updDate != null) entry.setUpdatedDate(updDate);
        SyndContent desc = new SyndContentImpl();
        if (StringUtils.isNotBlank(descriptionType)) desc.setType(descriptionType);
        desc.setValue(description);
        entry.setDescription(desc);
        entries.add(entry);
        return this;
    }

    public RomeRssOld output(File file) throws IOException, FeedException {
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(this.feed, file);
        return this;
    }

    public RomeRssOld output(Writer writer) throws IOException, FeedException {
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(this.feed, writer);
        return this;
    }

    public String outputString() throws FeedException {
        SyndFeedOutput output = new SyndFeedOutput();
        return output.outputString(this.feed);
    }

    public org.jdom.Document outputJDom() throws FeedException {
        SyndFeedOutput output = new SyndFeedOutput();
        return output.outputJDom(this.feed);
    }

    public org.w3c.dom.Document outputW3CDom() throws FeedException {
        SyndFeedOutput output = new SyndFeedOutput();
        return output.outputW3CDom(this.feed);
    }

}
