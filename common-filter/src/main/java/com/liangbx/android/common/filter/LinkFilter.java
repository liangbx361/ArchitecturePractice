package com.liangbx.android.common.filter;

import android.text.util.Linkify;
import android.util.Patterns;
import android.webkit.WebView;

import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/10/13
 */

public class LinkFilter {

    public List<LinkParam> filterLink(String sentence, Locale locale) {

        List<LinkParam> linkParams = new ArrayList<>();
        linkParams.addAll(gatherLinks(sentence, Linkify.WEB_URLS, Patterns.WEB_URL,
                Linkify.sUrlMatchFilter, null));

        linkParams.addAll(gatherLinks(sentence, Linkify.EMAIL_ADDRESSES, Patterns.EMAIL_ADDRESS,
                null, null));

        linkParams.addAll(gatherTelLinks(sentence, locale));
        linkParams.addAll(gatherMapLinks(sentence));

        return linkParams;
    }

    public void sort(List<LinkParam> linkParams) {

        Collections.sort(linkParams, new Comparator<LinkParam>() {
            @Override
            public int compare(LinkParam lhs, LinkParam rhs) {
                if(lhs.getStart() < rhs.getStart()) {
                    return -1;
                } else if(lhs.getStart() > rhs.getStart()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    private List<LinkParam> gatherLinks(String s, int type, Pattern pattern,
                                               Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
        List<LinkParam> linkParams = new ArrayList<>();
        Matcher m = pattern.matcher(s);

        while (m.find()) {
            int start = m.start();
            int end = m.end();
            if (matchFilter == null || matchFilter.acceptMatch(s, start, end)) {
                String url = m.group(0);
                LinkParam linkParam = new LinkParam(url, type, start, end);
                linkParams.add(linkParam);
            }
        }

        return linkParams;
    }

    private List<LinkParam> gatherTelLinks(String s, Locale locale) {
        List<LinkParam> linkParams = new ArrayList<>();

        getTelLink(s, locale, linkParams);
        if(locale != Locale.getDefault()) {
            getTelLink(s, Locale.getDefault(), linkParams);
        }

        getTelLink(s, Locale.CHINA, linkParams);

        return linkParams;
    }

    private void getTelLink(String s, Locale locale, List<LinkParam> linkParams) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Iterable<PhoneNumberMatch> matches = phoneUtil.findNumbers(s, locale.getCountry(),
                PhoneNumberUtil.Leniency.POSSIBLE, Long.MAX_VALUE);
        for (PhoneNumberMatch match : matches) {
            String phoneNumber = match.rawString();
            int start = match.start();
            int end = match.end();
            LinkParam linkParam = new LinkParam(phoneNumber, Linkify.PHONE_NUMBERS, start, end);
            if(!isContainer(linkParams, linkParam)) {
                linkParams.add(linkParam);
            }
        }
    }

    private boolean isContainer(List<LinkParam> linkParams, LinkParam linkParam) {

        for(LinkParam item : linkParams) {
            if(item.getLink().equals(linkParam.getLink())
                    && item.getStart() == linkParam.getStart()
                    && item.getEnd() == linkParam.getEnd()) {
                return true;
            }
        }

        return false;
    }

    private List<LinkParam> gatherMapLinks(String string) {
        List<LinkParam> linkParams = new ArrayList<>();

        String address;
        int base = 0;

        try {
            while ((address = WebView.findAddress(string)) != null) {
                int start = string.indexOf(address);

                if (start < 0) {
                    break;
                }

                int length = address.length();
                int end = start + length;

                int linkStart = base + start;
                int linkEnd = base + end;
                string = string.substring(end);
                base += end;

                linkParams.add(new LinkParam(address, Linkify.MAP_ADDRESSES, linkStart, linkEnd));
            }
        } catch (UnsupportedOperationException e) {
            // findAddress may fail with an unsupported exception on platforms without a WebView.
            // In this case, we will not append anything to the links variable: it would have died
            // in WebView.findAddress.

        }

        return linkParams;
    }

    public static class LinkParam {

        private String link;
        private int type;
        private int start;
        private int end;

        public LinkParam(String link, int type, int start, int end) {
            this.link = link;
            this.type = type;
            this.start = start;
            this.end = end;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}
