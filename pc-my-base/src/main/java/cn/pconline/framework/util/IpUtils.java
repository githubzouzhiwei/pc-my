package cn.pconline.framework.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {
	public static String getIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded;
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                ip = realIp + "/" + forwarded.replaceAll(", " + realIp, "");
            }
        }
        return ip;
    }

	private static String localIp;
	static {
		localIp = getLocalAddress();
	}
	public static String getIp() {
		return localIp;
	}

    @SuppressWarnings("rawtypes")
	static String getLocalAddress() {
        String result = null;
        Enumeration enu = null;
        try {
            enu = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            return "127.0.0.1";
        }
        while (enu.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) (enu.nextElement());
            Enumeration ias = ni.getInetAddresses();
            while (ias.hasMoreElements()) {
                InetAddress i = (InetAddress) (ias.nextElement());
                String addr = i.getHostAddress();
				if (addr.startsWith("192.")) {
                    return addr;
                }
            }
        }

        return result;
    }
    
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIp( HttpServletRequest request ){
		String clientIp = request.getHeader("X-Forwarded-For");
		if (StringUtils.isEmpty(clientIp)){
			clientIp = request.getHeader("X-real-ip");
		}
		if (StringUtils.isEmpty(clientIp)){
			clientIp = request.getRemoteAddr();
		}
		if (StringUtils.isNotEmpty(clientIp)){
			String[] ips = clientIp.split(",");
			if (ips.length >= 2){
				/*if(ips[0].trim().startsWith("192.168") && StringUtils.isNotBlank(ips[1])) {
					clientIp = ips[1];
        		} else {
        			clientIp = ips[0];
        		}*/
                //如果IP数长度大于等于2的话，遍历数组找出第一个不是内网的地址
                for(int i=0; i < ips.length;i++){
                    if( !judgePersonNetIp(ips[i]) ){
                        return ips[i];
                    }
                }
                //如果遍历完所有IP后，都是内网的IP地址，那么返回第一个IP
                return ips[0];
			}
		}
		return StringUtils.isNotEmpty(clientIp)? clientIp.trim() : clientIp;
	}


    /**
     * 通过IP地址判断其是否内网IP地址 是的话返回true，否则返回false
     * @param ip
     * @return
     */
	public static boolean judgePersonNetIp(String ip){
	    boolean result = false;

        try{
            ip = ip.trim();
            if(ip.startsWith("192.168")){ // C类
                result = true;
            }else if(ip.startsWith("10.")){ // B类
                result = true;
            }else if(ip.startsWith("172.")){
                ip = ip.substring(4);
                if(ip.indexOf(".") > 0){
                    String ipNumStr = ip.substring(0,ip.indexOf("."));
                    long ipNum = Integer.parseInt(ipNumStr);
                    if(ipNum >= 16 && ipNum<=31){ // A类
                        result = true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
