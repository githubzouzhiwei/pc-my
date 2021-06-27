package cn.pconline.pcloud.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseProperties {
	
	// 应用名称，缓存前缀
	@Value("${spring.application.name}")
    private String applicationName;

	// pc域名
	@Value("${app.domain:}")
	private String domain;

	// wap域名
	@Value("${app.wap.domain:}")
	private String wapDomain;
	
	// 小程序AppID
	@Value("${wx.mini.id:}")
	private String wxMiniId;
	
	// 小程序App秘钥
	@Value("${wx.mini.secret:}")
	private String wxMiniSecret;
	
	// 公众号AppID
	@Value("${wx.app.id:}")
	private String wxAppId;
	
	// 公众号App秘钥
	@Value("${wx.app.secret:}")
	private String wxAppSecret;

	// 应用代理ip
	@Value("${sys.proxy.ip:}")
	private String proxyIp;

	// 应用代理端口
	@Value("${sys.proxy.port:8080}")
	private int proxyPort;

	// 应用代理账号
	@Value("${sys.proxy.user:}")
	private String proxyUser;

	// 应用代理账号密码
	@Value("${sys.proxy.password:}")
	private String proxyPassword;
	
	// PDF文件保存路径
	@Value("${sys.file.path:}")
	private String filePath;
	
	// WEBP图片转换器路径
	@Value("${sys.file.cwebp:}")
	private String fileCwebp;
	
	// WEBP图片转换精度
	@Value("${sys.file.quality:75}")
	private int fileQuality;

	// UPC根路径
	@Value("${upc.root:}")
	private String upcRoot;
	// UPC应用名称
	@Value("${upc.appName:}")
	private String upcAppName;
	
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getWapDomain() {
		return wapDomain;
	}

	public void setWapDomain(String wapDomain) {
		this.wapDomain = wapDomain;
	}

	public String getWxMiniId() {
		return wxMiniId;
	}

	public void setWxMiniId(String wxMiniId) {
		this.wxMiniId = wxMiniId;
	}

	public String getWxMiniSecret() {
		return wxMiniSecret;
	}

	public void setWxMiniSecret(String wxMiniSecret) {
		this.wxMiniSecret = wxMiniSecret;
	}

	public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public String getWxAppSecret() {
		return wxAppSecret;
	}

	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getProxyUser() {
		return proxyUser;
	}

	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileCwebp() {
		return fileCwebp;
	}

	public void setFileCwebp(String fileCwebp) {
		this.fileCwebp = fileCwebp;
	}

	public int getFileQuality() {
		return fileQuality;
	}

	public void setFileQuality(int fileQuality) {
		this.fileQuality = fileQuality;
	}

	public String getUpcRoot() {
		return upcRoot;
	}

	public void setUpcRoot(String upcRoot) {
		this.upcRoot = upcRoot;
	}

	public String getUpcAppName() {
		return upcAppName;
	}

	public void setUpcAppName(String upcAppName) {
		this.upcAppName = upcAppName;
	}

}
