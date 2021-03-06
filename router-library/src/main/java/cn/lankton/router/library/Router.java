package cn.lankton.router.library;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taofangxin on 16/9/18.
 */
public class Router {
    private static Map<String, String> map; // 记录route
    private static Map<String, String> paramMap; // 记录参数类型

    static {
        map = new HashMap<>();
        paramMap = new HashMap<>();
        try {
            Class.forName("cn.lankton.router.library.RouterStaticInit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addPackage(String packageName) {
        try {
            Class.forName(packageName + ".RouterStaticInit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void add(String route, String clazz, String paramType) {
        map.put(route, clazz);
        paramMap.put(route, paramType);
    }

    public static void route(Context context, String route) {
        if (null == route) {
            return;
        }
        List<Query> queries = new ArrayList<>();
        String pathStr = route; // 可能存在多级路径, 打开多个界面
        if (route.contains("?")) {
            int index = route.indexOf("?");
            String queriesStr = route.substring(index + 1);
            getQueries(queriesStr, queries);
            pathStr = route.substring(0, index);
        }
        if (null != pathStr) {
            String[] paths = pathStr.split("/");
            for (int i = 0; i < paths.length; i++) {
                route = paths[i];
                for (String key : map.keySet()) {
                    if (key.equals(route)) {
                        String value = map.get(key);
                        String paramsStr = paramMap.get(key);
                        Map<String, String> typeMap = new HashMap();
                        if (null != paramsStr && paramsStr.length() > 0) {
                            String[] paramStrArray = paramsStr.split("&");
                            for (String paramStr : paramStrArray) {
                                if (paramsStr.length() > 0) {
                                    if (paramStr.contains("=")) {
                                        int index = paramStr.indexOf("=");
                                        String key1 = paramStr.substring(0, index);
                                        String value1 = paramStr.substring(index + 1);
                                        typeMap.put(key1, value1);
                                    } else {
                                        // 默认当作String
                                        typeMap.put(paramStr, "String");
                                    }
                                }
                            }
                        }
                        String cls = value;
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(context, cls));
                        if (!(context instanceof Activity)) {
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }
                        for (Query query : queries) {
                            String type = typeMap.get(query.key);
                            // 捕获异常， 让后面的解析继续进行
                            try {
                                if ("int".equals(type)) {
                                    intent.putExtra(query.key, Integer.valueOf(query.value));
                                } else if ("double".equals(type)) {
                                    intent.putExtra(query.key, Double.valueOf(query.value));
                                } else if ("boolean".equals(type)) {
                                    intent.putExtra(query.key, Boolean.valueOf(query.value));
                                } else {
                                    // 剩下的全部当作String
                                    intent.putExtra(query.key, query.value);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        // Router中结果数据清掉
                        Router.clearResult();
                        context.startActivity(intent);
                        break;
                    }
                }
            }
        }
    }

    static private void getQueries(String queriesStr, List<Query> queries) {
        if (null != queriesStr) {
            String[] strs = queriesStr.split("&");
            for (String str : strs) {
                if (str.contains("=")) {
                    // 没＝直接不管了
                    String[] unitStrs = str.split("=");
                    Query query = new Query();
                    int index = str.indexOf("=");
                    query.key = str.substring(0, index);
                    query.value = str.substring(index + 1);
                    try {
                        query.value = URLDecoder.decode(query.value, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    queries.add(query);
                }
            }
        }
    }

    static public Class<?> backFrom() {
        return RouteResult.getInstance().backFrom();
    }

    static public Bundle getResult() {
        return RouteResult.getInstance().getData();
    }

    static public void setResult(Bundle bundle, Class<?> backFrom) {
        RouteResult.getInstance().setData(bundle);
        RouteResult.getInstance().setBackFrom(backFrom);
    }

    static public void clearResult() {

    }

    static class Query {
        String key;
        String value;
    }

}
