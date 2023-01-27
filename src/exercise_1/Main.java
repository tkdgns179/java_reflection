package exercise_1;

import java.util.Collection;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;

        HashMap<String, Integer> mapObject = new HashMap<>();

        Class<?> hashMapClass = mapObject.getClass();

        Class<?> squareClass = Class.forName("exercise_1.Main$Square");

        // printClassInfo(stringClass, hashMapClass, squareClass);

        // java 1.8 에서는 사용할 수 없는 타입이라 버전을 바꿈
        var circleObject = new Drawable() {
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };

        printClassInfo(Collection.class, boolean.class, int [][].class, Color.class);
    }

    private static void printClassInfo(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            System.out.println(String.format("class name : %s, class package name : %s",
                clazz.getSimpleName(),
                clazz.getPackageName()
            ));

            Class<?> [] implementedInterfaces = clazz.getInterfaces();

            for (Class<?> implementedInterface : implementedInterfaces) {
                System.out.println(String.format("class %s implements : %s",
                    implementedInterface.getSimpleName(),
                    implementedInterface.getPackageName()
                ));
            }

            System.out.println("Is array : " + clazz.isArray());
            System.out.println("Is primitive : " + clazz.isPrimitive());
            System.out.println("Is enum : " + clazz.isEnum());
            System.out.println("Is interface : " + clazz.isInterface());
            System.out.println("Is anoymous : " + clazz.isAnonymousClass());

            System.out.println();
            System.out.println();
        }
    }

    private static class Square implements Drawable {

        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private static interface Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        BLUE,
        RED,
        GREEN
    }
}
