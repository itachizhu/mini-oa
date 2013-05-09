/**
 * 
 */
package org.mini.web.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.mini.framework.action.BaseAction;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Result;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @author Administrator
 *
 */
@Controller
@Namespace("/")
public class CaptchaImageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private Producer captchaProducer;
	
	private ByteArrayInputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	@Action(value = "code", results = { @Result(name = SUCCESS, type="stream", params = {"contentType", "image/jpeg", "inputName", "inputStream"}) })
	@Override
    public String execute() throws Exception {
		String capText = captchaProducer.createText(); 
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO
				.createImageOutputStream(output);
		ImageIO.write(bi, "JPEG", imageOut);
		imageOut.close();
		inputStream = new ByteArrayInputStream(output.toByteArray());
		output.flush();
		output.close();
		return SUCCESS;
	}

}
