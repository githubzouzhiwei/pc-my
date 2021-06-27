package cn.pconline.pcloud.api.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.pconline.framework.util.EncryptionUtils;
import cn.pconline.framework.util.IpUtils;
import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.api.config.ApiProperties;
import cn.pconline.pcloud.base.util.RedisTemplateUtil;

/**
 * 接口校验拦截器
 * @author jzc
 *
 */
@Component
public class ApiCheckInterceptor implements HandlerInterceptor {
	
	@Autowired
    RedisTemplateUtil redisTemplateUtil;
	
	@Autowired
	ApiProperties apiProperties;

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		// 是否启用接口认证
		if(!apiProperties.isAuthEnable()) {
			return true;
		}

        // 校验白名单
		String clientIp = IpUtils.getClientIp(request);
		if(!apiProperties.getWhiteIps().contains(clientIp) && !clientIp.equals("127.0.0.1")) {
			this.writerForError(request, response, 1001, "This ip(" + clientIp + ") has no access rights!");
			return false;
		}
		
		// 参数校验
		if(StringUtils.isBlank(request.getParameter("time")) || StringUtils.isBlank(request.getParameter("_dx"))) {
			this.writerForError(request, response, 1002, "Lack of parameters!");
			return false;
		}
		
		// 校验时间戳有效期(上下15分钟)
		long time = Long.valueOf(request.getParameter("time"));
		long curTime = System.currentTimeMillis();
		if(curTime - time > 900000 || time - curTime > 900000) {
			this.writerForError(request, response, 1002, "The request has expired!");
			return false;
		}
		
		// 校验密文
		String _dx = request.getParameter("_dx");
		if(!_dx.equals(EncryptionUtils.md5(apiProperties.getSecret().trim() + time))){
			this.writerForError(request, response, 1004, "Failure to verify ciphertext!");
			return false;
		}
		
		// 防刷控制(暂不开启)
		if(!this.preventBrush(clientIp + "-" + request.getRequestURI())) {
			this.writerForError(request, response, 1005, "Request frequency is too high!");
			return false;
		}
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
    		ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
    		Exception e) throws Exception {
    }
    
    /**
     * 写入返回信息
     * @param request
     * @param response
     * @param code
     * @param msg
     */
    private void writerForError(HttpServletRequest request, HttpServletResponse response, int code, String msg) {
    	String uri = request.getRequestURI();
		String param = request.getQueryString() == null ? "" : "?" + request.getQueryString();
    	System.out.println("### " + msg);
    	System.out.println(uri + param);
    	
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
    
    /**
     * 防刷校验 [60r/m]
     * @param key
     * @return
     */
    private boolean preventBrush(String key) {
    	key = "PREVENT-BRUSH-" + key;
    	
    	if(redisTemplateUtil.hasKey(key)) {
    		long r = redisTemplateUtil.incr(key, 1); // 累计访问次数
    		
    		if(r > 60) {
    			if(r == 61) {
    				redisTemplateUtil.expire(key, 1800); // 封禁半小时
    			}
    			return false;
    		}
    	} else {
    		redisTemplateUtil.set(key, 1, 60); // 初始缓存1分钟
    	}
    	
    	return true;
    }

}
