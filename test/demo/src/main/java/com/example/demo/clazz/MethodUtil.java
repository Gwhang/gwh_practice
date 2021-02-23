package com.example.demo.clazz;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  invoke 方法应用
 */
public final class MethodUtil {

    private static Map<String,Object> cacheMap=new ConcurrentHashMap<String,Object>();

    public MethodUtil() {
    }

    /**
     * 执行指定对象的指定方法
     * @param obj 指定的对象
     * @param methodName 指定的方法名称
     * @param params 传输的参数
     * @return
     */
    public static Object invokeMethod(Object obj,String methodName,Object... params){
        Class<?> objectClass=obj.getClass();
        try {
            if(params.length==0||params==null){
                Method declaredMethod = objectClass.getDeclaredMethod(methodName);
                declaredMethod.setAccessible(true);
                Object result = declaredMethod.invoke(obj);
                return result;
            }else{
                int size= params.length;
                Class<?>[] classes=new Class[size];
                Object[] objects=new Object[size];
                for (int i=0;i<size;i++){
                    classes[i]=params[i].getClass();
                    objects[i]=params[i];
                }
                Method declaredMethod = objectClass.getDeclaredMethod(methodName, classes);
                declaredMethod.setAccessible(true);
                Object result = declaredMethod.invoke(obj, objects);
                return result;
            }

        }catch (Exception e){
            e.printStackTrace();

        }

        return null;
    }

    /**
     * 缓存注解
     * @param obj 指定的对象
     * @param methodName 指定的方法名称
     * @param params 传输的参数
     * @return
     */
    public static Object CacheMethod(Object obj,String methodName,Object... params){
        Class<?> objectClass=obj.getClass();
        try {
            if(params.length==0||params==null){
                //没有参数的情况下，以大KEY为缓存的key
                Method declaredMethod = objectClass.getDeclaredMethod(methodName);
                declaredMethod.setAccessible(true);
                Cache annotation = declaredMethod.getAnnotation(Cache.class);
                //方法上面有cache注解进行缓存
                if(annotation!=null){
                    String key=annotation.key();
                    Object value= cacheMap.get(key);
                    if(value != null){
                        return value;
                    }
                }
                Object result = declaredMethod.invoke(obj);
                //将数据放入map进行缓存
                if(annotation != null){
                    String key=annotation.key();
                    cacheMap.put(key,result);

                }
            }else{
                int size= params.length;
                Class<?>[] classes=new Class[size];
                Object[] objects=new Object[size];
                for (int i=0;i<size;i++){
                    classes[i]=params[i].getClass();
                    objects[i]=params[i];
                }
                Method declaredMethod = objectClass.getDeclaredMethod(methodName, classes);
                declaredMethod.setAccessible(true);
                Cache annotation = declaredMethod.getAnnotation(Cache.class);
                //方法上面有cache注解进行缓存
                if(annotation!=null){
                    //获取大 key
                    String key=annotation.key();
                    //获取 小 key
                    Object param = params[0];
                    // 拼接缓存key
                    String cacheKey = key +":"+param;
                    //从缓存中查找数据
                    Object value = cacheMap.get(cacheKey);
                    if(value != null){
                        return value;
                    }

                }
                
                Object result = declaredMethod.invoke(obj, objects);
                //将数据放入map进行缓存
                if(annotation != null){
                    //获取大 key
                    String key=annotation.key();
                    //获取 小 key
                    Object param = params[0];
                    // 拼接缓存key
                    String cacheKey = key +":"+param;

                    cacheMap.put(cacheKey,result);

                }
            }

        }catch (Exception e){
            e.printStackTrace();

        }

        return null;
    }

    public static void main(String[] args) {
        User user=new User("张三",23,"123456");
        Object getName = invokeMethod(user, "getName");
        System.out.println(getName);

        Object method = invokeMethod(user, "setName", "李四");
        System.out.println(user);

    }

}
