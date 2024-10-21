import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class main {

    public static void main(String[] args) {
        Proverka Proverka = new Proverka();
        int sum = SumIntegeer(Proverka);
        System.out.println("Cумма " + sum);
    }

    public static int SumIntegeer(Object obj) {
        int sum = 0;
        Class cl = obj.getClass();
        for (Field field : cl.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() == int.class || field.getType() == Integer.class) {
                int modifiers = field.getModifiers();
                if (Modifier.isPublic(modifiers) || Modifier.isPrivate(modifiers)) {

                    if (Modifier.isStatic(modifiers)) {
                        try {

                            sum += field.getInt(null);
                        } catch (IllegalAccessException e) {
                            System.err.println("Ошибка доступа к полю: " + e.getMessage());
                        }
                    } else {
                        try {
                            sum += field.getInt(obj);
                        } catch (IllegalAccessException e) {
                            System.err.println("Ошибка доступа к полю: " + e.getMessage());
                        }
                    }
                }
            }
        }
        return sum;
    }
}