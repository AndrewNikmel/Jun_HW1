package Task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     *
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public void printFoodstuffs() {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет",
                    food.getCarbohydrates() ? "Да" : "Нет");
        });
    }

    public void cardBalancing() {
        AtomicInteger check = new AtomicInteger(0);
        boolean proteins = addFoodForBalance(Food::getProteins, check);
        boolean fats = addFoodForBalance(Food::getFats, check);
        boolean carbohydrates = addFoodForBalance(Food::getCarbohydrates, check);
        String balance = " ";
        if (check.get() == 3)
            balance = " уже ";
        if (proteins && fats && carbohydrates) {
            System.out.printf("\nКорзина%s сбалансирована по БЖУ\n. ", balance);
        } else
            System.out.println("\nНевозможно сбалансировать корзину по БЖУ\n.");
    }

    /**
     * Метод addFoodForBalance принимает предикат и объект check, который отслеживает количество успешных вызовов предиката.
     * Сначала метод проверяет, есть ли в списке foodstuffs элемент, удовлетворяющий предикату.
     * Если такой элемент есть, метод увеличивает значение объекта check и возвращает true.
     * Если такого элемента нет, метод добавляет новый элемент, который удовлетворяет предикату, в список foodstuffs.
     * Для этого он создает поток из объектов market.getThings(Food.class), фильтрует его с помощью предиката,
     * находит любой подходящий элемент и добавляет его в список. После этого метод возвращает true.
     */
    private boolean addFoodForBalance(Predicate<Food> food, AtomicInteger check) {
        if (foodstuffs.stream().noneMatch(food)) {
            return foodstuffs.add((T) market.getThings(Food.class).stream()
                    .filter(food)
                    .findAny()
                    .get());
        } else {
            check.incrementAndGet();
            return true;
        }
    }
    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }


//    public void cardBalancing()
//    {
//        boolean proteins = false;
//        boolean fats = false;
//        boolean carbohydrates = false;
//
//        for (var food : foodstuffs)
//        {
//            if (!proteins && food.getProteins())
//                proteins = true;
//            else
//            if (!fats && food.getFats())
//                fats = true;
//            else
//            if (!carbohydrates && food.getCarbohydrates())
//                carbohydrates = true;
//            if (proteins && fats && carbohydrates)
//                break;
//        }
//
//        if (proteins && fats && carbohydrates)
//        {
//            System.out.println("Корзина уже сбалансирована по БЖУ.");
//            return;
//        }
//
//        for (var thing : market.getThings(clazz))
//        {
//            if (!proteins && thing.getProteins())
//            {
//                proteins = true;
//                foodstuffs.add(thing);
//            }
//            else if (!fats && thing.getFats())
//            {
//                fats = true;
//                foodstuffs.add(thing);
//            }
//            else if (!carbohydrates && thing.getCarbohydrates())
//            {
//                carbohydrates = true;
//                foodstuffs.add(thing);
//            }
//            if (proteins && fats && carbohydrates)
//                break;
//        }
//
//        if (proteins && fats && carbohydrates)
//            System.out.println("Корзина сбалансирована по БЖУ.");
//        else
//            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
//
//    }

    //endregion

//    public Collection<T> getFoodstuffs() {
//        return foodstuffs;
//    }



//    public void printFoodstuffs()
//    {
//        /*int index = 1;
//        for (var food : foodstuffs)
//            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
//                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/


//        AtomicInteger index = new AtomicInteger(1);
//        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
//                index.getAndIncrement(), food.getName(),
//                food.getProteins() ? "Да" : "Нет",
//                food.getFats() ? "Да" : "Нет",
//                food.getCarbohydrates() ? "Да" : "Нет"));
//    }
}
