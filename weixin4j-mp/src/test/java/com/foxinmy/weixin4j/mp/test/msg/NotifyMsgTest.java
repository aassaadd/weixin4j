package com.foxinmy.weixin4j.mp.test.msg;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.JsonResult;
import com.foxinmy.weixin4j.mp.api.MediaApi;
import com.foxinmy.weixin4j.mp.api.NotifyApi;
import com.foxinmy.weixin4j.mp.message.NotifyMessage;
import com.foxinmy.weixin4j.mp.test.TokenTest;
import com.foxinmy.weixin4j.tuple.Image;
import com.foxinmy.weixin4j.tuple.Music;
import com.foxinmy.weixin4j.tuple.News;
import com.foxinmy.weixin4j.tuple.Text;
import com.foxinmy.weixin4j.tuple.Video;
import com.foxinmy.weixin4j.tuple.Voice;
import com.foxinmy.weixin4j.type.MediaType;

/**
 * 客服消息测试
 * 
 * @className NotifyMsgTest
 * @author jy.hu
 * @date 2014年4月10日
 * @since JDK 1.7
 * @see
 */
public class NotifyMsgTest extends TokenTest {

	private NotifyApi notifyApi;
	private MediaApi mediaApi;

	@Before
	public void init() {
		notifyApi = new NotifyApi(tokenHolder);
		mediaApi = new MediaApi(tokenHolder);
	}

	@Test
	public void text() throws WeixinException {
		NotifyMessage notify = new NotifyMessage("to", new Text("ttt"));
		System.out.println(notifyApi.sendNotify(notify));
	}

	@Test
	public void image() throws WeixinException {
		NotifyMessage notify = new NotifyMessage("to", new Image("image"));
		System.out.println(notifyApi.sendNotify(notify));
	}

	@Test
	public void voice() throws WeixinException {
		NotifyMessage notify = new NotifyMessage("to", new Voice("voice"));
		System.out.println(notifyApi.sendNotify(notify));
	}

	@Test
	public void video() throws WeixinException {
		NotifyMessage notify = new NotifyMessage("to", new Video("video"));
		System.out.println(notifyApi.sendNotify(notify));
	}

	@Test
	public void music() throws WeixinException {
		NotifyMessage notify = new NotifyMessage("to", new Music("music"));
		System.out.println(notifyApi.sendNotify(notify));
	}

	@Test
	public void news() throws WeixinException {
		News news = new News();
		NotifyMessage notify = new NotifyMessage("to", news);
		news.pushArticle("title1", "desc1", "picUrl1", "url1");
		news.pushArticle("title2", "desc2", "picUrl2", "url2");
		System.out.println(notifyApi.sendNotify(notify));
	}

	@Test
	public void send1() throws IOException, WeixinException {
		NotifyMessage notify = new NotifyMessage(
				"owGBft_vbBbOaQOmpEUE4xDLeRSU", new Text(
						"this is a notify message!"));
		JsonResult result = notifyApi.sendNotify(notify);
		Assert.assertEquals(0, result.getCode());
	}

	@Test
	public void send2() throws WeixinException, IOException {
		String mediaId = mediaApi.uploadMedia(new File("/tmp/test.jpg"),
				MediaType.image, false);
		NotifyMessage imageNotify = new NotifyMessage(
				"owGBft_vbBbOaQOmpEUE4xDLeRSU", new Image(mediaId));
		JsonResult result = notifyApi.sendNotify(imageNotify);
		Assert.assertEquals(0, result.getCode());
	}
}
