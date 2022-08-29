package com.bsg.upm.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class IpV4Utils {

    // IpV4的正则表达式，用于判断IpV4地址是否合法
    private static final String IPV4_REGEX = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})";

    /**
     * 判断ipV4或者mask地址是否合法，通过正则表达式方式进行判断
     * 
     * @param ipv4
     */
    public static boolean ipV4Validate(String ipv4) {
        return ipv4Validate(ipv4, IPV4_REGEX);
    }

    private static boolean ipv4Validate(String addr, String regex) {
        if (addr == null) {
            return false;
        } else {
            return Pattern.matches(regex, addr.trim());
        }
    }

    public static boolean isSameSegment(String ip1, String ip2) {
        String[] ip1Arr = ip1.split("\\.");
        String[] ip2Arr = ip2.split("\\.");
        if (ip1Arr[0].equals(ip2Arr[0]) && ip1Arr[1].equals(ip2Arr[1]) && ip1Arr[2].equals(ip2Arr[2])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个ip地址，如果两个都是合法地址，则1代表ip1大于ip2，-1代表ip1小于ip2,0代表相等；
     * 
     * @return
     */
    public static int compareIpV4s(String ip1, String ip2) {
        int result = 0;
        int ipValue1 = getIpV4Value(ip1); // 获取ip1的32bit值
        int ipValue2 = getIpV4Value(ip2); // 获取ip2的32bit值
        if (ipValue1 > ipValue2) {
            result = 1;
        } else if (ipValue1 < ipValue2) {
            result = -1;
        }
        return result;
    }

    public static int getIpV4Value(String ipOrMask) {
        byte[] addr = getIpV4Bytes(ipOrMask);
        int address1 = addr[3] & 0xFF;
        address1 |= ((addr[2] << 8) & 0xFF00);
        address1 |= ((addr[1] << 16) & 0xFF0000);
        address1 |= ((addr[0] << 24) & 0xFF000000);
        return address1;
    }

    /**
     * 将ip 32bit值转换为如“192.168.0.1”等格式的字符串
     * 
     * @param ipValue
     *            32bit值
     * @return
     */
    public static String trans2IpStr(int ipValue) {
        // 保证每一位地址都是正整数
        return ((ipValue >> 24) & 0xff) + "." + ((ipValue >> 16) & 0xff) + "." + ((ipValue >> 8) & 0xff) + "."
                + (ipValue & 0xff);
    }

    public static byte[] getIpV4Bytes(String ipOrMask) {
        try {
            String[] addrs = ipOrMask.split("\\.");
            int length = addrs.length;
            byte[] addr = new byte[length];
            for (int index = 0; index < length; index++) {
                addr[index] = (byte) (Integer.parseInt(addrs[index]) & 0xff);
            }
            return addr;
        } catch (Exception e) {
        }
        return new byte[4];
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     * 
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    // IP的正则
    private static Pattern pattern = Pattern
            .compile("(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\." + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
                    + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\." + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})");
    public static final String DEFAULT_ALLOW_ALL_FLAG = "*";// 允许所有ip标志位
    public static final String DEFAULT_DENY_ALL_FLAG = "0"; // 禁止所有ip标志位

    /**
     * 
     * getAvaliIpList:(根据IP白名单设置获取可用的IP列表).
     * 
     * @date 2017-4-17 下午02:50:20
     * @param ipConfig
     * @return
     */

    private static Set<String> getAvaliIpList(String allowIp) {
        String[] splitRex = allowIp.split(";");// 拆分出白名单正则
        Set<String> ipList = new HashSet<String>(splitRex.length);
        for (String allow : splitRex) {
            if (allow.contains("*")) {// 处理通配符 *
                String[] ips = allow.split("\\.");
                String[] from = new String[] { "0", "0", "0", "0" };
                String[] end = new String[] { "255", "255", "255", "255" };
                List<String> tem = new ArrayList<String>();
                for (int i = 0; i < ips.length; i++)
                    if (ips[i].indexOf("*") > -1) {
                        tem = complete(ips[i]);
                        from[i] = null;
                        end[i] = null;
                    } else {
                        from[i] = ips[i];
                        end[i] = ips[i];
                    }

                StringBuilder fromIP = new StringBuilder();
                StringBuilder endIP = new StringBuilder();
                for (int i = 0; i < 4; i++)
                    if (from[i] != null) {
                        fromIP.append(from[i]).append(".");
                        endIP.append(end[i]).append(".");
                    } else {
                        fromIP.append("[*].");
                        endIP.append("[*].");
                    }
                fromIP.deleteCharAt(fromIP.length() - 1);
                endIP.deleteCharAt(endIP.length() - 1);

                for (String s : tem) {
                    String ip = fromIP.toString().replace("[*]", s.split(";")[0]) + "-"
                            + endIP.toString().replace("[*]", s.split(";")[1]);
                    if (validate(ip)) {
                        ipList.add(ip);
                    }
                }
            } else if (allow.contains("/")) {// 处理 网段 xxx.xxx.xxx./24
                ipList.add(allow);
            } else {// 处理单个 ip 或者 范围
                if (validate(allow)) {
                    ipList.add(allow);
                }
            }

        }

        return ipList;
    }

    /**
     * 对单个IP节点进行范围限定
     * 
     * @param arg
     * @return 返回限定后的IP范围，格式为List[10;19, 100;199]
     */
    private static List<String> complete(String arg) {
        List<String> com = new ArrayList<String>();
        int len = arg.length();
        if (len == 1) {
            com.add("0;255");
        } else if (len == 2) {
            String s1 = complete(arg, 1);
            if (s1 != null)
                com.add(s1);
            String s2 = complete(arg, 2);
            if (s2 != null)
                com.add(s2);
        } else {
            String s1 = complete(arg, 1);
            if (s1 != null)
                com.add(s1);
        }
        return com;
    }

    private static String complete(String arg, int length) {
        String from = "";
        String end = "";
        if (length == 1) {
            from = arg.replace("*", "0");
            end = arg.replace("*", "9");
        } else {
            from = arg.replace("*", "00");
            end = arg.replace("*", "99");
        }
        if (Integer.valueOf(from) > 255)
            return null;
        if (Integer.valueOf(end) > 255)
            end = "255";
        return from + ";" + end;
    }

    /**
     * 在添加至白名单时进行格式校验
     * 
     * @param ip
     * @return
     */
    private static boolean validate(String ip) {
        String[] temp = ip.split("-");
        for (String s : temp)
            if (!pattern.matcher(s).matches()) {
                return false;
            }
        return true;
    }

    /**
     * 
     * isPermited:(根据IP,及可用Ip列表来判断ip是否包含在白名单之中).
     * 
     * @date 2017-4-17 下午03:01:03
     * @param ip
     * @param ipList
     * @return
     */
    private static boolean isPermited(String ip, Set<String> ipList) {
        if (ipList.isEmpty() || ipList.contains(ip))
            return true;
        for (String allow : ipList) {
            if (allow.indexOf("-") > -1) {// 处理 类似 192.168.0.0-192.168.2.1
                String[] tempAllow = allow.split("-");
                String[] from = tempAllow[0].split("\\.");
                String[] end = tempAllow[1].split("\\.");
                String[] tag = ip.split("\\.");
                boolean check = true;
                for (int i = 0; i < 4; i++) {// 对IP从左到右进行逐段匹配
                    int s = Integer.valueOf(from[i]);
                    int t = Integer.valueOf(tag[i]);
                    int e = Integer.valueOf(end[i]);
                    if (!(s <= t && t <= e)) {
                        check = false;
                        break;
                    }
                }
                if (check)
                    return true;
            } else if (allow.contains("/")) {// 处理 网段 xxx.xxx.xxx./24
                int splitIndex = allow.indexOf("/");
                // 取出子网段
                String ipSegment = allow.substring(0, splitIndex); // 192.168.3.0
                // 子网数
                String netmask = allow.substring(splitIndex + 1);// 24
                // ip 转二进制
                long ipLong = ipToLong(ip);
                // 子网二进制
                long maskLong = (2L << 32 - 1) - (2L << Integer.valueOf(32 - Integer.valueOf(netmask)) - 1);
                // ip与和子网相与 得到 网络地址
                String calcSegment = longToIP(ipLong & maskLong);
                // 如果计算得出网络地址和库中网络地址相同 则合法
                if (ipSegment.equals(calcSegment))
                    return true;
            }
        }
        return false;
    }

    /**
     * 
     * isPermited:(根据IP地址，及IP白名单设置规则判断IP是否包含在白名单).
     * 
     * @date 2017-4-17 下午03:01:37
     * @param ip
     * @param ipWhiteConfig
     * @return
     */
    public static boolean isPermited(String ip, String ipWhiteConfig) {
        if (DEFAULT_ALLOW_ALL_FLAG.equals(ipWhiteConfig))
            return true;
        if (DEFAULT_DENY_ALL_FLAG.equals(ipWhiteConfig))
            return false;
        if (null == ip || "".equals(ip))
            return false;
        // ip格式不对
        if (!pattern.matcher(ip).matches())
            return false;
        Set<String> ipList = getAvaliIpList(ipWhiteConfig);
        return isPermited(ip, ipList);
    }

    private static long ipToLong(String strIP) {
        long[] ip = new long[4];
        // 先找到IP地址字符串中.的位置
        int position1 = strIP.indexOf(".");
        int position2 = strIP.indexOf(".", position1 + 1);
        int position3 = strIP.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIP.substring(0, position1));
        ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIP.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    // 将10进制整数形式转换成127.0.0.1形式的IP地址
    private static String longToIP(long longIP) {
        StringBuilder sb = new StringBuilder("");
        // 直接右移24位
        sb.append(String.valueOf(longIP >>> 24));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf(longIP & 0x000000FF));
        return sb.toString();
    }
}
