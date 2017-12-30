package model;

import java.util.List;

//====================================================
// this json object that work in it
/*
* RSSObject
*          -status =>String  => variable String
*          -feed => url,title,link,author,description,image => String => variable of Class Feed
*          -item => title,pubData,guid,author,thumbnail,description,content =>String ,category=> list(index,String  => variable of class Item
*   */


/*
*   "status": "ok"
    "feed": {

    "url": "http://rss.nytimes.com/services/xml/rss/nyt/Science.xml"
    "title": "NYT &gt; Science"
    "link": "https://www.nytimes.com/section/science?partner=rss&amp;emc=rss"
    "author": ""
    "description": ""
    "image": "https://static01.nyt.com/images/misc/NYT_logo_rss_250x40.png"

    }
    "items": [

        {
        "title": "Trilobites: Why We ‘Hear’ Some Silent GIFs"
        "pubDate": "2017-12-08 21:20:27"
        "link": "https://www.nytimes.com/2017/12/08/science/why-we-hear-some-silent-gifs.html?partner=rss&amp;emc=rss"
        "guid": "https://www.nytimes.com/2017/12/08/science/why-we-hear-some-silent-gifs.html"
        "author": "HEATHER MURPHY"
        "thumbnail": ""
        "description": "There are no audio tracks with these looping images, but some people really do “hear” them, neuroscientists say."
        "content": "There are no audio tracks with these looping images, but some people really do “hear” them, neuroscientists say."
        "categories": [
            "0": "Video Recordings, Downloads and Streaming"
            "1": "Senses and Sensation"
            "2": "Ears and Hearing"
            "3": "Brain"
            ]
     }
* */

public class RSSObject {

    private String status;
    private Feed feed ;
    private List<Item> items ;

    public RSSObject() {
    }

    public RSSObject(String status, Feed feed, List<Item> items) {
        this.status = status;
        this.feed = feed;
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

