package cn.pconline.pcloud.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import cn.pconline.pcloud.base.config.BaseProperties;

@Configuration
public class ApiProperties extends BaseProperties {
	/**
	 * 白名单
	 */
	@Value("${api.white.ips:}")
	private String whiteIps;
	
	/**
	 * 秘钥
	 */
	@Value("${api.secret:}")
	private String secret;
	
	/**
	 * 是否启用接口认证
	 */
	@Value("${api.authEnable:}")
	private boolean authEnable;

	public String getWhiteIps() {
		return whiteIps;
	}

	public void setWhiteIps(String whiteIps) {
		this.whiteIps = whiteIps;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public boolean isAuthEnable() {
		return authEnable;
	}

	public void setAuthEnable(boolean authEnable) {
		this.authEnable = authEnable;
	}
}
