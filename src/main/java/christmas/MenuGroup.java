package christmas;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {
    MAIN("메인", Arrays.asList(MenuDetail.BABY_BACK_RIBS, MenuDetail.CHRISTMAS_PASTA, MenuDetail.SEAFOOD_PASTA, MenuDetail.T_BONE_STEAK)),
    APPETIZER("애피타이저", Arrays.asList(MenuDetail.CAESAR_SALAD, MenuDetail.MUSHROOM_SOUP, MenuDetail.TAPAS)),
    DESSERT("디저트", Arrays.asList(MenuDetail.CHOCOLATE_CAKE, MenuDetail.ICE_CREAM)),
    DRINK("음료", Arrays.asList(MenuDetail.CHAMPAGNE, MenuDetail.RED_WINE, MenuDetail.ZERO_COLA)),
    EMPTY("없음", Arrays.asList(MenuDetail.EMPTY));

    private final String category;
    private final List<MenuDetail> menuList;

    MenuGroup(String category, List<MenuDetail> menuList) {
        this.category = category;
        this.menuList = menuList;
    }
    public static MenuGroup findByMenuList(MenuDetail pickMenu){
        return Arrays.stream(MenuGroup.values())
                .filter(menu -> menu.hasPickMenu(pickMenu))
                .findAny()
                .orElse(EMPTY);
    }
    public boolean hasPickMenu(MenuDetail pickMenu){
        return menuList.stream()
                .anyMatch(menu -> menu==pickMenu);
    }
}
