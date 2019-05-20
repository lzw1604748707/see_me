package com.sandu.common.kit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class JsoupKit {

	private static final Whitelist contentWhitelist = Whitelist();
	private static final Document.OutputSettings notPrettyPrint = new Document.OutputSettings().prettyPrint(false);
	
	private static Whitelist Whitelist() {
		return  Whitelist.relaxed()
		.removeProtocols("a", "href", "ftp", "http", "https", "mailto")
		.removeProtocols("img", "src", "http", "https")
		// 官方默认会将 target 给过滤掉
		.addAttributes("a", "href", "title", "target")  

		/**
		 * 在 Whitelist.relaxed() 之外添加额外的白名单规则
         */
		.addTags("div", "span", "embed", "object", "param")
		.addAttributes(":all", "style", "class", "id", "name")
		.addAttributes("object", "width", "height", "classid", "codebase")
		.addAttributes("param", "name", "value")
		.addAttributes("embed", "src", "quality", "width", "height", "allowFullScreen", "allowScriptAccess", "flashvars", "name", "type", "pluginspage");
	}
	
	/**
	 * 过滤xss
	 * 将temp目录图片移动到对应目录
	 * 更新图片src
	 */
	public static String fiterContent(String html,String folder){
		html = filterArticleContent(html);
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("img");
		for (Element element : elements) {
			String src = element.attr("src");
			//如果有temp，才需要替换
			if (src.contains("temp")) {
				String url = UploadFileMoveKit.move(src, folder);
				html = html.replaceAll(src, url);
			}
		}
		return html;
	}
	
	/**
	 * 对文章 content 字段过滤
	 */
	public static String filterArticleContent(String content) {
		return content != null ? Jsoup.clean(content, "", contentWhitelist, notPrettyPrint) : null;
	}
	
	/**
	 * title过滤 
	 */
	public static String filterArticleTitle(String title) {
		return getText(title);
	}
	
	/**
	 * 获取 html 中的纯文本信息，过滤所有 tag
 	 */
	public static String getText(String html) {
		return html != null ? Jsoup.clean(html, Whitelist.none()) : null;
	}

	
	public static void main(String[] args) {
		String sb ="<h1>不改</h1><img src=\"http://127.0.0.1:80/upload/temp/true1.jpg\">";
		System.out.println(fiterContent(sb,"/info/2"));
	}
}
