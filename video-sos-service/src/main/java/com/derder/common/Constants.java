package com.derder.common;

import java.nio.charset.Charset;

public interface Constants
{
    String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    String MAX_CONNECTIONS_PERHOST = "maxConnectionsPerHost";

    String MAX_TOTAL_CONNECTIONS = "maxTotalConnections";

    String SYSTEM_GENERATOR = "system_generator ";

    String IDENTITY = "identity";

    // 自定义ID
    String ASSIGNED = "assigned";

    String UUID = "uuid";

    // 角色搜索限定
    String ROLE_SEARCH_LIMIT = "limit";

    String USER_ID = "userId";

    // 角色拥有索引库集合
    String ROLE_SEARCH_INDEX = "indexList";

    String DEFAULT_LANG = "zh-CN";

    String EN_DEFAULT_LANG = "en-US";

    String SUCCESS = "success";

    String POST = "POST";

    String GET = "GET";

    String HTTP_COLON = "http:";

    String DOUBLE_SLASH = "//";

    String SINGLE_SLASH = "/";

    String SYSTEM_ENCODING = "UTF-8";

    String QUESTION_MARK = "?";

    String COMMA = ",";

    Charset SYSTEM_CHARSET = Charset.forName(Constants.SYSTEM_ENCODING);

    String SESSION_ID = "sessionId";

    String TOKEN = "token";

    String USER_NO = "userNo"; //用户编号

    String CLIENT_TYPE_PARAM = "_c";

    String ANDROID = "android";

    String UA = "UA";

    String IMEI = "IMEI";

    String WEB = "web";

    String IOS = "ios";

}
