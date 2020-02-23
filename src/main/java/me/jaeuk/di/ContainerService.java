package me.jaeuk.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

// di framework 를 직접 만들보면서 스터디.
public class ContainerService {

    public static <T> T getObject(Class<T> classType)  {
        T instance = createInstance(classType);
        // 필드에 inject 어노테이션 있으면 주입.
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            if(f.getAnnotation(Inject.class) != null){
                Object fieldInstance = createInstance(f.getType());
                f.setAccessible(true);
                try {
                    f.set(instance, fieldInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
