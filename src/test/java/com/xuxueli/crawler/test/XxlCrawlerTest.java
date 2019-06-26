package com.xuxueli.crawler.test;

import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.conf.XxlCrawlerConf;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 爬虫示例01：爬取页面数据并封装VO对象
 *
 * @author xuxueli 2017-10-09 19:48:48
 */
public class XxlCrawlerTest {

    @PageSelect(cssQuery = "body")
    public static class PageVo {

        @PageFieldSelect(cssQuery = "#js-initialData", selectType = XxlCrawlerConf.SelectType.HTML)
        private String initialData;

        public String getInitialData() {
            return initialData;
        }

        public void setInitialData(String initialData) {
            this.initialData = initialData;
        }

        @Override
        public String toString() {
            return "PageVo{" +
                    "initialData='" + initialData + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls("https://www.zhihu.com/search?type=content&q=长腿")
                .setAllowSpread(false)
                .setPageParser(new PageParser<PageVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, PageVo pageVo) {
                        // 解析封装 PageVo 对象
                        System.out.println("数据是：" + pageVo.getInitialData());
                    }
                })
                .build();

        System.out.println("start");
        crawler.start(true);
        System.out.println("end");
    }

}
